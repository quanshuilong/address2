define(["jquery", "base", "map/AppEvent", "lodash", "OLNewSDK"], function ($, base, AppEvent, _, OpenLayers) {
    var OpenLayers = window.OpenLayers;

    function ScenarioView(options) {
        this.initialize(options);
    }

    _.assign(ScenarioView.prototype, {
        constructor: ScenarioView,
        /**
         * 获取默认地图方案的rest接口地址
         */
        getDefaultUrl: "userScenarioRestServer/getDefaultScenario",

        /**
         * 默认方案数据,进行临时保存,方便进行地图方案切换
         */
        defaultScenarioData: null,

        /**
         * 地图对象
         */
        map: null,

        /**
         * 地图方案是否加载完毕,加载完毕即进行地图定位操作
         */
        loadScenarioEnd: false,

        /**
         * 当前地图对象加载的图层数组,用于方案切换时删除之前叠加在地图对象上的图层
         */
        currLoadLayers: [],

        initialize: function (options) {
            this.map = options.mapView.map;
            this.vectorlayer = options.mapView.vectorlayer;
            var noInitDefault = options["noInitDefault"];
            !noInitDefault&& this.initDefaultScenarioData();
            !noInitDefault&& this.initEvent();
        },

        initEvent: function () {
            var that = this;

            /**
             * 点击卫星方案
             */
            $(document).on("click", "#sc_satellite", function (e) {
                $(".scenarioList").stop();
                //0  卫星    54  矢量
                $(".mapDIV .satellite").css("top", 0);
                $(".mapDIV .vector").css("top", 54);
                $(".mapDIV .sc_history").css("top", 108);
                that.switchScenarioByType("Image");
                if($(".luwangCheck")[0].checked === true) {
                    that.loadLuWangData2();
                }else {
                    that.removeLuWangData();
                }
                e.stopPropagation();
            });

            /**
             * 点击地图方案
             */
            $(document).on("click", "#sc_vector", function (event) {
                $(".scenarioList").stop();
                $(".mapDIV .vector").css("top", 0);
                $(".mapDIV .satellite").css("top", 54);
                $(".mapDIV .sc_history").css("top", 108);
                that.switchScenarioByType("Vector");
                that.removeLuWangData();
            });

            /**
             * 点击历史影像方案
             */
            $(document).on("click", "#sc_history", function () {
                $(".scenarioList").stop();
                $(".mapDIV .vector").css("top", 0);
                $(".mapDIV .satellite").css("top", 54);
                $(".mapDIV .sc_history").css("top", 108);
            });

            $(".scenarioList").on("mouseover", function (e) {
                $(".scenarioList").stop().animate({height: 54*$(".scenarioList>div").length});
            }).on("mouseout", function (e) {
                $(".scenarioList").stop().animate({height: 46});
            });

        },

        mapChangescenario: function(sec_id){
            base.post("userServiceRestServer/getByWhere", JSON.stringify({sec_id: sec_id})).done($.proxy(function (res) {
                var that = this;
                if (res.status == "success") {
                    var layers = this.createLayersByScenarioData(JSON.parse(res.content));
                    for (var j = 0; j < that.currLoadLayers.length; j++) {
                        that.map.removeLayer(that.currLoadLayers[j]);
                    }
                    layers[layers.length - 1].isBaseLayer = true;
                    that.map.addLayers(layers);
                    that.updateVectorlayerIndex();
                    that.updateOverViewControl();
                    that.currLoadLayers = layers;
                    that.loadScenarioEnd = true;
                    that.map.setCenter(new OpenLayers.LonLat(base.mapCenter.x, base.mapCenter.y), base.mapZoom);
                }
            }, this));
        },

        /**
         * 初始化默认方案数据
         */
        initDefaultScenarioData: function () {
            base.get(this.getDefaultUrl, {}).done($.proxy(function (res) {
                if (res.status == "success") {
                    this.defaultScenarioData = res.content;
                    this.switchScenarioByType("Vector");
                    base.globalEvent.emit(AppEvent.ScenarioView.loadDefaultScenarioData, this.defaultScenarioData);
                }
            }, this));
        },

        userInfo: null,
        /**
         * 根据方案类型加载指定方案数据
         * @param type
         */
        switchScenarioByType: function (type) {
            this.loadScenarioEnd = false;
            var data = base.parseJSON(this.defaultScenarioData);
            for (var i = 0; i < data.length; i++) {
                if (data[i].type === type) {
                    this.userInfo = JSON.parse(data[i].userInfo);
                    var layers = this.createLayersByScenarioData(data[i].ServiceList);
                    for (var j = 0; j < this.currLoadLayers.length; j++) {
                        this.map.removeLayer(this.currLoadLayers[j]);
                    }
                    layers[layers.length - 1].isBaseLayer = true;
                    this.map.addLayers(layers);
                    this.updateVectorlayerIndex();
                    this.updateOverViewControl();
                    this.currLoadLayers = layers;
                    this.loadScenarioEnd = true;
                    break;
                }
            }

        },
        removeLuWangData: function() {
            var that = this;

            var layers = that.map.getLayersByName(/^img_luwang_load[0-9]*$/g);
            _.forEach(layers, function (layer) {
                that.map.removeLayer(layer);
            });

        },
        loadLuWangData2: function() {
            var that = this;
            var cacheLayer = new OpenLayers.Layer.ESRICache("img_luwang_load0",
                "http://10.168.14.51:6080/arcgis/rest/services/NHImageRoadMap/MapServer",{
                    zoomOffset:0,
                    isBaseLayer:false,
                    //From layerInfo above
                    resolutions: [0.0010707574526236262,4.568565131194138E-4,2.284282565597069E-4,1.1421412827985346E-4
                    ,5.710706413992673E-5,2.8553532069963364E-5,1.4276766034981682E-5,7.138383017490841E-6,3.5691915087454205E-6
                    ,1.7845957543727096e-006],
                    tileSize: new OpenLayers.Size(256,256),
                    tileOrigin: new OpenLayers.LonLat( -400 , 400)
            });
            that.map.addLayer(cacheLayer);

        },
        /*loadLuWangData: function() {
            var that = this;
            //获取wmts能力描述信息
            var wmtsUrl = "http://127.0.0.1:8020/gisserver/gwc/service/wmts/leadorSpace:nanhaivector";
            function getRes(scales) {
                var res = [];
                for(var i = 0, j = scales.length; i < j;i++) {
                    res.push(that.map.pyramid.getResolutionForScale(parseFloat(scales[i])));
                }
                return res;
            }
            function getTileMatrixSetByMatrixSetName(content,identifier) {

                var tileMatrixSets = content.tileMatrixSets;
                var topleft = [];
                var result = {};
                var scales = [];
                for(var matrix in tileMatrixSets) {
                    if(matrix === identifier) {
                        result["matrixSet"] = identifier;
                        topleft.push(tileMatrixSets[matrix].matrixIds[0].topLeftCorner.lon,
                            tileMatrixSets[matrix].matrixIds[0].topLeftCorner.lat
                            );
                        result["zoomOffset"] = parseInt(tileMatrixSets[matrix].matrixIds[0].identifier);
                        result["matrixIds"] = tileMatrixSets[matrix].matrixIds;
                        result["tileOrigin"] = new OpenLayers.LonLat(topleft[0], topleft[1]);
                        for(var id = 0; id < tileMatrixSets[matrix].matrixIds.length;id++) {
                            scales.push(tileMatrixSets[matrix].matrixIds[id].scaleDenominator);
                        }
                    }
                }

                var res = getRes(scales);
                result["resolutions"] = res;
                return result;
            }
            var userInfo = this.userInfo;
            OpenLayers.Request.GET({
                url: wmtsUrl,
                params: {
                    SERVICE : "WMTS",
                    VERSION : "1.0.0",
                    REQUEST: "GetCapabilities"
                },
                success: function(request){
                    var format = new  OpenLayers.Format.WMTSCapabilities();
                    var doc = request.responseXML;
                    var docStr = request.responseText;
                    if (!doc || !doc.documentElement) {
                        doc = request.responseText;
                    }
                    try {
                        var jsonOnj = format.read(doc);
                    }
                    catch (e) {

                        return;
                    }

                    if(jsonOnj.contents === undefined) {

                        return;
                    }
                    var layersInfo =  jsonOnj.contents.layers;

                    var layers = [];
                    var Cfg = {};
                    for(var i = 0;i < layersInfo.length;i++) {
                        var result = getTileMatrixSetByMatrixSetName(jsonOnj.contents,layersInfo[i].tileMatrixSetLinks[0].tileMatrixSet);
                        var style = layersInfo[i]["styles"][0].identifier;
                        var layeridentifier = layersInfo[i].identifier;
                        var format = layersInfo[i].formats[0];
                        var bounds = layersInfo[i].bounds;
                        var zoomOffset = that.map.pyramid.getLevelForResolution(that.map.baseLayer.resolutions[0]);
                        //创建WMTS底图图层
                        var wmtsLayer = new OpenLayers.Layer.WMTS({
                            name : "img_luwang_load"+i,
                            layerid: new Date().getTime(),//时间戳
                            username: userInfo.loginName,//用户名
                            password: userInfo.passWord,//密码
                            url :  wmtsUrl,
                            matrixIds: result["matrixIds"],
                            version:"1.0.0",
                            //瓦片原点，计算行列号的起始位置，其值可以通过wmts服务的getCapabilities返回结果获得
                            tileOrigin:result.tileOrigin,
                            matrixSet : result.matrixSet,
                            style : style,
                            layer : layeridentifier,
                            resolutions: result.resolutions,
                            format : format,
                            isBaseLayer: false,
                           // zoomOffset: result.zoomOffset,
                            maxResolution :result.resolutions[0],//最大分辨率
                            minResolution :result.resolutions[result.resolutions.length - 1],//最小分辨率
                            tileFullExtent: bounds
                        });
                        //添加图层
                        that.map.addLayers([wmtsLayer]);
                    }

                },
                failure: function(){
                    OpenLayers.Console.error.apply(OpenLayers.Console, arguments);

                }
            });
        },*/
        /**
         * 更新矢量要素结果图层的图层顺序至地图最上
         */
        updateVectorlayerIndex: function () {
            var index = -1;
            for (var j = 0; j < this.map.layers.length; j++) {
                var _index = this.map.getLayerIndex(this.map.layers[j]);
                if (index < _index) {
                    index = _index;
                }
            }
            this.map.setLayerIndex(this.vectorlayer, index + 1);
        },

        /**
         * 更新鹰眼控件
         */
        updateOverViewControl: function () {
            var c = this.map.getControl("ol_control_overviewMap");
            if (c != null) {
                c.destroy();
            }
            var _layers = [];
            for (var layers = this.map.layers, i = layers.length - 1; i >= 0; i--) {
                if (layers[i].CLASS_NAME == "OpenLayers.Layer.WMTS"
                    || layers[i].CLASS_NAME == "OpenLayers.Layer.WMS"
                    || layers[i].CLASS_NAME == "OpenLayers.Layer.ESRICache"
                    || layers[i].CLASS_NAME == "OpenLayers.Layer.ArcGIS93Rest") {
                    _layers.push(this.map.layers[i].clone());
                }
            }
            if (_layers.length > 0) {
                _layers[0].setIsBaseLayer(true);
                var overviewMap = new OpenLayers.Control.OverviewMap({
                    id: "ol_control_overviewMap",
                    layers: _layers,
                    maximized: false
                });
                this.map.addControl(overviewMap);
            }
        },
        /**
         * 根据方案数据创建图层数组
         * @param list
         * @returns {Array}
         */
        createLayersByScenarioDataTest: function (list) {
            var layers = [];
            for (var i = 0; i < list.length; i++) {
                if (list[i].type === "LDWMTS") {
                    var layerArr = JSON.parse(list[i].capabilities).Layer;
                    for (var j = 0; j < layerArr.length; j++) {
                        layers.push(this.createWMTSLayer(list[i], layerArr[j]));
                    }
                }
            }
            return layers;
        },

        /**
         * 根据方案数据创建图层数组
         * @param list
         * @returns {Array}
         */
        createLayersByScenarioData: function (list) {
            var layers = [];
            for (var i = 0; i < list.length; i++) {
                if (list[i].type === "ArcGISCache") {
                    layers.push(this.createArcGISCacheLayer(list[i]));
                } else if (list[i].type === "WMTS") {
                    var layerArr = JSON.parse(list[i].capabilities).Layer;
                    for (var j = 0; j < layerArr.length; j++) {
                        layers.push(this.createWMTSLayer(list[i], layerArr[j]));
                    }
                } else if (list[i].type === "LDWMTS") {
                    var layerArr = JSON.parse(list[i].capabilities).Layer;
                    for (var j = 0; j < layerArr.length; j++) {
                        layers.push(this.createLDWMTSLayer(list[i], layerArr[j]));
                    }
                }
            }
            return layers;
        },

        /**
         * 创建ArcGISCache图层对象
         * @param data
         * @returns {*}
         */
        createArcGISCacheLayer: function (data) {
            var capa = JSON.parse(data.capabilities);
            var resolutions = [];
            for (var j in capa.tileInfo.lods) {
                resolutions.push(capa.tileInfo.lods[j].resolution);
            }
            var _opt = {
                tileSize: new OpenLayers.Size(capa.tileInfo.rows, capa.tileInfo.cols),
                resolutions: resolutions,
                maxResolution: resolutions[0],
                minResolution: resolutions[resolutions.length - 1],
                maxExtent: new OpenLayers.Bounds(capa.fullExtent.xmin, capa.fullExtent.ymin, capa.fullExtent.xmax, capa.fullExtent.ymax),
                tileOrigin: {
                    lon: capa.tileInfo.origin.x,
                    lat: capa.tileInfo.origin.y
                },
                layerInfo: {
                    fullExtent: {
                        xmin: capa.fullExtent.xmin,
                        ymin: capa.fullExtent.ymin,
                        xmax: capa.fullExtent.xmax,
                        ymax: capa.fullExtent.ymax
                    },
                    spatialReference: {
                        wkid: capa.fullExtent.spatialReference.wkid
                    }
                }
            };
            var layer = new OpenLayers.Layer.ESRICache(
                data.title,
                data.url,
                {
                    resolutions: resolutions,
                    maxExtent: [capa.fullExtent.xmin, capa.fullExtent.ymin, capa.fullExtent.xmax, capa.fullExtent.ymax],
                    tileOrigin: new OpenLayers.LonLat(capa.tileInfo.origin.x, capa.tileInfo.origin.y),
                    tileSize: new OpenLayers.Size(capa.tileInfo.rows, capa.tileInfo.cols),
                    opacity: capa.opacity,
                    isBaseLayer: false
                }
            );
            layer.isBaseLayer = false;
            return layer;
        },

        /**
         * 创建标准WMTS图层对象
         * @param data
         * @param layer
         * @returns {*}
         */
        createWMTSLayer: function (data, layer) {
            var scales = "", _matrixIds = [];
            $.each(layer.matrixIds, function (l, matrixId) {
                if (!matrixId.isHidden) {
                    if (l == layer.matrixIds.length - 1) {
                        scales += matrixId.scaleDenominator;
                    } else {
                        scales += matrixId.scaleDenominator + ",";
                    }
                    _matrixIds.push(matrixId);
                }
            });
            var resolutions = this.getRes(scales);
            var tileOrigin = layer.matrixIds[0].TopLeftCorner.split(" ");
            tileOrigin = new OpenLayers.LonLat(tileOrigin[1], tileOrigin[0]);
            var userInfo = this.userInfo|| { loginName: "test", password: "e10adc3949ba59abbe56e057f20f883e"};
            var wmtsLayer = new OpenLayers.Layer.WMTS({
                layerid: new Date().getTime(),//时间戳
                username: userInfo.loginName,//用户名
                password: userInfo.passWord,//密码
                name: layer.name,//服务名称
                url: data.url,//服务地址
                matrixSet: layer.matrixSet,//矩阵集名称
                matrixIds: _matrixIds,
                tileOrigin: tileOrigin,
                style: layer.style,//图层样式
                layer: layer.name,//图层标识
                resolutions: resolutions,//比例尺
                format: layer.format,//数据格式
                maxResolution: resolutions[0],//最大分辨率
                minResolution: resolutions[resolutions.length - 1],//最小分辨率
                zoomOffset: Number(_matrixIds[0].identifiers),//地图级别偏移
                tileSize: new OpenLayers.Size(layer.matrixIds[0].tileWidth, layer.matrixIds[0].tileHeight),
                isBaseLayer: false,
                opacity: layer.opacity
            });
            return wmtsLayer;
        },

        createLDWMTSLayer: function (data, layer) {
            var scales = "", _matrixIds = [], resolutions = [];
            $.each(layer.matrixIds, function (l, matrixId) {
                if (!matrixId.isHidden) {
                    if (l == layer.matrixIds.length - 1) {
                        scales += matrixId.scaleDenominator;
                    } else {
                        scales += matrixId.scaleDenominator + ",";
                    }
                    _matrixIds.push(matrixId);
                    resolutions.push(matrixId.Resolution)
                }
            });
            var tileOrigin = layer.matrixIds[0].TopLeftCorner.split(" ");
            tileOrigin = new OpenLayers.LonLat(tileOrigin[1], tileOrigin[0]);
            var userInfo = this.userInfo|| { loginName: "test", password: "e10adc3949ba59abbe56e057f20f883e"};
            var wmtsLayer = new OpenLayers.Layer.WMTS({
                layerid: new Date().getTime(),//时间戳
                username: userInfo.loginName,//用户名
                password: userInfo.passWord,//密码
                name: layer.name,//服务名称
                url: data.url,//服务地址
                matrixSet: layer.matrixSet,//矩阵集名称
                matrixIds: _matrixIds,
                tileOrigin: tileOrigin,
                style: layer.style,//图层样式
                layer: layer.name,//图层标识
                applicationName: "图释南海",
                resolutions: resolutions,//比例尺
                format: layer.format,//数据格式
                maxResolution: resolutions[0],//最大分辨率
                minResolution: resolutions[resolutions.length - 1],//最小分辨率
                zoomOffset: Number(_matrixIds[0].identifier),//地图级别偏移
                tileSize: new OpenLayers.Size(layer.matrixIds[0].tileWidth, layer.matrixIds[0].tileHeight),
                isBaseLayer: false,
                opacity: layer.opacity
            });
            return wmtsLayer;
        },

        /**
         * 根据比例尺数组返回分辨率数组
         * @param scales
         * @returns {Array}
         */
        getRes: function (scales) {
            var res = [];
            var scaleStr = scales.split(",");
            for (var i = 0, j = scaleStr.length; i < j; i++) {
                res.push(this.map.pyramid.getResolutionForScale(Number(scaleStr[i])));
            }
            return res;
        }
    })

    return ScenarioView;

});