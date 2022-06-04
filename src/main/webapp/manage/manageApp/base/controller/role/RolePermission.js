Ext.define('manageApp.base.controller.role.RolePermission', {
    extend: 'Ext.app.Controller',
    // stores: ['manageApp.base.store.RolePermissions'],
    // views: ['manageApp.base.view.role.RolePermission'],
    refs: [
        {
            ref: 'searchBar',
            selector: 'baseViewRolePermission form[name=searchBar]'
        },
        {
            ref: 'tree',
            selector: 'baseViewRolePermission treepanel[name=roleTree]'
        }
    ],
    init: function (application) {

        console.log("initRolePermission");

        this.control({
            'baseViewRolePermission  button[name=butSave]': {
                click: this.saveClick
            },
            'baseViewRolePermission  button[name=butFind]': {
                click: this.findClick,
                afterrender: this.findClick,

            },
            'baseViewRolePermission treepanel[name=roleTree]': {
                checkchange: this.checkChangeClick,
                // itemclick:this.treeClick
            }

        });
    },

    onLaunch: function (application) {
        console.log("onLaunch");
        this.loadGridDate();
//		添加
//		修改

    },
    /**
     * tree 复选框点击事件  node, checked, eOpts
     * //todo
     */
    checkChangeClick: function (node, checkStatus, opts) {
        console.log("checkChangeClick");

        var me = this;

            travelChildrenChecked(node, checkStatus, opts);
            travelParentChecked(node, checkStatus, opts);


        }
    ,

        /**
         * tree点击事件
         */
        treeClick:function (node, record, item, index, event, eOpts) {
            console.log("treeClick.click ");
            console.log(node);
            console.log(node.id);
            console.log(item);
            console.log(node.hasChildNodes() + "childNodes    ");
            if (node.hasChildNodes()) {
                for (var i = 0; i < node.childNodes.length; i++) {
                    node.childNodes[i].getUI().checkbox.checked = state;
                }
            }

        }
    ,
        /**
         * 权限保存事件
         */
        saveClick:function () {


            var treepanel1=this.getTree();

            var selNodes = this.getTree().getChecked();

                var userList =[];
                //遍历获取所有的节点数据
                Ext.each(selNodes, function (node) {
                    //子节点 也就是用户节点
                        var user = {};
                        user.id = node.data.id;
                        user.parentId=node.data.parentId;
                        userList.push(user);
                });

                console.log(userList);

            manageApp.utils.Ajax.request2( '../base/relation/insertRelation','post',
                {data:  Ext.JSON.encode(userList) ,roleId: this.roleId },//字符串
                function(options,success,response){
                    if(success){
                        Ext.Msg.alert('Status',JSON.parse(response.responseText).message)
                    }
                    // Ext.JSON.encode(userList);
                });

            // var model = Ext.create('manageApp.base.model.RolePermission',this,children);

        },

        /**
         * 加载grid的数据
         */
        loadGridDate:function (store,roleId) {
            console.log(store);
            console.log("my store");

            if (this.getSearchBar() != null || this.getSearchBar() != 'undefined') {

                store.proxy.extraParams ={roleId:roleId};

                store.load({page: 1});
            } else {
                store.load();
            }
        }
    ,
        /**
         * 查询栏重置
         */
        resetClick:function () {
            if (this.getSearchBar() != null || this.getSearchBar() != 'undefined') {
                this.getSearchBar().getForm().reset();
            }
        }
    ,
        /**
         * 查询点击事件
         */
        findClick:function (obj) {
            var  me =this;
            var view=obj.up("baseViewRolePermission");
            var store=view.roleTreeStore;
            var roleId=view.params.roleId;
            this.roleId=roleId;
            this.loadGridDate(store,roleId);
            // this.loadGridDate();
            var task = new Ext.util.DelayedTask(function(){
                me.getTree().expandAll();
            });
            task.delay(250);
        }
    ,




        /**
         * 添加,删除后,同步grid中的数据
         * options
         *    .success callback
         *  .failure callback
         *  .scope
         */
        syncGridDate:function (options) {
            if (this.gridStore != null || this.gridStore != 'undefined') {
                var scope = null;
                if (typeof (options) != 'undefined') {
                    scope = options.scope;
                }
                this.gridStore.sync({
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
                    scope: this.gridStore
                });
            }
        }
    ,


    });

/** 递归遍历父节点 **/
  function travelParentChecked (node, checkStatus, opts) {
    // console.log(opts);
    // console.log("up optrs");
    //父节点
    var upNode = node.parentNode;
    console.log(upNode);
    console.log("upNode");
    if (upNode != null) {
        var opts = {};
        opts["isPassive"] = true;
        //父节点当前选中状态
        var upChecked = upNode.data.checked;

        //选中状态，遍历父节点，判断有父节点下的子节点是否都全选
        if (checkStatus) {
            var allChecked = true;//含有选中的子元素
            var hasChecked=true;//子节点含有选中
            //此时父节点不可能是选中状态
            //如果有一个节点未选中，可以判断，当前父节点肯定是未选中状态，所以此时不必向上遍历
            upNode.eachChild(function (child) {
                if (child.data.checked) {
                    allChecked = true;
                    return false;
                }
            });

            upNode.set('checked', allChecked);
            if (allChecked) {
                // upNode.set('checked', allChecked);
                this.travelParentChecked(upNode, allChecked, opts);
            } else {//如果后台传递数据时，选择状态正确的话，此处不需要执行
                this.travelParentChecked(upNode, allChecked, opts);
            }
        } else {//未选中，让父节点全都 不选
            upNode.eachChild(function (child) {
                if(child.data.checked){
                    hasChecked=true;
                    return false;
                }
            });
            if(!hasChecked) {
                console.log("!hasChecked");
                if (upNode.data.checked) {
                    upNode.set('checked', checkStatus);
                    this.travelParentChecked(upNode, checkStatus, opts);
                } else {
                    this.travelParentChecked(upNode, allChecked, opts);
                }
            }
        }
    }
};




/** 递归遍历子节点，复选框 **/
 function travelChildrenChecked (node, checkStatus, eOpts){
    console.log("child");
    console.log(eOpts);
    var isLeaf = node.data.leaf;
    console.log("child 1");
    if(!isLeaf){
        console.log("child 2")
        node.expand(false, function(){
            // if(eOpts["isPassive"] == null){//主动点击
            console.log("child3");
            node.eachChild(function (child) {
                console.log("child4");
                child.set('checked', checkStatus);
                // node=child;
                travelChildrenChecked(child,checkStatus,eOpts);
                // child.fireEventArgs('checkchange',child, checked);
                // child.fireEvent('checkchange',child, checked);//不知什么原因，不起作用
            });
            // }
        });
    }
    node.set('checked', checkStatus);
};