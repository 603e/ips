<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="zone.framework.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8' />
    <title></title>
    <meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no' />
    	<%-- 引入扩展图标 --%>
	<link rel="stylesheet" href="<%=contextPath%>/style/syExtIcon.css" type="text/css"> 
 	<link rel="stylesheet" href="<%=contextPath%>/js/mapbox-gl/mapbox-gl_v0.32.1.css" type="text/css">
	<script type="text/javascript" src="<%=contextPath%>/js/mapbox-gl/mapbox-gl_v0.32.1.js" charset="utf-8"></script>
    
 <!--    <script src='https://api.tiles.mapbox.com/mapbox-gl-js/v0.37.0/mapbox-gl.js'></script>
    <link href='https://api.tiles.mapbox.com/mapbox-gl-js/v0.37.0/mapbox-gl.css' rel='stylesheet' /> -->
    <style>
        body { margin:0; padding:0; }
        #map { position:absolute; top:0; bottom:0; width:100%; }
        #info {
        display: block;
        position: relative;
        margin: 0px auto;
        width: 50%;
        padding: 10px;
        border: none;
        border-radius: 3px;
        font-size: 12px;
        text-align: center;
        color: #222;
        background: #fff;
    }
    </style>
</head>
<body>

<div id='map'></div>
<!-- <pre id='info'></pre> -->
<script>
mapboxgl.accessToken = 'pk.eyJ1IjoibWFwZXIiLCJhIjoiY2l3dm9qdzRiMDAxMTJ6cGY2ZHlzOTRvNCJ9.WcJV0GCgk_4XXHa8cnmi_Q';
var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/maper/ciwvpz28c002z2qpqxdg2m5cy',
    center: [116.420298, 39.947635],
    zoom: 20.5
});

map.on('mousemove', function (e) {
    document.getElementById('info').innerHTML =
        JSON.stringify(e.point) + '<br />' +
        JSON.stringify(e.lngLat)+ '<br />' +
        JSON.stringify(map.getZoom());
});

map.on('load', function() {
    map.addSource("customerPositions", {
        type: "geojson",
        data: "../../../json/heatMap.geojson",
        cluster: true,
        clusterMaxZoom: 30, // Max zoom to cluster points on
        clusterRadius: 2 // Radius of each cluster when clustering points (defaults to 50)
    });

    map.addLayer({
        id: "clusters",
        type: "circle",
        source: "customerPositions",
        filter: ["has", "point_count"],
        paint: {
            "circle-color": {
                property: "point_count",
                type: "interval",
                stops: [
                	[0, "#008000"],
                    [10, "#ffff00"],
                    [150, "#ff0000"],
                ]
            },
            "circle-radius": {
                property: "point_count",
                type: "interval",
                stops: [
                	[0, 10],
                    [10, 15],
                    [150, 30]
                ]
            }
        }
    });

    map.addLayer({
        id: "cluster-count",
        type: "symbol",
        source: "customerPositions",
        filter: ["has", "point_count"],
        layout: {
            "text-field": "{point_count_abbreviated}",
            "text-font": ["DIN Offc Pro Medium", "Arial Unicode MS Bold"],
            "text-size": 12
        }
    });

    map.addLayer({
        id: "unclustered-point",
        type: "circle",
        source: "customerPositions",
        filter: ["!has", "point_count"],
        paint: {
            "circle-color": "#11b4da",
            "circle-radius": 3,
            "circle-stroke-width": 1,
            "circle-stroke-color": "#fff"
        }
    });
});
</script>

</body>
</html>
