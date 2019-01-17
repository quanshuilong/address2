//define([], function(){
var basemapconfig = [
    {
        url: "http://192.168.11.58:6080/arcgis/rest/services/zhuhai/zhuhai_tc/MapServer",
        projection: 'EPSG:4326',
        origin: [-180.0, 90.0],
        resolutions: [0.9410711005830282, 0.7031250000000002, 0.3515625000000001, 0.17578125000000006, 0.08789062500000003, 0.043945312500000014, 0.021972656250000007, 0.010986328125000003, 0.005493164062500002,
            0.002746582031250001, 0.0013732910156250004, 6.866455078125002E-4, 3.433227539062501E-4, 1.7166137695312505E-4, 8.583068847656253E-5, 4.2915344238281264E-5, 2.1457672119140632E-5, 1.0728836059570316E-5,
            5.364418029785158E-6, 2.682209014892579E-6]
    }
];

var center_zoom = {
    "section": {//地图基本操作功能
        center: [113.57734, 22.27068],
        zoom: 12
    },
    "AddPopup": {
        center: [116.4750, 39.8979],
        zoom: 5
    },
    "Service": {
        center: [113.5968, 22.2832],
        zoom: 12
    }
};

basemapconfig[0]["section"] = center_zoom.section;
var baseMap = {
    map: null,
    vector: null,
    initMap: function(options){
        this.map = new ol.Map({
            projection: basemapconfig[0].projection,
            target: options["el"]|| "simpleMap",
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.ESRICache({
                        origin: basemapconfig[0].origin,
                        resolutions: basemapconfig[0].resolutions,
                        projection: basemapconfig[0].projection,
                        url: basemapconfig[0].url + '/tile/{z}/{y}/{x}'
                    })
                })
            ],
            view: new ol.View({
                projection: basemapconfig[0].projection,
                resolutions: basemapconfig[0].resolutions,
                center: basemapconfig[0].section.center,//ol.proj.transform([/*94.65424626536483, 40.14363363555644*/114.34, 30.55], 'EPSG:4326', 'EPSG:3857'),
                zoom: basemapconfig[0].section.zoom
            })
        });
        var source = this.source = new ol.source.Vector({wrapX: false});
        var vector = this.vector = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#ffcc33',
                    width: 2
                }),
                /*image: new ol.style.Circle({
                 radius: 7,
                 fill: new ol.style.Fill({
                 color: '#ffcc33'
                 })
                 })*/
                image: new ol.style.Icon(({
                    src: 'dist/img/marker/poi_blue.png'
                }))
            })
        });
        this.map.addLayer(vector);
        this.addControl();
        this.addInteraction();
    },
    addControl: function(){
        var zoomslider = new ol.control.ZoomSlider();
        this.map.addControl(zoomslider);
    },
    addInteraction: function(){
        var selectSingleClick = this.selectSingleClick = new ol.interaction.Select({
            layers: [this.vector],
            multi: false // multi is used in this example if hitTolerance > 0
        });
        var draw = this.draw = new ol.interaction.Draw({
            source: this.vector.getSource(),
            type: "Point",
            style: poiSvgStyle({value: "" })
        });

        this.map.addInteraction(draw);
        this.draw.setActive(false);
        this.map.addInteraction(selectSingleClick);
    }
};