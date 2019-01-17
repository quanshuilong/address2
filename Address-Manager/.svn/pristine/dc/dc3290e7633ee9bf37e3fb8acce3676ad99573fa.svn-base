package com.tscloud.common.tool.datagirdtool;


import com.google.common.collect.Lists;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.transaction.TransactionMode;

import javax.transaction.TransactionManager;
import java.util.List;
import java.util.UUID;

/**
 * datagird manager
 */
public class DataGridTool {
    private static final Logger log = LogManager.getLogger(DataGridTool.class);

    private static DataGridTool dataGirdTool = null;
    private static  EmbeddedCacheManager embeddedCacheManager = null;

    private DataGridTool(){}

    public synchronized static DataGridTool initDataGird(){
        if (dataGirdTool == null){
            dataGirdTool = new DataGridTool();
            createDataGridServer();
        }
        return dataGirdTool;
    }

    /**
     * createDataGridServer
     * @return
     */
    private static void createDataGridServer(){
        try{
            if(embeddedCacheManager == null){
                String nodeName = "node"+ UUID.randomUUID().toString();
                //config ipv4
                System.setProperty("java.net.preferIPv4Stack", "true");
                log.info("Starting DataGrid Server ......");
                embeddedCacheManager = new DefaultCacheManager(
                        GlobalConfigurationBuilder.defaultClusteredBuilder()
                                .transport().clusterName("resourceDataGridCluster")
                                .nodeName(nodeName)
                                .addProperty("configurationFile", "jgroups.xml")
                                .build(),
                        new ConfigurationBuilder()
                                .clustering()
                                .cacheMode(CacheMode.REPL_SYNC).transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL)
                                .autoCommit(false)
                                .build()
                );
                log.info("Starting DataGrid Finish!");
            }
        }catch (Exception e){
            log.error("DataGrid Start error!",e);
        }
    }

    /**
     *  isExistCache
     * @param cacheName
     * @return
     */
    public boolean isExistCache(String cacheName){
        boolean flag = true;
        if(embeddedCacheManager == null ){
            this.createDataGridServer();
        }
        flag = embeddedCacheManager.cacheExists(cacheName);
        return flag;
    }

    /**
     * createCache  by name
     * @param cacheName
     * @return
     */
    public Cache createCache(String cacheName){
        if(embeddedCacheManager == null ){
            this.createDataGridServer();
        }
        try {
            Configuration configuration = new ConfigurationBuilder()
                    .clustering()
                    .cacheMode(CacheMode.DIST_SYNC)
                    .hash().numOwners(2).transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL)
                    .autoCommit(false)
                    .build();
            embeddedCacheManager.defineConfiguration(cacheName,configuration);
            log.info("Cache Name：【"+cacheName+"】 Create Succeed!");
        }catch (Exception e){
            log.error("Cache Name：【" + cacheName + "】 Create Failure!", e);
        }
        return embeddedCacheManager.getCache(cacheName);
    }

    /**
     * beginTransaction
     * @param cache
     */
    public void beginTransaction(Cache cache){
        TransactionManager tm = cache.getAdvancedCache().getTransactionManager();
        try {
            tm.begin();
            log.info("Cache Name:"+cache.getName()+" Transaction  Begin ...");
        }catch (Exception e){
            log.error("Cache Name:"+cache.getName()+" Transaction  Failure !");
        }
    }

    /**
     * commitTransaction
     * @param cache
     */
    public void commitTransaction(Cache cache){
        TransactionManager tm = cache.getAdvancedCache().getTransactionManager();
        try {
            tm.commit();
            log.info("Cache Name:"+cache.getName()+" Transaction  Commit!");
        }catch (Exception e){
            log.error("Cache Name:"+cache.getName()+" Transaction  Failure !");
        }
    }

    /**
     * getCache
     * @param cacheName
     * @return
     */
    public  Cache getCache(String cacheName){
        if(embeddedCacheManager == null ){
            this.createDataGridServer();
        }
        return embeddedCacheManager.getCache(cacheName);
    }

    /**
     * get cache if it not exist.
     * @param cacheName
     * @param flag true : if not exist then create, false : call getCache
     * @return cache
     * @author chenlu@ccuu.me
     */
    public Cache getCache(String cacheName, boolean flag) {
        return flag ? (isExistCache(cacheName) ? getCache(cacheName) : createCache(cacheName)) : getCache(cacheName);
    }

    /**
     * getKeys
     * @param cache
     * @param key
     * @return
     */
    public List<String> getKeys(Cache cache,String key){
        List<String> keyList = Lists.newArrayList();
        while (cache.keySet().iterator().hasNext()){
            String key_set = (String)cache.keySet().iterator().next();
            if (key_set.indexOf(key) != -1){
                keyList.add(key_set);
            }
        }
        return  keyList;
    }


    /**
     * put val to the cache
     * @param key
     * @param val
     * @param cache
     * @return
     * @author chenlu@ccuu.me
     */
    public boolean put(String key, Object val, Cache cache) {
        boolean isSuc = false;
        try {
//            beginTransaction(cache);
            cache.put(key, val);
//            commitTransaction(cache);
            isSuc = true;
        } catch (Exception e) {
            log.error("put value to cache failure.", e);
        }
        return isSuc;
    }

    /**
     * get value from cache by key
     * @param key
     * @param cache
     * @return
     * @author chenlu@ccuu.me
     */
    public Object get(String key, Cache cache) {
        Object obj = null;
        try {
//            beginTransaction(cache);
            obj = cache.get(key);
//            commitTransaction(cache);
        } catch (Exception e) {
            log.error("get value from cache failure.", e);
        }
        return obj;
    }

    public boolean del(String key, Cache cache) {
        boolean flag = false;
        try {
//            beginTransaction(cache);
            cache.remove(key);
//            commitTransaction(cache);
            flag = true;
        } catch (Exception e) {
            log.error("del data in cache by key failure.", e);
        }

        return flag;
    }
}
