Ext.require('manageApp.utils.Model');
Ext.define('manageApp.base.model.User',
    {
        extend : 'Ext.data.Model',
        idProperty:'id',
        fields: [
            { name: 'id', type: 'int', defaultValue: 0 },
            { name: 'userName', type: 'string'},//用户名
            { name: 'alias', type: 'string'},//用户别名
            { name: 'passWord', type: 'string'},//密码
            { name: 'email', type: 'string'},//邮箱
            { name: 'sex', type: 'string'},//性别
            { name: 'depart', type: 'string'},//部门
            { name: 'role', type: 'int'},//角色
            { name: 'roles', type: 'string'},//角色
            { name: 'state', type: 'int'},//状态
            { name: 'sysType', type: 'int'},//系统类型
            { name: 'createTime', type: 'date'},//创建时间
            { name: 'createTimeBegin', type: 'date'},//
            { name: 'createTimeEnd', type: 'date'},//
            { name: 'createUser', type: 'string' },//创建人
            { name: 'modifTime', type: 'date'},//修改时间
            { name: 'modifTimeBegin', type: 'date'},//
            { name: 'modifTimeEnd', type: 'date'},//
            { name: 'modifUser',  type: 'string' }//修改人
        ],
//    validations : [{
//        type : 'presence',
//        field : 'firstName'
//    } ],
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
                        read : '../base/user/findPage.do',//load
                        create : '../base/user/save.do',//save
                        update : '../base/user/save.do',//update
                        destroy : '../base/user/delete.do'//remove
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