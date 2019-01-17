
    var FeatureManager = function(map, vector) {
        this.map = map;
        this._typeMapping = {};
        var featuresLayer = vector;//map.getLayersById("AddFeaturedLayerVector_id");
        var sourceLayer = featuresLayer.getSource();
        this.sourceLayer = sourceLayer;
    };
    FeatureManager.prototype = {
        constructor: FeatureManager,
        dataTypes:[],//["keyWordQuery","classTypeQuery"];
        _typeMapping : null,
        sourceLayer: null,
        addFeatures: function(features,sort,isAppend) {
            var currentSort = sort || "default";
            this._typeMapping[currentSort] = features;
            if(!isAppend) {
                this._typeMapping[currentSort] = features;
                this.sourceLayer.addFeatures(features);
            }else {
                if(this._typeMapping[currentSort] && features) {
                    this._typeMapping[currentSort] = this._typeMapping[currentSort].concat(features);
                    this.sourceLayer.addFeatures(this._typeMapping[currentSort]);
                }
            }
           // this.sourceLayer.addFeatures(features);
        },
        clearFeatureFromSource: function(sort) {
            var that = this;
           // var collection = this.sourceLayer.getFeaturesCollection();
            var currentSort = sort || "default";
            var features = this._typeMapping[currentSort];
            _.forEach(features,function(feature) {
                that.sourceLayer.removeFeature(feature);
            });
            this._typeMapping[currentSort] = null;
        },
        removeFeature: function(feature) {
            this.sourceLayer.removeFeature(feature);
        },
        clearAll: function() {
            this.sourceLayer.clear();
            for(var type in this._typeMapping) {
                this._typeMapping[type] = [];
            }
        }
    };
