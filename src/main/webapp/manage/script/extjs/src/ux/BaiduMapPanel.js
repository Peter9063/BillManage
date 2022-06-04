Ext.define('Ext.ux.BaiduMapPanel', {
    extend: 'Ext.Panel',

    alias: 'widget.baiduMapPanel',

    requires: ['Ext.window.MessageBox','manageApp.forum.model.ConcentratorReadingPeriod'],
    
    tools:{},

    initComponent: function () {
        Ext.applyIf(this.bmap, this.config);
        this.callParent();
    },
    afterRender: function () {
    	/**
    	 * 百度地图控制渲染需要有一个元素支点。
    	 * 所以在extjs控件呈现后，执行是非常合理的时机。
    	 */
        //设置panel属性
        var elWidthHeight = this.ownerCt.getSize();
        Ext.applyIf(this, elWidthHeight);
        this.callParent();

        //设置百度地图属性
        if (this.bmapType === 'map') {
            this.bmap = new BMap.Map(this.body.dom);
        }

        if (this.bmapType === 'map') {
            this.bmap.centerAndZoom(this.centerCity, this.zoomLevel); //设置初始化中心点
        }

        this.onMapReady(); //地图加载项
    },
    onMapReady: function () {
        this.addMapConfigs(); //添加地图属性
        this.addMapControls(); //添加地图控件
        this.addMapTools();
        this.addMapMarkers(this.markers); //添加标记
    },
    addMapTools:function () {
        if (Ext.isArray(this.toolsConfigs)) {
            for (var i = 0; i < this.toolsConfigs.length; i++) {
                this.addMapTool(this.toolsConfigs[i]);
            }
        } else if (typeof this.toolsConfigs === 'string') {
            this.addMapTool(this.toolsConfigs);
        }
    },
    addMapTool:function (configParam) {
    	var me=this;
    	if(configParam=='DistanceTool'){//测距控件
    		this.tools['DistanceTool']=new BMapLib.DistanceTool(this.bmap);
    	}
    	if(configParam=='MarkerTool'){//marker点控件
    		this.tools['MarkerTool']= new BMapLib.MarkerTool(this.bmap, {autoClose: true});
    		this.tools['MarkerTool'].addEventListener("markend", function(evt){ 
    			var marker = evt.marker;
    			marker.enableDragging();
    			

    			
    			me.addMarkerMouseupEven(marker,me.getMap());
    			me.addMarkerDblclickEven(marker,me.getMap());
    			
    			//console.log('x:'+curMkr.Bc['offsetLeft']+" ; y:"+curMkr.Bc['offsetTop']);
    			var obj={lng:marker.point.lng,lat:marker.point.lat}
    			var record =Ext.create('manageApp.forum.model.ConcentratorReadingPeriod',obj)
    			Ext.create('manageApp.forum.view.concentratorPointDistance.ConcentratorReadingPeriod4MapFormWin',{
    				title:'新增',
    				isSave:true,
    				record:record,
    				marker:marker,
    				map:me
    			});
    		});
    	}
    },
    addMapConfigs: function () {
        if (Ext.isArray(this.mapConfigs)) {
            for (var i = 0; i < this.mapConfigs.length; i++) {
                this.addMapConfig(this.mapConfigs[i]);
            }
        } else if (typeof this.mapConfigs === 'string') {
            this.addMapConfig(this.mapConfigs);
        }
    },
    addMapConfig: function (configParam) {
        this.getMap()[configParam]();
    },
    addMapControls: function () {
        //debugger
        if (Ext.isArray(this.mapControls)) {
            for (var i = 0; i < this.mapControls.length; i++) {
                this.addMapControl(this.mapControls[i]);
            }
        }
    },
    addMapControl: function (controlParam) {
//       debugger
       var controlBase = new BMap[controlParam.controlName](controlParam);
       this.getMap().addControl(controlBase);
    },
    addMapMarkers: function (markerArray) {
        if (Ext.isArray(markerArray)) {
            for (var i = 0; i < markerArray.length; i++) {
                this.addMapMarker(markerArray[i]);
            }
        }
    },
    addMapMarker: function (markerParam) {
        var point = new BMap.Point(markerParam.x, markerParam.y); //创建图点
        var markerBase = new BMap.Marker(point); //创建标记

        if (markerParam.isDragging == true)
            markerBase.enableDragging();
        else
            markerBase.disableDragging();

        this.getMap().addOverlay(markerBase); //标记覆盖入地图
    },
    getMap: function () {
        return this.bmap;
    },
    addMarkerMouseupEven: function (marker){}
    ,
    addMarkerDblclickEven: function (marker){}
    ,
    addMarkerClickEven: function (marker){}
});