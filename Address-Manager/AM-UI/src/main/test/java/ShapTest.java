import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tscloud.address.domain.entity.AddrApprovalEntity;
import com.tscloud.address.domain.entity.AddressResult;
import com.tscloud.address.domain.utils.ConstantCrud;
import com.tscloud.address.domain.utils.DistrictLevel;
import com.tscloud.address.ui.utils.ApiSystemResult;
import com.tscloud.common.utils.Reflections;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.junit.Test;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.GeometryDescriptor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author Leighton
 * @create 2018-11-07.
 */
public class ShapTest {

    @Test
    public void test() throws Exception {

        File file = new File("E:/data/三类点项目监控-车站.shp");
      /*  Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", file.toURI().toURL());*/


        ShapefileDataStore dataStore =new ShapefileDataStore(file.toURI().toURL());
        dataStore.setCharset(Charset.forName(codeString(file)));

        //
        Class cazz= AddressResult.class;
        Method []methods=cazz.getDeclaredMethods();
        List<String> setname=new ArrayList<>();
        for (Method m:methods){
            if (m.getName().indexOf("set")==0){
                String name=m.getName().substring(3,m.getName().length());
                name=name.substring(0, 1).toLowerCase() + name.substring(1);
                setname.add(name);
            }
        }
        List<String> attrMatch=new ArrayList<>();
        //获取文件属性字段 看看是否满足我们的要求
        dataStore.getSchema().getAttributeDescriptors().forEach(action->{
            //System.out.println(action);
            if (!(action instanceof GeometryDescriptor)){
                System.out.println(action);
                if (setname.contains(action.getName().getLocalPart())){
                    attrMatch.add(action.getName().getLocalPart());
                }
            }
        });
        if(attrMatch.size()>4){

        }
//        System.out.println(attrMatch);
        //判断是否是poi文件
    if(!dataStore.getFeatureSource().getSchema().getGeometryDescriptor().getType().getName().toString().equals("Point")){
        throw  new UnsupportedOperationException("这个shap文件中的数据不符合要求");
    }


        FeatureCollection<SimpleFeatureType, SimpleFeature> collection =  dataStore.getFeatureSource().getFeatures();
        FeatureIterator<SimpleFeature> features = collection.features();
        try {
        //collection.size() 获取数据总数
       Object []obj=collection.toArray();
            for(int i=0;i<10;i++){//在此处可以模拟分页 提高写入的速度
                SimpleFeature  feature= (SimpleFeature) obj[i];
                Set<Property> properties = new LinkedHashSet<>(feature.getProperties());
                Point point = (Point) feature.getDefaultGeometry();
                properties.forEach(action->{
                    if (!(action instanceof GeometryAttribute)){
                        System.out.println(action.getName().getLocalPart()+"->"+action.getValue());
                    }
                });
            }
        }finally {
            features.close();
        }

    }

    @Test
    public void test02() throws IllegalAccessException, InstantiationException {
        Class cazz= AddressResult.class;
       /* Field[] fields= cazz.getDeclaredFields();
//        cazz.getDeclaredFields()
        for (Field field:fields){
            System.out.println(field.getName());
        }*/

       Method []methods=cazz.getDeclaredMethods();
        List<String> list=new ArrayList<>();
        for (Method m:methods){
            if (m.getName().indexOf("set")==0){
                String name=m.getName().substring(3,m.getName().length());
                //field.substring(0,1).toLowerCase()
                name=name.substring(0, 1).toLowerCase() + name.substring(1);
                list.add(name);
            }
//            list.contains()
//            System.out.println(m.getName().indexOf("set"));
        }
        AddressResult   result=new AddressResult();
        List a= Reflections.takeSet(result);

        System.out.println(a);
//        AddressResult result= (AddressResult) cazz.newInstance();
//        System.out.println(result.toString());

       String str=String.format("在下,%s,%s,%s", "东邪","西毒","南帝");
        System.out.println(MessageFormat.format("该域名{0}被访问了 {1} 次.", "1" , "iVisit"));
        System.out.println(str);
    }


    @Test
    public void test03(){
        AddrApprovalEntity entity=new AddrApprovalEntity();
        entity.setOperate(ConstantCrud.select.name());
        System.out.println(ConstantCrud.select);
    }

    @Test
    public void test04(){
        String result="{\"total\":1,\"success\" : 1," +
                "\"status\" : \"0\", \"SuccessResult\" :[{\"name\" : \"由家村\",\"address\" : \"广东省珠海市香洲区南坪镇\",\"x\" :113.5013033294444}],\"FailureResult\":null}";
        /*JSONObject jsonObject= JSON.parseObject(result);
//        jsonObject.get("Resultsfailure")
        List<AddressResult> add= JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("ResultsSuccess"), SerializerFeature.WriteClassName),AddressResult.class);
        System.out.println(add);*/

        ApiSystemResult result1= JSON.parseObject(result, ApiSystemResult.class);
        List<AddressResult>  a= result1.getSuccessResult();
//        System.out.println(a.get(0));
//        System.out.println(JSON.toJSONString(a.get(0)));
        try {
//            System.out.println(BeanObjectToMap.convertBean(a.get(0)));
//            System.out.println(StringUtils.createLinkStringByGet(StringUtils.strToMap()),"\"x\""));

        String json="{\"adcode\":\"440402\",\"address\":\"武汉市洪山区......\",\"id\":\"84082786\",\"level\":5,\"name\":\"这是我的测试数据\",\"type\":\"290105\"}";

          Map<String,Object> map =JSON.parseObject(json,Map.class);
            System.out.println(">>>>"+map);
//            System.out.println(StringUtils.createLinkStringByGet(map,"name"));


        } catch (Exception e) {
            e.printStackTrace();
        }


//        System.out.println(String.valueOf(null));
    }

