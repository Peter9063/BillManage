/**
 * A Picker field that contains a tree panel on its popup, enabling selection of tree nodes.
 */
Ext.define('Ext.ux.TreePicker', {
    extend: 'Ext.form.field.Picker',
    xtype: 'treepicker',
    
    uses: [
        'Ext.tree.Panel'
    ],

    triggerCls: Ext.baseCSSPrefix + 'form-arrow-trigger',

    config: {
        /**
         * @cfg {Ext.data.TreeStore} store
         * A tree store that the tree picker will be bound to
         */
        store: null,

        /**
         * @cfg {String} displayField
         * The field inside the model that will be used as the node's text.
         * Defaults to the default value of {@link Ext.tree.Panel}'s `displayField` configuration.
         */
        displayField: null,

        /**
         * @cfg {Array} columns
         * An optional array of columns for multi-column trees
         */
        columns: null,

        /**
         * @cfg {Boolean} selectOnTab
         * Whether the Tab key should select the currently highlighted item. Defaults to `true`.
         */
        selectOnTab: true,

        /**
         * @cfg {Number} maxPickerHeight
         * The maximum height of the tree dropdown. Defaults to 300.
         */
        maxPickerHeight: 300,
        
        /**
         * @cfg {Number} minPickerHeight
         * The minimum height of the tree dropdown. Defaults to 100.
         */
        minPickerHeight: 30,
        
        /**
         * @cfg {Boolean/Object} resizable
         * Specify as `true` to allow user resizing at each edge and corner of the window, false to disable resizing.
         */
        pickerResizable: true
    },
   
    editable: true,

    initComponent: function() {
        var me = this;
//        if (Ext.isString(me.store))
    	me.store = Ext.data.StoreManager.lookup(me.store);
        
        me.callParent(arguments);

        me.addEvents(
            /**
             * @event select
             * Fires when a tree node is selected
             * @param {Ext.ux.TreePicker} picker        This tree picker
             * @param {Ext.data.Model} record           The selected record
             */
            'select',
            'specialkey'
        );

        me.mon(me.store, {
            scope: me,
            load: me.onLoad,
            update: me.onUpdate
        });
        
        me.on({'specialkey': me.specialkey});
    },

    /**
     * Creates and returns the tree panel to be used as this field's picker.
     */
    createPicker: function() {
        var me = this,
            picker = new Ext.tree.Panel({
                shrinkWrapDock: 2,
                store: me.store,
                floating: true,
                displayField: me.displayField,
                columns: me.columns,
                minHeight: me.minPickerHeight,
                maxHeight: me.maxPickerHeight,
                manageHeight: false,
                height: 400,		//解决在点击展开图标时，picker自动滚动到picker顶部的 bug
                shadow: false,
    			autoScroll : true,
    			resizable : me.pickerResizable,
                listeners: {
                    scope: me,
                    itemclick: me.onItemClick,
                    beforeDestroy: me.onBeforeDestroy
                },
                viewConfig: Ext.apply({
                    listeners: {
                        scope: me,
                        render: me.onViewRender
                    }
                }, me.viewConfig)
            }),
            view = picker.getView();

        if (Ext.isIE9 && Ext.isStrict) {
            // In IE9 strict mode, the tree view grows by the height of the horizontal scroll bar when the items are highlighted or unhighlighted.
            // Also when items are collapsed or expanded the height of the view is off. Forcing a repaint fixes the problem.
            view.on({
                scope: me,
                highlightitem: me.repaintPickerView,
                unhighlightitem: me.repaintPickerView,
                afteritemexpand: me.repaintPickerView,
                afteritemcollapse: me.repaintPickerView
            });
        }
        return picker;
    },
    
    onViewRender: function(view){
        view.getEl().on('keypress', this.onPickerKeypress, this);
    },

    /**
     * repaints the tree view
     */
    repaintPickerView: function() {
        var style = this.picker.getView().getEl().dom.style;

        // can't use Element.repaint because it contains a setTimeout, which results in a flicker effect
        style.display = style.display;
    },

    /**
     * Aligns the picker to the input element
     */
    alignPicker: function() {
        var me = this,
            picker;

        if (me.isExpanded) {
            picker = me.getPicker();
            if (me.matchFieldWidth) {
                // Auto the height (it will be constrained by max height)
                picker.setWidth(me.bodyEl.getWidth());
            }
            else
            	picker.setWidth(me.bodyEl.getWidth() + (me.offsetWidth || 120));

            if (picker.isFloating()) {
                me.doAlign();
            }
        }
    },

    /**
     * Handles a click even on a tree node
     * @private
     * @param {Ext.tree.View} view
     * @param {Ext.data.Model} record
     * @param {HTMLElement} node
     * @param {Number} rowIndex
     * @param {Ext.EventObject} e
     */
    onItemClick: function(view, record, node, rowIndex, e) {
        var me = this;
        me.selectItem(record);
        me.isExpanded = false;			//解决选择树节点后，需要点击两次trigger才能打开picker的bug
    },

    /**
     * Handles brfore destroy even on a tree node
     */
    onBeforeDestroy: function() { 
    	return true;
    },
    
    /**
     * Handles a keypress event on the picker element
     * @private
     * @param {Ext.EventObject} e
     * @param {HTMLElement} el
     */
    onPickerKeypress: function(e, el) {
        var key = e.getKey();

        if(key === e.ENTER || (key === e.TAB && this.selectOnTab)) {
            this.selectItem(this.picker.getSelectionModel().getSelection()[0]);
        }
    },

    /**
     * Changes the selection to a given record and closes the picker
     * @private
     * @param {Ext.data.Model} record
     */
    selectItem: function(record) {
        var me = this;
        me.setValue(record.getId());
        me.setRawValue(record.get(me.displayField));
        me.getPicker().hide();
        me.inputEl.focus();
        me.fireEvent('select', me, record);
    },

    /**
     * Runs when the picker is expanded.  Selects the appropriate tree node based on the value of the input element,
     * and focuses the picker so that keyboard navigation will work.
     * @private
     */
    onExpand: function() {
        var me = this,
            picker = me.picker,
            store = picker.store,
            value = me.value,
            node = null;

        
        if (value) {
            node = store.getNodeById(value);
        }
        
        if (!node) {
            node = store.getRootNode();
        }
        
        picker.selectPath(node.getPath());

        Ext.defer(function() {
            picker.getView().focusNode(node);
        }, 1);
    },

    /**
     * Sets the specified value into the field
     * @param {Mixed} value
     * @return {Ext.ux.TreePicker} this
     */
    setValue: function(value) {
        var me = this,
            record,
            inputEl = me.inputEl;

        me.value = value;

        if (me.store.loading) {
            // Called while the Store is loading. Ensure it is processed by the onLoad method.
            return me;
        }
            
        // try to find a record in the store that matches the value
        if (value === undefined) {
//            record = me.store.getRootNode();
//            me.value = record.getId();
        } else {
            record = me.store.getNodeById(value);
        }

        if (inputEl && me.emptyText && !Ext.isEmpty(value)) {
            inputEl.removeCls(me.emptyCls);
        }

        // set the raw value to the record's display field if a record was found
        me.setRawValue(record ? record.get(me.displayField) : '');
        me.applyEmptyText();
        
        return me;
    },
    
    getSubmitValue: function(){
        var value = this.getValue();
        
        // If the value is null/undefined, we still return an empty string. If we
        // don't, the field will never get posted to the server since nulls are ignored.
        if (Ext.isEmpty(value)) {
            value = '';
        }
        return value;    
    },

    /**
     * Returns the current data value of the field (the idProperty of the record)
     * @return {Number}
     */
    getValue: function() {
        return this.value;
    },

    /**
     * Handles the store's load event.
     * @private
     */
    onLoad: function() {
        var me = this,
        	value = me.value;

        if (value) {
        	me.setValue(value);
        }
    },
    
    onFocus: function() {
    	var me = this;
    	
    	if (me.readOnly !== true)
			me.expand();
    	
    	me.callParent(arguments);
	},
    
    onUpdate: function(store, rec, type, modifiedFieldNames){
        var display = this.displayField;
        
        if (type === 'edit' && modifiedFieldNames && Ext.Array.contains(modifiedFieldNames, display) && this.value === rec.getId()) {
            this.setRawValue(rec.get(display));
        }
    },
    
    specialkey: function(field, e){
        if (e.getKey() == e.ENTER) {
        	var text = this.getRawValue();
    		var me=this;
    		me.picker=me.getPicker() || me.createPicker();
    		me.expand();
    		me.isExpanded = true;
    		
    		text = this.emptyText == text ? '' : text;
    		var reEn=/[a-zA-Z]+/;
    		var validEn=reEn.test(text);
    		var firstSelected=false;
    		//创建正则表达式对象，忽略大小写。
    		var re;
    		if(validEn){
    			re= new RegExp(Ext.String.escapeRegex(makePy(text)[0]), 'ig');
    		}else{
    			re=new RegExp(Ext.String.escapeRegex(text), 'ig');
    		}
    		// 定位并级联展开
    		me.picker.getRootNode().cascadeBy(function(n) {
    			
    			if (!n.isLeaf() && !n.isLoaded()) {
    				return true;
    			}
    			var reTest;
    			if(validEn){
        			reTest= re.test(makePy(n.data.text));
        		}else{
        			reTest=re.test(n.data.text);
        		}
    			if(reTest){
    				node = n;
//    				me.picker.getSelectionModel().select(node)
    				node.bubble(function(parentNode) {
    					parentNode.expand();
    				});
    				if(!firstSelected){
    					me.picker.fireEvent('itemclick', me.picker,node);
        				firstSelected=true;
    				}
    				return false;
    			}
    			return true;
    		});

    		if(!firstSelected) {
    			me.collapse();
    			me.isExpanded = false;
        		
    		}
        }
    }

});