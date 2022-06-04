Ext.define('manageApp.utils.Form', {
     statics: {  
    	 getNotNullVal: function(baseForm) {
    	 		var values = baseForm.getValues();
				var commitParam = {};
				for (var name in values) {
					if (values[name] != undefined && values[name] != '') {
						commitParam[name] = values[name];
					}
				}
				return commitParam;
         },
         getCommitParam:function(baseForm,changFn){
         	var commitParam=this.getNotNullVal(baseForm);
         	if(changFn!=null && typeof(changFn)===Function){
         		commitParam=changFn(commitParam);
         	}
         	return commitParam;
         }
     }
});