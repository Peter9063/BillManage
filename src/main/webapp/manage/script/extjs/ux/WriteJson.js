Ext.define('Ext.ux.WriteJson',{
	
	 extend: 'Ext.data.writer.Json',
    /*
     * Formats the data for each record before sending it to the server. 
     * This method should be overridden to format the data in a way that differs from the default.
     */
    getRecordData: function(record, operation) {
//        var data = {};
//        /*
//         * Parse your record and give it whatever structure you need here..
//         */
//        data.lines = [];
//        return data;
    	
        var data = record;
        var me = this;
        var toObject = function (name, value) {
            var o = {};
            o[name] = value;
            return o;
        };
        var itemsToObject = function (item) {
            for (prop in item) {
                if (Array.isArray(item[prop])) {
                    me.getRecordData(item[prop]);
                }
                else {
                    if (item.hasOwnProperty(prop)) {
                        var nameParts = prop.split('.');
                        var j = nameParts.length - 1;
                        if (j > 0) {
                            var tempObj = item[prop];
                            for (; j > 0; j--) {
                                tempObj = toObject(nameParts[j], tempObj);
                            }
                            item[nameParts[0]] = item[nameParts[0]] || {};
                            Ext.Object.merge(item[nameParts[0]], tempObj);
                            delete item[prop];
                        }
                    }
                }
            }
        };

        if (!Array.isArray(data)) {
            data = data.getData();
            itemsToObject(data);
        }
        else {
            var dataLength = data.length;
            for (var i = 0; i < dataLength; i++) {
                itemsToObject(data[i]);
            }
        }

        return data;
        
//       return this.callParent(arguments);
    }
});