Ext.define('manageApp.base.controller.main.MainFrame',{
	extend: 'Ext.app.Controller',
	stores:[
		'manageApp.base.store.Navigator'
	],
	views:[
		'manageApp.base.view.main.MainFrame'
	],
	refs:[
		{
        	ref: 'baseViewNavigatorFrame',
        	selector: 'baseViewNavigatorFrame'
		},
		{
        	ref: 'baseViewCentFrame',
        	selector: 'baseViewCentFrame'
		}
		
	],

	init: function(application){
		
		this.control({
			'baseViewNavigatorFrame treepanel':{
				itemclick: this.onNaviItemClick
			}
		});
		
	},
	onLaunch:function(application){
		//******引导菜单数据初始化  开始***************************************************
		var naviStore = this.getStore('manageApp.base.store.Navigator');
        naviStore.on({
        	beforeload: Ext.bind(function(){this.body.mask(Ext.view.AbstractView.prototype.loadingText);},
        						 this.getBaseViewNavigatorFrame()),
        	load: Ext.bind(this.navigatorBudding,this)
        });
        naviStore.load();
        //******引导菜单数据初始化  结束***************************************************
		
	},
	/**
	 * 引导菜单构建
	 * @param {} store
	 */
	navigatorBudding: function(store) {
	   var viewNavigatorFrame=this.getBaseViewNavigatorFrame();
	
       viewNavigatorFrame.suspendLayouts();
       viewNavigatorFrame.removeAll(false);
       store.getRootNode().eachChild(function(child) {
        	viewNavigatorFrame.add(Ext.widget('treepanel', {
                title: child.get('text'),
                autoScroll: true,
                rootVisible: false,
                root: child.data
            }));
        });
       viewNavigatorFrame.resumeLayouts(true);
       viewNavigatorFrame.body.unmask();
    },
    /**
     * 菜单页面跳转
     * @param {} tree
     * @param {} record
     * @param {} dom
     * @param {} index
     * @param {} eOpts
     */
    onNaviItemClick:function(tree, record, dom, index, eOpts){
    	
    	if (record.raw['url']!=null){
    		
    		
    		var navigatorRecord={"controller":'manageApp.base.controller.test.Test',
    							 'view':'manageApp.base.view.test.Test',
    							 'params':null,
    							 'title':'test1',
					             'closable': true,
						         'autoDestroy': true
    							 };
    		
    	  //URL参数解析
    	   var urls=record.raw['url'].split('?');
    	   var urlStr='';
    	   var params={};
    	   if(urls.length>1){
    		   urlStr=urls[0];
    		   var tempParams=urls[1].split('&');
    		   for(var i=0;i<tempParams.length;i++){
    			   var KeyValue=tempParams[i].split('=');
    			   params[KeyValue[0]]=KeyValue[1];
    		   }
    	   }
    	   else{
    		   urlStr=record.raw['url'];
    		   params=null;
    	   }
//    	   console.log('url:'+urlStr);
//    	   console.log('params:'+params);
//    	   console.log('params2:'+Ext.JSON.encode(params));
    		
    		navigatorRecord.title=record.raw['text'];
    		navigatorRecord.controller=urlStr;
    		navigatorRecord.view=urlStr.replace('.controller.','.view.');
    		navigatorRecord.params=params;
    		
    		Ext.require(navigatorRecord.controller, function(){
  				this.getController(navigatorRecord.controller);
  				var viewCmp=this.getBaseViewCentFrame().down('[title='+navigatorRecord.title+']');
  				
  				if(!viewCmp){
  					viewCmp=this.getBaseViewCentFrame().add(Ext.create(navigatorRecord.view, navigatorRecord));
  				}
  				
  				this.getBaseViewCentFrame().setActiveTab(viewCmp);
    		},this);
    		
    	}
    	
    },
    	/**
	 * 页面跳转
	 * @param {} param
	 */
	jumpPage:function(title,url,params){
		
		var navigatorRecord={"controller":'manageApp.base.controller.Test',
					 'view':'manageApp.base.view.Test',
					 'params':null,
					 'title':'test1',
		             'closable': true,
			         'autoDestroy': true
					 };
							 
		navigatorRecord.title=title;
		navigatorRecord.controller=url;
		navigatorRecord.view=url.replace('.controller.','.view.');
		navigatorRecord.params=params;
		
		Ext.require(navigatorRecord.controller, function(){
			this.getController(navigatorRecord.controller);
			var viewCmp=this.getBaseViewCentFrame().down('[title='+navigatorRecord.title+']');
			
			if(!viewCmp){
				viewCmp=this.getBaseViewCentFrame().add(Ext.create(navigatorRecord.view, navigatorRecord));
			}
			else{
				viewCmp.close();
				viewCmp=this.getBaseViewCentFrame().add(Ext.create(navigatorRecord.view, navigatorRecord));
			}
			this.getBaseViewCentFrame().setActiveTab(viewCmp);
		},this);
	}
});