Ext.define('manageApp.utils.Ajax', {
     statics: {  
    	 request: function(url,method,params,callback) {
 			Ext.Ajax.request({
			    url: url,
			    method: method,
			    params: params,
			    timeout: 60000,
			    callback:callback,
			    success: function(response,options){
			    	var responseJson=Ext.JSON.decode(response.responseText);
			    	if(responseJson.success==true){
			    		Ext.MessageBox.alert('成功', responseJson.msg);
			    	}
			    	else{
			    		Ext.MessageBox.alert('失败', responseJson.msg);
			    	}
			    },
			    failure:function(response,options){
		    		var msg='网络故障';
		    		if(response.responseText!=''){
		    			var responseJson=Ext.JSON.decode(response.responseText);
		    			msg=responseJson.msg;
		    		}
		    		Ext.MessageBox.alert('失败', msg);
			    }
			});
         } , 
     	 request2: function(url,method,params,callback) {
 			Ext.Ajax.request({
			    url: url,
			    method: method,
			    params: params,
			    timeout: 60000,
			    callback:callback,
			    success: function(response,options){
//			    	var responseJson=Ext.JSON.decode(response.responseText);
//			    	if(responseJson.success==true){
//			    		Ext.MessageBox.alert('成功', responseJson.msg);
//			    	}
//			    	else{
//			    		Ext.MessageBox.alert('失败', responseJson.msg);
//			    	}
			    },
			    failure:function(response,options){
		    		var msg='网络故障';
		    		if(response.responseText!=''){
		    			var responseJson=Ext.JSON.decode(response.responseText);
		    			msg=responseJson.msg;
		    		}
		    		Ext.MessageBox.alert('失败', msg);
			    }
			});
         }
     }
});