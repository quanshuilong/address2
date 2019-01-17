
    var ol = window.ol;
    
    var poSvg = '<svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="36" height="48" viewBox="0 0 36 48">' +
        '<path d="M18 0c-9.8 0-17.7 7.8-17.7 17.4 0 15.5 17.7 30.6 17.7 30.6s17.7-15.4 17.7-30.6c0-9.6-7.9-17.4-17.7-17.4z" <!-- if: {{{on}}} == true -->fill="#f44336"<!-- else -->fill="#377CC8"<!-- /if -->></path> ' +
        '<text <!-- if: {{{value}}} < 10 -->x="14px"<!-- else -->x="8px"<!-- /if --> y="26px" class="a123" fill="#fff" font-size="20px">{{{value}}}</text> ' +
        '</svg>';

    var poiSvgStyle = function (value) {
        var mysvg = new Image();
        if(value.on) {
            mysvg.src ="img/red_poi.png";
        }else {
            mysvg.src ="img/blue_poi.png";
        }
         //'data:image/svg+xml,' + poiSvgRender(value);
        var icon = new ol.style.Icon({
            img: mysvg,
            imgSize: [48, 48]
        });
        //控制图标大小
       // icon.setScale(0.7);
        var style = new ol.style.Style({
            image:icon,
            text: new ol.style.Text({
                text: value.value+"",
                offsetX:-12,
                offsetY:-10,
                font:"14px sans-serif",
                fill: new ol.style.Fill({
                    color: '#fff'
                })
            })
        });
        return style;
    }
