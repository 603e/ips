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

 <!--
    <script src='https://api.tiles.mapbox.com/mapbox-gl-js/v0.28.0/mapbox-gl.js'></script>
    <link href='https://api.tiles.mapbox.com/mapbox-gl-js/v0.28.0/mapbox-gl.css' rel='stylesheet' />
 -->
     <style>
        body { margin:0; padding:0; }
        #map { position:absolute; top:0; bottom:0; width:99%; float:left; }
        #control { position: absolute; z-index: 1; top: 420px; right: 550px; }
    </style>
     <style>
    #floorCtl {
        background: #fff;
        position: absolute;
        z-index: 1;
        top: 300px;
        right: 30px;
        border-radius: 3px;
        width: 30px;
        border: 1px solid rgba(0,0,0,0.4);
        font-family: 'Open Sans', sans-serif;
    }

    #floorCtl a {
        font-size: 13px;
        color: #404040;
        display: block;
        margin: 0;
        padding: 0;
        padding: 10px;
        text-decoration: none;
        border-bottom: 1px solid rgba(0,0,0,0.25);
        text-align: center;
    }

    #floorCtl a:last-child {
        border: none;
    }

    #floorCtl a:hover {
        background-color: #f8f8f8;
        color: #404040;
    }

    #floorCtl a.active {
        background-color: #3887be;
        color: #ffffff;
    }

    #floorCtl a.active:hover {
        background: #3074a4;
    }
</style>
</head>
<body>

<div id='map'></div>
<div id='control'>
	<img class="controlImg hpn-icon-turnRight" onclick="turnRightFun();"/>
	<img class="controlImg hpn-icon-turnLeft" onclick="turnLeftFun();"/>
</div>
<nav id="floorCtl"></nav>
<script>

var floorIds = [ '1', '2', '3', '4' ];

for (var i = 0; i < floorIds.length; i++) {
    var id = floorIds[i];

    var link = document.createElement('a');
    link.href = '#';
    link.className = 'active';
    link.textContent = id;

    link.onclick = function (e) {
        var clickedFloor = this.textContent;
        showFloorMap(clickedFloor);
    };

    var floors = document.getElementById('floorCtl');
    floors.appendChild(link);
}

mapboxgl.accessToken = 'pk.eyJ1IjoibWFwZXIiLCJhIjoiY2l3dm9qdzRiMDAxMTJ6cGY2ZHlzOTRvNCJ9.WcJV0GCgk_4XXHa8cnmi_Q';
var map;	
var bearing = 90;
//mapboxgl.accessToken = 'pk.eyJ1IjoiNjAzIiwiYSI6ImNpdnRlaWo1NzA0djIydHIzbTV6eDA2bWYifQ.siYI7FbEz3ZaWuU8-4PJGw';
//var styleLocation = 'mapbox://styles/maper/ciwvpz28c002z2qpqxdg2m5cy';
	//styleLocation = 'mapbox://styles/maper/cizfl4jyx007m2sji1ndyc4nl';
	//styleLocation = '../../../json/mapboxData0306.json'
	
	var showMap = function(styleLocation,centerPoint,zoom){
		map = new mapboxgl.Map({
		    container: 'map', // container id
		    style: styleLocation, //stylesheet location
		    center: centerPoint, // starting position
		    zoom: zoom, // starting zoom
		    bearing:bearing,
		    attributionControl:true
		});
		map.addControl(new mapboxgl.NavigationControl());	

		map.on('load', function() {

		    // Add a new source from our GeoJSON data and set the
		    // 'cluster' option to true.
		    map.addSource("earthquakes", {
		        type: "geojson",
		        // Point to GeoJSON data. This example visualizes all M1.0+ earthquakes
		        // from 12/22/15 to 1/21/16 as logged by USGS' Earthquake hazards program.
		        data: '../../../json/earthquakes.geojson',
		        cluster: true,
		        clusterMaxZoom: 15, // Max zoom to cluster points on
		        clusterRadius: 20 // Use small cluster radius for the heatmap look
		    });

		    // Use the earthquakes source to create four layers:
		    // three for each cluster category, and one for unclustered points

		    // Each point range gets a different fill color.
		    var layers = [
		        [0, 'green'],
		        [20, 'orange'],
		        [200, 'red']
		    ];

		    layers.forEach(function (layer, i) {
		        map.addLayer({
		            "id": "cluster-" + i,
		            "type": "circle",
		            "source": "earthquakes",
		            "paint": {
		                "circle-color": layer[1],
		                "circle-radius": 70,
		                "circle-blur": 1 // blur the circles to get a heatmap look
		            },
		            "filter": i === layers.length - 1 ?
		                [">=", "point_count", layer[0]] :
		                ["all",
		                    [">=", "point_count", layer[0]],
		                    ["<", "point_count", layers[i + 1][0]]]
		        }, 'waterway-label');
		    });

		    map.addLayer({
		        "id": "unclustered-points",
		        "type": "circle",
		        "source": "earthquakes",
		        "paint": {
		            "circle-color": 'rgba(0,255,0,0.5)',
		            "circle-radius": 20,
		            "circle-blur": 1
		        },
		        "filter": ["!=", "cluster", true]
		    }, 'waterway-label');
		});
	}

var showFloorMap = function(n) {
	var styleLocation = "mapbox://styles/mapbox/streets-v9";
	var centerPoint = [112.520855,-0.008069];
	var zoom=10;
	if(n==1){
		styleLocation = 'mapbox://styles/maper/ciwvpz28c002z2qpqxdg2m5cy';
		centerPoint = [112.520855,-0.008069];
		zoom=10;
		bearing = 90;
	}else if(n==2){
		styleLocation = 'mapbox://styles/maper/cizfl4jyx007m2sji1ndyc4nl';
		centerPoint = [112.520855,-0.008069];
		zoom=10;
		bearing = 90;
	}else if(n==3){
		styleLocation = 'mapbox://styles/mapbox/streets-v9';
		centerPoint = [-103.59179687498357, 40.66995747013945];
		zoom=3;
		bearing = 0;
	}else if(n==4){
		styleLocation = 'mapbox://styles/mapbox/dark-v9';
		centerPoint = [-103.59179687498357, 40.66995747013945];
		zoom=3;
		bearing = 0;
	}
	showMap(styleLocation,centerPoint,zoom);
}
 
var turnRightFun = function() {
	bearing = bearing-90;
	map.setBearing(bearing);
}
var turnLeftFun = function() {
	bearing = bearing+90;
	map.setBearing(bearing);
}
</script>

</body>
</html>
