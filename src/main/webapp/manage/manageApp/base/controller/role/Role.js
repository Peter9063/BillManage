Ext.define('manageApp.base.controller.role.Role', {
    extend: 'Ext.app.Controller',
    stores: ['manageApp.base.store.Roles'],
    views: ['manageApp.base.view.role.Role'
    ],

    refs:[
        {
            ref: 'searchBar',
            selector: 'baseViewRole form[name=searchBar]'
        },
        {
            ref: 'grid',
            selector: 'baseViewRole grid[name=grid]'
        }
    ],
    init: function(application) {
        console.log("init");
        this.gridStore = this.getStore('manageApp.base.store.Roles');

        this.control({
            'baseViewRole grid button[name=butAdd]':{
                click: this.saveClick
            },
            'baseViewRole grid button[name=butEdit]':{
                click: this.editClick
            },
            'baseViewRole grid button[name=butDel]':{
                click: this.delClick
            },
            'baseViewRole grid button[name=butPermission]':{
                click:this.permissionClick
            },
            'baseViewRole grid button[name=butUserDistribution]':{
                click:this.userDistributionClick
            },
            'baseViewRole form button[name=butFind]':{
                click: this.findClick,
                afterrender: this.findClick
            },
            'baseViewRole form button[name=butReset]':{
                click: this.resetClick
            },
            'baseViewRoleFormWin button[name=btnWinFormSave]':{
                click:this.winFromSave
            },
            'baseViewRoleFormWin button[name=btnWinFormModif]':{
                click:this.winFormModif
            },
            'baseViewRolePermissionWin button[name=btnWinPermissionSave]':{
                click:this.WinPermissionSave
            }

        });
    },

    onLaunch:function(application){
        console.log("onLaunch");
        this.loadGridDate();
//		添加
//		修改

    },
    /**
     * 新增点击事件
     */
    saveClick:function(){
        console.log('saveClick');
        this.formWin=Ext.create('manageApp.base.view.role.RoleFormWin',{
            title:'新增',
            isSave:true

        });
    },
    /**
     * 修改点击事件
     */
    editClick:function(){
        console.log('editClick');

        var selectedRows=this.getGrid().getSelectionModel().getSelection();
        console.log(selectedRows+"selectedRows");
        if(selectedRows.length==1){
            console.log(selectedRows[0]);
            this.formWin=Ext.create('manageApp.base.view.role.RoleFormWin',{
                title:'修改',
                isSave:false,
                record:selectedRows[0]
            });
        }
        else{
            Ext.Msg.alert('Status', '只能选择一条记录进行修改操作!');
        }
    },
    /**
     * 用户分配按钮点击事件
     */
    userDistributionClick : function(){
        console.log("权限按钮点击事件");
        var mainPanel=this.getController('manageApp.base.controller.main.MainFrame');
        var selectedRows=this.getGrid().getSelectionModel().getSelection();
        if(selectedRows.length==1) {
            var roleId=selectedRows[0].get("roleId");
            var roleName=selectedRows[0].get("name");
            var url='manageApp.base.controller.role.RoleAndUserDistribution';
            mainPanel.jumpPage('角色 - 用户分配',url,{'roleId':roleId,'roleName':roleName});
        }else{
            Ext.Msg.alert('Status', '只能选择一条记录进行修改操作!');
        }
    },

    /**
     * 权限按钮点击事件
     */
    permissionClick: function(){

        var mainPanel=this.getController('manageApp.base.controller.main.MainFrame');
        var selectedRows=this.getGrid().getSelectionModel().getSelection();

        if(selectedRows.length==1) {

            var roleId=selectedRows[0].get("roleId");
            var roleName=selectedRows[0].get("name");
            var url='manageApp.base.controller.role.RolePermission';
            mainPanel.jumpPage('角色资源分配',url,{'roleId':roleId,'roleName':roleName});

            // this.formWin = Ext.create('manageApp.base.view.role.RolePermissionWin', {
            //     title: '权限分配',
            //     isSave: true,
            //     roleId:selectedRows[0].get('roleId')
            // });
        }else{
            Ext.Msg.alert('Status', '只能选择一条记录进行修改操作!');

        }
    },
    /**
     * 删除点击事件
     */
    delClick:function(){
        var  me=this;
        var selectedRows=this.getGrid().getSelectionModel().getSelection();
        if(selectedRows.length<1){
            Ext.Msg.alert('Status', '必需选取一条记录进行删除操作!');
        }
        else{
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    //****************单条记录删除方式,但不更新store中的内容*********
                    //selectedRows[0].destroy();
                    //************************************************************

                    //********借助store接口进行更新*******************************
                    if (me.gridStore != null || me.gridStore != 'undefined') {
                        console.log("remove");
                        me.gridStore.remove(selectedRows);
                        me.syncGridDate({
                            success: function (batch, eOpts) {
                                var msg = batch.proxy.reader.rawData.message;
                                if (msg == null) {
                                    msg = 'Request failed.';
                                }
                                Ext.Msg.alert('Status', msg);
                            },
                            failure: function (batch, eOpts) {
                                var msg = batch.proxy.reader.rawData.message;
                                if (msg == null) {
                                    msg = 'Request failed.';
                                }
                                Ext.Msg.alert('Status', msg);
                            },
                            scope: me.gridStore
                        });
                    }
                    //**********************************************************
                }else {
                    return;
                }
            })
        }
    },
    /**
     * 加载grid的数据
     */
    loadGridDate:function(){
        console.log(this.gridStore);
        // console.log("girdstore.load")

//		this.gridStore = this.getStore('manageApp.base.store.Categorys');
        if(this.getSearchBar()!=null || this.getSearchBar()!='undefined'){
            console.log(this.gridStore);
            this.gridStore.proxy.extraParams=manageApp.utils.Form.getCommitParam(this.getSearchBar().getForm());
            this.gridStore.load({page: 1});
        }
        else{
            this.gridStore.load();
        }
    },
    /**
     * 查询栏重置
     */
    resetClick:function(){
        if(this.getSearchBar()!=null || this.getSearchBar()!='undefined'){
            this.getSearchBar().getForm().reset();
        }
    },
    /**
     * 查询点击事件
     */
    findClick:function(){
        this.loadGridDate();
    },
    /**
     * role增加菜单界面 点击事件
     */
    winFromSave:function(){
        if (!this.formWin.formPanel.getForm().isValid()) {
            return ;
        }

        var newRecord=this.formWin.formPanel.getForm().getValues();
        console.log(newRecord);
        console.log(newRecord+"newRecord")
        var model = Ext.create('manageApp.base.model.Role',newRecord);

        model.save({
            callback:function(records,operation,success){

                console.log("model.save")
                if(success){
                    if(operation.request.proxy.reader.rawData.success=='true'||
                        operation.request.proxy.reader.rawData.success==true){
                        success=true;
                    }
                    else{
                        success=false;
                    }
                }
                if(success){
                    this.formWin.close();
                    this.formWin=null;
                    this.loadGridDate();
                }
                else{
                    var msg=operation.request.proxy.reader.rawData.message;
                    if(msg==null){
                        msg='save faile.';
                    }
//					this.formWin.close();
//					this.formWin=null;
                    Ext.Msg.alert('Status', msg);
                }
            },
            scope:this
        });
    },

    /**
     * role修改界面按钮点击事件
     */
    winFormModif:function(){
        console.log('edit');
        if (!this.formWin.formPanel.getForm().isValid()) {
            return ;
        }
        var model=this.formWin.formPanel.getForm().getRecord();
        this.formWin.formPanel.getForm().updateRecord(model);
        console.log(model)
        console.log(this.formWin.formPanel.getForm().updateRecord(model));
//		this.gridStore.commitChanges();//该语句为本地保存,保存后this.gridStore.getModifiedRecords()会清掉;
        var gridStore=this.gridStore;
        this.syncGridDate({
            success:function(batch, eOpts){
                var msg=batch.proxy.reader.rawData.message;
                if(msg==null){
                    msg='Changes saved successfully.';
                }
                Ext.Msg.alert('Status',msg);
                var me=this;
                this.close();
            },
            failure:function(batch, eOpts){
                var msg=batch.proxy.reader.rawData.message;
                if(msg==null){
                    msg='Request failed.';
                }
                Ext.Msg.alert('Status', msg);
            },
            scope:this.formWin
        });

    },

    /**
     * role Permission win btnSave click
     * @constructor
     */
    WinPermissionSave: function(obj){
        console.log('WinPermissionSave');
        // if (!this.formWin.formPanel.getForm().isValid()) {
        //     return ;
        // }
        var view=obj.up("baseViewRolePermissionWin");
        var grid=view.down("grid[name=permissionRoleGrid]");
      // var a=  obj.up("baseViewRolePermissionWin").down("grid[name=permissionRoleGrid]").getSelectionModel().getSelection();

        var selectedRows=grid.getSelectionModel().getSelection();
        // getGrid().getSelectionModel().getSelection();
        // var selectedRows=this.getGrid().getSelectionModel().getSelection();
        console.log(selectedRows+"selectedRows");
        console.log(selectedRows);
        if(selectedRows.length==1){

        }
        // var newRecord=this.formWin.formPanel.getForm().getValues();
        // var model = Ext.create('manageApp.base.model.Role',newRecord);
        var model = Ext.create('manageApp.base.model.Menu',selectedRows[0]);

        model.save({
            callback:function(records,operation,success){

                console.log("model.save")
                if(success){
                    if(operation.request.proxy.reader.rawData.success=='true'||
                        operation.request.proxy.reader.rawData.success==true){
                        success=true;
                    }
                    else{
                        success=false;
                    }
                }
                if(success){
                    this.formWin.close();
                    this.formWin=null;
                    this.loadGridDate();
                }
                else{
                    var msg=operation.request.proxy.reader.rawData.message;
                    if(msg==null){
                        msg='save faile.';
                    }
//					this.formWin.close();
//					this.formWin=null;
                    Ext.Msg.alert('Status', msg);
                }
            },
            scope:this
        });
    },

    /**
     * 添加,删除后,同步grid中的数据
     * options
     * 	.success callback
     *  .failure callback
     *  .scope
     */
    syncGridDate:function(options){
        if(this.gridStore!=null || this.gridStore!='undefined'){
            var scope=null;
            if(typeof(options)!='undefined'){
                scope=options.scope;
            }
            this.gridStore.sync({
                success : function(batch, eOpts) {
                    if(typeof(options)!='undefined' && typeof(options.success)=='function'){
                        if(scope!=null){
                            options.success.call(scope,batch, eOpts);
                        }
                        else{
                            options.success(batch, eOpts);
                        }
                    }
                },
                failure : function(batch, eOpts) {
                    this.rejectChanges();
                    if(typeof(options)!='undefined' && typeof(options.success)=='function'){
                        if(scope!=null){
                            options.failure.call(scope,batch, eOpts);
                        }
                        else{
                            options.failure(batch, eOpts);
                        }
                    }
                },
                scope:this.gridStore
            });
        }
    },


});