/**
 * create by gaoyi
 * create time 2016-12-21
 */
//define(['map',"jquery","base"], function(map,jquery,base) {

var popupManager = {
    popup: null,
    init: function(options){
        var map = options["map"];
        var container = document.querySelector('#popup12');
        var markAddContainer = document.querySelector("#popupMarkAdd12");

        var selectFeatureInteraction;
        //由具体类实现
        ol.Overlay.prototype.showPopup = function(feature,callback) {
            var popup = this;
            callback(feature);
            var coordinate = feature.getGeometry().getCoordinates();
            // console.log(coordinate);
            popup.setPosition(coordinate); //设置popup的位置
            popup.setOffset([-155, 0-($(popup.getElement()).height() + 27 + 15)]);
        }

        ol.Overlay.prototype.clickPoiCallback = function(evt) {
            var feature = evt.selected[0];
            if (feature) {
                this.showPopup(feature);
            }
        };

        var popup = this.popup =  new ol.Overlay(({
            id:"poiPopup",
            element: container,
            autoPan: true,
            stopEvent: true,
            autoPanAnimation: {
                duration: 250
            }
        }));

        map.addOverlay(popup);
        var markAddPopup = new ol.Overlay(({
            id:"markAddPopup",
            element: markAddContainer,
            autoPan: true,
            stopEvent: true,
            autoPanAnimation: {
                duration: 50
            }
        }));

        map.addOverlay(markAddPopup);
        $(".popup-close").click(function() {
            popup.setPosition(undefined);
            //TODO:
            markAddPopup.setPosition(undefined);
            //TODO:
            //清除选择框中多余的要素
            map.selectSingleClick.getFeatures().clear();
        });

        var basePopupInfo = function() {

        }

        basePopupInfo.prototype.showPopup = function(feature) {
            var pros = feature.getProperties();
            if(pros.f) {

            }
        };
        return this.popup;
    }
};
