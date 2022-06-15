Ext.define('manageApp.ebusiness.store.Orders',
{
    extend: 'Ext.data.Store',
    model: 'manageApp.ebusiness.model.Order',
    autoLoad: false,
    remoteSort:true,
    pageSize:50,
    proxy :
    {
        type : 'ajax',
        reader :
        {
    	    type : 'json',
        	root:'data',
            totalProperty:'total'
        },
        api :
        {
            read : '../ebusiness/order/findPage.do',
            create : '../ebusiness/order/create.do',
            update : '../ebusiness/order/update.do',
            destroy : '../ebusiness/order/delete.do'
        },
        actionMethods :
        {
            read : 'GET',
            create : 'POST',
            update : 'POST',
            destroy : 'POST'
        },
        writer:{
        	type:'json',
        	allowSingle:false
        }
    }
});
