Ext.define('manageApp.base.controller.role.RoleAndUserDistribution', {
    extend: 'Ext.app.Controller',

    aliasWinViews: 'baseViewRoleAndUserDistributionWin',
    aliasViews: 'baseViewRoleAndUserDistribution ',
    aliasSearchBar: "form[name=searchBar] ",
    aliasGrid: 'grid[name=grid] ',

    init: function (application) {

        console.log("initRoleAndUserDistribution");

        // var regViewEvent = {};
        // regViewEvent[this.aliasViews + 'button[name=butAdd]'] = {click: this.butAdd};
        // regViewEvent[this.aliasViews + 'button[name=butRemove]'] = {click: this.removeClick};
        // regViewEvent[this.aliasViews + 'button[name=butFind]'] = {click: this.findClick, afterrender: this.findClick};
        // regViewEvent[this.aliasViews + 'button[name=butAllSave]'] = {click: this.saveAllClick};
        // regViewEvent[this.aliasWinViews +"button[name=butFind]"] = {
        //     click: this.findClickWin,
        //     afterrender: this.findClickWin
        // };
        // // regViewEvent[this.aliasWinViews + 'button[name=butAdd1]'] = {click: this.butAddWin};
        // this.control(regViewEvent);

            this.control({
                'baseViewRoleAndUserDistribution  button[name=butReset]': {
                    click: this.resetClick
                },
                'baseViewRoleAndUserDistribution  button[name=butAdd]': {
                    click: this.butAdd
                },
                'baseViewRoleAndUserDistribution  button[name=butRemove]': {
                    click: this.removeClick
                },
                'baseViewRoleAndUserDistribution  button[name=butFind]': {
                    click: this.findClick,
                    afterrender: this.findClick,
                },
                'baseViewRoleAndUserDistribution  button[name=butAllSave]': {
                    click: this.saveAllClick
                },
                'baseViewRoleAndUserDistributionWin button[name=butFind]': {
                    click: this.findClickWin,
                    afterrender: this.findClickWin
                },
                'baseViewRoleAndUserDistributionWin button[name=butAdd]': {
                    click: this.butAddWin,
                },
                'baseViewRoleAndUserDistributionWin  button[name=butReset]': {
                    click: this.resetClickWin
                },


            });
    },

    onLaunch: function (application) {
        console.log("onLaunch");
        this.loadGridDate();
//		添加
//		修改

    },
    /**
     *  添加按钮点击事件
     */
    butAdd: function (obj, event, eOpts) {
        console.log("butAdd")
        var view = obj.up(this.aliasViews);
        var params = [];
        params.parentView = view;
        params.roleId = view.params.roleId;
        params.roleName = view.params.roleName;
        console.log(view.params);
        this.formWin = Ext.create('manageApp.base.view.role.RoleAndUserDistributionWin', {
            title: '新增',
            params: params
        });
    },

    /**
     * win窗口 添加按钮点击事件
     */
    butAddWin: function (obj) {
        console.log("butAddWin");
        var me = this;
        var view = obj.up(this.aliasWinViews);
        var grid = view.down(this.aliasGrid);
        var store = view.mainStore;
        var selectedRows = grid.getSelectionModel().getSelection();
        var userList = [];
        for (var i = 0; i < selectedRows.length; i++) {
            userList.push(selectedRows[i].data)
            store.remove(selectedRows[i]);
        }
        console.log(selectedRows[0].data);
        console.log('selectedRows[0]');
        var roleId=view.params.roleId;
        if (selectedRows.length < 1) {
            Ext.Msg.alert('Status', '请选择一条记录进行增加操作!');
            return;
        } else {
            manageApp.utils.Ajax.request2('../base/roleUserRelation/insertRelation', 'post',
                {data: Ext.JSON.encode(userList), roleId: roleId},//字符串
                function (options, success, response) {
                    if (success) {
                        Ext.Msg.alert('Status', JSON.parse(response.responseText).message);
                        view.params.parentView.mainStore.load();
                    }else {
                        grid.rejectChanges();
                    }
                });
        }

    },
    /**
     * 移除按钮点击事件
     */
    removeClick: function (obj, event, eOpts) {
        console.log("rightClick");
        var me = this;
        var view = obj.up(this.aliasViews);
        var grid = view.down(this.aliasGrid);
        var store = view.mainStore;
        var selectedRows = grid.getSelectionModel().getSelection();
        if (selectedRows.length < 1) {
            Ext.Msg.alert('Status', '请选择一条记录进行移除操作!');
            return;
        } else {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {

                    //********借助store接口进行更新*******************************
                    if (store != null || store != 'undefined') {
                        console.log("remove");
                        store.remove(selectedRows);
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
                        }, store);
                    }
                    //**********************************************************
                } else {
                    return;
                }
            })
        }
    },


    /**
     * 权限保存事件
     */
    saveClick: function () {
        manageApp.utils.Ajax.request2('../base/relation/insertRelation', 'post',
            {data: Ext.JSON.encode(), roleId: this.roleId},//字符串
            function (options, success, response) {
                if (success) {
                    Ext.Msg.alert('Status', JSON.parse(response.responseText).message)
                }
            });
    },

    /**
     * 加载grid的数据
     */
    loadGridDate: function (searchBar, store, roleId, type) {
        if (searchBar != null || searchBar != 'undefined') {
            store.proxy.extraParams = manageApp.utils.Form.getCommitParam(searchBar.getForm());
            store.proxy.extraParams.roleId=roleId;
            store.proxy.extraParams.type=type;
            store.load({page: 1});
        } else {
            store.load();
        }
    }
    ,
    /**
     * win查询栏重置
     */
    resetClick: function (obj) {
        var view = obj.up(this.aliasViews);
        var searchBar = view.down(this.aliasSearchBar);
        if (searchBar != null || searchBar != 'undefined') {
            searchBar.getForm().reset();
        }
    }
    ,
    /**
     * win查询栏重置
     */
    resetClickWin: function (obj) {
        var view = obj.up(this.aliasWinViews);
        var searchBar = view.down(this.aliasSearchBar);
        if (searchBar != null || searchBar != 'undefined') {
            searchBar.getForm().reset();
        }
    }
    ,
    /**
     * 查询点击事件
     */
    findClick: function (obj, event, eOpts) {
        var me = this;
        var view = obj.up(this.aliasViews);
        var store = view.mainStore;
        var searchBar = view.down(this.aliasSearchBar);
        var type = store.storeId.substr(13);
        var roleId = view.params.roleId;
        this.roleId = roleId;
        this.loadGridDate(searchBar, store, roleId, type);
    },


    /**
     *添加分配用户窗口  查询按钮 点击事件
     * @param obj
     */
    findClickWin: function (obj) {
        console.log("findClickWin");
        var me = this;
        var view = obj.up(this.aliasWinViews);
        var store = view.mainStore;
        var searchBar = view.down(this.aliasSearchBar);
        var roleId = view.params.roleId;
        this.loadGridDate(searchBar, store, roleId, 1);
    },

    saveAllClick: function (obj) {
        console.log("saveAllClick");
        var view = obj.up("baseViewRoleAndUserDistribution");
        var store = view.mainStore_right;
        var items = store.data.items;
        var userList = [];
        for (var i = 0; i < items.length; i++) {
            userList.push(items[i].data)
        }
        manageApp.utils.Ajax.request2(
            "../forum/roleUserRelation/insertRelation",
            'post',
            {data: Ext.JSON.encode(userList), roleId: this.roleId},//字符串
            function (options, success, response) {
                if (success) {
                    Ext.Msg.alert('Status', JSON.parse(response.responseText).message)
                }
            });

    },


    /**
     * 添加,删除后,同步grid中的数据
     * options
     *    .success callback
     *  .failure callback
     *  .scope
     */
    syncGridDate: function (options, gridStore) {
        if (gridStore != null || gridStore != 'undefined') {
            var scope = null;
            if (typeof (options) != 'undefined') {
                scope = options.scope;
            }
            gridStore.sync({
                success: function (batch, eOpts) {
                    if (typeof (options) != 'undefined' && typeof (options.success) == 'function') {
                        if (scope != null) {
                            options.success.call(scope, batch, eOpts);
                        } else {
                            options.success(batch, eOpts);
                        }
                    }
                },
                failure: function (batch, eOpts) {
                    this.rejectChanges();
                    if (typeof (options) != 'undefined' && typeof (options.success) == 'function') {
                        if (scope != null) {
                            options.failure.call(scope, batch, eOpts);
                        } else {
                            options.failure(batch, eOpts);
                        }
                    }
                },
                scope: gridStore
            });
        }
    }


});