    @Test
    public void test05(){
        final Integer PAGE_SIZE = 3;
        List<Long> datas = Arrays.asList(new Long [] {1L,2L,3L,4L,5L,6L,7L,8L});

        //总记录数
        Integer totalCount = datas.size();

        //分多少次处理
        Integer requestCount = totalCount / PAGE_SIZE;

        for (int i = 0; i <= requestCount; i++) {
            Integer fromIndex = i * PAGE_SIZE;
            //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
            int toIndex = Math.min(totalCount, (i + 1) * PAGE_SIZE);
            List<Long> subList = datas.subList(fromIndex, toIndex);
            System.out.println(subList);
            //总数不到一页或者刚好等于一页的时候,只需要处理一次就可以退出for循环了
            if (toIndex == totalCount) {
                break;
            }
        }
    }

    @Test
    public void test06() throws Exception {
        File filePath =new File("D:/v_village.json");
        String input = FileUtils.readFileToString(filePath, "UTF-8");
        JSONObject js=JSON.parseObject(input);

        JSONArray array=js.getJSONArray("RECORDS");
        List<AddressResult>results=JSON.parseArray(array.toJSONString(),AddressResult.class);
        for (AddressResult result:results){
            System.out.println(result);
        }
       //
    }

    @Test
    public void test07() throws Exception {
        String filepath="D:/test/test.shp";
        //创建shape文件对象
        File file = new File(filepath);
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put( ShapefileDataStoreFactory.URLP.key, file.toURI().toURL() );
        ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);
        //定义图形信息和属性信息
        SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
        tb.setCRS(DefaultGeographicCRS.WGS84);
        tb.setName("shapefile");
        tb.add("the_geom", Point.class);
        tb.add("address",String.class);
        tb.add("type", String.class);
        tb.add("adcode", String.class);
        tb.add("level", Integer.class);
        ds.createSchema(tb.buildFeatureType());
        ds.setCharset(Charset.forName("GBK"));
        //设置Writer
        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);

        File filePath =new File("D:/v_village.json");
        String input = FileUtils.readFileToString(filePath, "UTF-8");
        JSONObject js=JSON.parseObject(input);

        JSONArray array=js.getJSONArray("RECORDS");
        List<AddressResult>results=JSON.parseArray(array.toJSONString(),AddressResult.class);
        for (AddressResult result:results){
            //写下一条
            SimpleFeature feature = writer.next();
            feature.setAttribute("the_geom",new GeometryFactory().createPoint(new Coordinate(result.getX()/3600,result.getY()/3600)));
            feature.setAttribute("address",result.getAddress());
            feature.setAttribute("type",result.getType());
            feature.setAttribute("adcode",result.getAdcode());
            feature.setAttribute("level",6);
        }
        writer.write();
        writer.close();
        ds.dispose();


        //读取刚写完shape文件的图形信息
      /*  ShpFiles shpFiles = new ShpFiles(filepath);
        ShapefileReader reader = new ShapefileReader(shpFiles, false, true, new GeometryFactory(), false);

        try {
            while (reader.hasNext()) {
                System.out.println(reader.nextRecord().shape());
            }
        } finally {
            reader.close();
        }*/

        ShapefileDataStore dataStore =new ShapefileDataStore(file.toURI().toURL());
        dataStore.setCharset(Charset.forName(codeString(file)));
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection =  dataStore.getFeatureSource().getFeatures();
        FeatureIterator<SimpleFeature> features = collection.features();
        while (features.hasNext()){
           SimpleFeature feature= features.next();
            Set<Property> properties = new LinkedHashSet<>(feature.getProperties());
            Point point = (Point) feature.getDefaultGeometry();
            properties.forEach(action->{
                if (!(action instanceof GeometryAttribute)){
                    System.out.println(action.getName().getLocalPart()+"->"+action.getValue());
                }
                System.out.println("---------");
            });
        }

    }


    @Test
    public void test08(){
     String code="441303008000";
        if (code.length()==12){
//            System.out.println((StringUtils.remove(StringUtils.substring(code,9),"0")).equals(""));
            System.out.println(StringUtils.remove(StringUtils.substring(code,6),"0").equals("0"));
        }
        System.out.println(StringUtils.substring(null,0,6));
        System.out.println(DistrictLevel.firstLevel.getLevel());
        int count=1500;
        int pageSize=23;
//        System.out.println(count/23);
        int toIndex = Math.min(30, (2 + 1) * pageSize);
//        System.out.println(toIndex);
    }
    /**
     * 获取编码方式
     *
     * @param
     * @return 编码方式
     * @throws Exception
     */
    public static String codeString(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bin = new BufferedInputStream(fileInputStream);
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        IOUtils.closeQuietly(bin);
        IOUtils.closeQuietly(fileInputStream);
        System.out.println(code);
        return code;
    }
}
