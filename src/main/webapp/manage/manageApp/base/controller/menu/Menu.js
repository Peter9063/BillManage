Ext.define('manageApp.base.controller.menu.Menu', {
    extend: 'Ext.app.Controller',
    stores: ['manageApp.base.store.Menus'],
    views: ['manageApp.base.view.menu.Menu'],
    refs:[
        {
            ref: 'searchBar',
            selector: 'baseViewMenu form[name=searchBar]'
        },
        {
            ref: 'tree',
            selector: 'baseViewMenu treepanel[name=grid]'
        }
    ],
    init: function(application) {

        var me =this;
        console.log("init");
        this.treeStore = this.getStore('manageApp.base.store.Menus');

        this.control({
            'baseViewMenu treepanel button[name=butAdd]':{
                click: this.saveClick
            },
            'baseViewMenu treepanel button[name=butEdit]':{
                click: this.editClick
            },
            'baseViewMenu treepanel button[name=butDel]':{
                click: this.delClick
            },
            'baseViewMenu form button[name=butFind]':{
                click: this.findClick,
                afterrender: this.afterrenderClick
            },
            'baseViewMenu form button[name=butReset]':{
                click: this.resetClick
            },
            'baseViewMenuFormWin button[name=btnWinFormSave]':{
                click:this.winFromSave
            },
            'baseViewMenuFormWin button[name=btnWinFormModif]':{
                click:this.winFormModif
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
        var selectedRows=this.getTree().getSelectionModel().getSelection();
        // console.log(selectedRows[0].id);
        // console.log(selectedRows[0].data.name);
        // console.log(selectedRows[0].data.menuId);
        if(selectedRows.length==1) {
            this.formWin = Ext.create('manageApp.base.view.menu.MenuFormWin', {
                title: '新增',
                isSave: true,
                record: selectedRows[0]
            });
        }else {
            Ext.Msg.alert('Status', '只能选择一条记录进行增加子资源操作!');
        }
    },
    /**
     * 修改点击事件
     */
    editClick:function(){
        console.log('editClick');

        var selectedRows=this.getTree().getSelectionModel().getSelection();
        console.log(selectedRows+"selectedRows");
        if(selectedRows.length==1){
            console.log(selectedRows[0]);
            this.formWin=Ext.create('manageApp.base.view.menu.MenuFormWin',{
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
     * 删除点击事件
     */
    delClick:function(){
        var me=this;
        var selectedRows=this.getTree().getSelectionModel().getSelection();
        if(selectedRows.length<1){
            Ext.Msg.alert('Status', '必需选取一条记录进行删除操作!');
        }
        else{


            // var selectedRows = grid.getSelectionModel();
            if (selectedRows.length=1) {
                Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                    if (button == "yes") {
                        // console.log(me.treeStore);
                        //****************单条记录删除方式,但不更新store中的内容*********
                        //selectedRows[0].destroy();
                        //************************************************************
                        //********借助store接口进行更新*******************************
                        if(me.treeStore!=null || me.treeStore!='undefined'){

                            var id=selectedRows[0].id.substr(26);//获取treeStore 选中行的 node 的节点id 截掉前26位
                            var node= me.treeStore.getNodeById(id);//根据id获取对应的node节点
                            node.remove();
                            // me.treeStore.remove(selectedRows);//treestore中无此方法，用以上内容替换
                            me.syncGridDate({
                                success:function(batch, eOpts){
                                    var msg=batch.proxy.reader.rawData.message;
                                    if(msg==null){
                                        msg='Request failed.';
                                    }
                                    Ext.Msg.alert('Status', msg);
                                },
                                failure:function(batch, eOpts){
                                    var msg=batch.proxy.reader.rawData.message;
                                    if(msg==null){
                                        msg='Request failed.';
                                    }
                                    Ext.Msg.alert('Status', msg);
                                },
                                scope:me.treeStore
                            });
                        }
                        //**********************************************************

                    }
                    else {
                        // Ext.Msg.alert("否","不删除")
                    }
                });
            }
            // return;

        }
    },
    /**
     * 加载grid的数据
     */
    loadGridDate:function(){
        var me=this;
        console.log(this.treeStore);
//		this.treeStore = this.getStore('manageApp.base.store.Categorys');
        if(this.getSearchBar()!=null || this.getSearchBar()!='undefined'){
            console.log(this.treeStore);
            this.treeStore.proxy.extraParams=manageApp.utils.Form.getCommitParam(this.getSearchBar().getForm());
            this.treeStore.load({page: 1});
        }
        else{
            this.treeStore.load();

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

        /**
         * params(root根节点，store,查询条件)
         */
        var rootNode=this.getTree().getRootNode();
        this.getTree().expandAll();
        var test=this.getSearchBar().getForm().getValues();
        console.log(test);
        travelRootNode(rootNode,this.getTree(),test["name"]);

    },
    /**
     * 查询点击事件
     */
    afterrenderClick:function(){
        var me=this;
        this.loadGridDate();
        var task = new Ext.util.DelayedTask(function(){
            // 这里放置要延迟加载的代码段
            me.getTree().expandAll();
        });
        //创建延迟加载对象
        //调用延迟加载对象的delay方法执行  参数为毫秒 本示例延迟0.1秒执行
        task.delay(250);

    },
    /**
     * menu增加菜单界面 点击事件
     */
    winFromSave:function(){
        if (!this.formWin.formPanel.getForm().isValid()) {
            return ;
        }

        var newRecord=this.formWin.formPanel.getForm().getValues();

        var model = Ext.create('manageApp.base.model.Menu',newRecord);
        // var model = Ext.create('manageApp.base.model.PCode',newRecord);
        console.log(model);
        console.log(newRecord);
        console.log("newRecord");
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
     *  修改界面按钮点击事件
     */
    winFormModif:function(){
        console.log('edit');
        if (!this.formWin.formPanel.getForm().isValid()) {
            return ;
        }
        var model=this.formWin.formPanel.getForm().getRecord();
        this.formWin.formPanel.getForm().updateRecord(model);
        console.log(this.treeStore);
        console.log("girdstore");
//		this.treeStore.commitChanges();//该语句为本地保存,保存后this.treeStore.getModifiedRecords()会清掉;
        var treeStore=this.treeStore;
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
     * 添加,删除后,同步grid中的数据
     * options
     * 	.success callback
     *  .failure callback
     *  .scope
     */
    syncGridDate:function(options){
        var me=this;
        if(this.treeStore!=null || this.treeStore!='undefined'){
            var scope=null;
            if(typeof(options)!='undefined'){
                scope=options.scope;
            }
            console.log("sync");
            this.treeStore.sync({

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
                    // this.rejectChanges();//treestore 下似乎不支持，用下行重载
                    me.loadGridDate();
                    if(typeof(options)!='undefined' && typeof(options.success)=='function'){
                        if(scope!=null){
                            options.failure.call(scope,batch, eOpts);
                        }
                        else{
                            options.failure(batch, eOpts);
                        }
                    }
                },
                scope:this.treeStore
            });
        }
    },
});

function travelRootNode(rootNode,treePanel,test) {
    if(rootNode.hasChildNodes()){
        if(rootNode.findChild('name',test)){
            var ss = treePanel.getSelectionModel();
            ss.select(rootNode.findChild('name',test));
            return;
        }else {
            rootNode.eachChild(function (child) {
                travelRootNode(child,treePanel,test);
            })
        }
    }else {
        return;
    }
}