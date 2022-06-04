Ext.require('manageApp.utils.Model');
Ext.define('manageApp.base.model.Menu',
    {
        extend: 'Ext.data.Model',
        idProperty: 'id',
        fields: [
            { name: 'menuId', type: 'Integer' },//菜单id
            { name: 'code',  type: 'string'},//菜单编号
            { name: 'pId',  type: 'Integer'},//菜单父id
            { name: 'name',  type: 'String'},//菜单名称
            { name: 'url',  type: 'String'},//url
            { name: 'sort',  type: 'Integer'},//菜单排序号
            { name: 'levels',  type: 'Integer'},//菜单层级
            { name: 'status',  type: 'String'},//菜单状态
            {name: 'menuFlag' ,  type: 'String'},//是否菜单
            { name: 'createTime',  type: 'Date'},//创建时间
            { name: 'updateTime',  type: 'Date'},//修改时间
            { name: 'createUser',  type: 'string'},//创建人
            { name: 'updateUser',  type: 'string'}//修改人

        ],
        proxy :
            {
                type : 'ajax',
                reader :
                    {
                        type : 'json',
                        root:'',
                        totalProperty:'total'
                    },
                api :
                    {
                        read:'../base/menu/findAllMenu',
                        // read:'../base/menu/findMenu',//不用了
                        // read : './resources/test.json',//load
                        create : '../base/menu/insertMenu',//   save
                        update : '../base/menu/updateMenu',//update
                        destroy : '../base/menu/deleteMenu'//remove
                        // destroy : '../ceshi'//remove
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