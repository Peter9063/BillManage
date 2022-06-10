Ext.require('manageApp.utils.Model');
Ext.define('manageApp.base.model.PCode',
    {
        extend: 'Ext.data.Model',
        fields: ['menuId', 'name'],
        proxy: {
            type:"ajax",
            // url: "./resources/pCode.json",
            // url: "../base/menu/findMenu",
            reader: {
                type: "json",
                root: "data"
            },

            api :
                {
                    read : '../base/menu/findMenu',//load
                    create : '../base',//save
                    update : './resource',//update
                    destroy : '../base'//remove
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

    }
);