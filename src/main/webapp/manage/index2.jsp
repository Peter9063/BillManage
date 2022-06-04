<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="script/extjs/resources/css/ext-all.css">
    <script type="text/javascript" charset="utf-8" src="script/extjs/bootstrap.js"></script>
    <script type="text/javascript" charset="utf-8" src="script/extjs/locale/ext-lang-zh_CN.js"></script> 
    <!-- <link rel="stylesheet" type="text/css" href="script/classic/theme-classic/resources/theme-classic-all.css"> -->
   <!--  <script type="text/javascript" charset="utf-8" src="script/ext-all-debug.js"></script> -->
    <script type="text/javascript" charset="utf-8" src="manageApp/utils/Ajax.js"></script>
    <script type="text/javascript" charset="utf-8" src="manageApp/utils/Form.js"></script>
    <script type="text/javascript" charset="utf-8" src="manageApp/utils/Model.js"></script>
    <script type="text/javascript" charset="utf-8" src="script/extjs/ux/TreePicker.js"></script>
    <!-- <link rel="stylesheet" type="text/css" href="script/classic/theme-classic/theme-classic.js"> -->
    <!-- <script type="text/javascript" charset="utf-8" src="manageApp/app.js"></script> -->
    
    <link href="script/gantt-2.2.9/resources/css/sch-gantt-all.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="script/gantt-2.2.9/gnt-all-debug.js" ></script>
    <script type="text/javascript" src="script/gantt-2.2.9/sch-lang-zh_CN.js" ></script>

        <script type="text/javascript">
        Ext.onReady(function(){
        	Localize();
            var taskStore = Ext.create('Gnt.data.TaskStore', {
                autoLoad    : true,
                proxy       : {
                    type    : 'memory',
                    reader  : {
                        type : 'json'
                    },

                    data    : [
                        { 
                            "StartDate" : "2010-01-18",
                            "EndDate"   : "2010-02-02",
                            "Id"        : 1,
                            "Name"      : "Planning",
                            "expanded"  : true,
                            "children"  : [
                                { 
                                    "StartDate" : "2010-01-18",
                                    "EndDate"   : "2010-01-26",
                                    "Id"        : 2,
                                    "leaf"      : true,
                                    "Name"      : "Investigate",
                                    "parentId"  : 1
                                },
                                { 
                                    "StartDate" : "2010-01-22",
                                    "EndDate"   : "2010-01-25",
                                    "Id"        : 3,
                                    "leaf"      : true,
                                    "Name"      : "Investigate2",
                                    "parentId"  : 1
                                },
                                { 
                                    "StartDate" : "2010-01-28",
                                    "EndDate"   : "2010-01-28",
                                    "Id"        : 4,
                                    "leaf"      : true,
                                    "Name"      : "Investigate3",
                                    "parentId"  : 1
                                }
                            ]
                        }
                    ]
                    // eof data
                }
                // eof proxy
            });

            var ganttPanel = Ext.create('Gnt.panel.Gantt', {
                height     : 400,
                width      : 1000,

                viewPreset : 'weekAndDayLetter',
                
                stripeRows: true,//分辨奇偶行
                snapToIncrement : true,
                enableDependencyDragDrop: false,//是否允许
                loadMask: true,//是否显示loading 框
                showTodayLine: true,//显示今天
                highlightWeekends: true,//是否用不同颜色显示周末
                //leftLabelField: 'Name',

                startDate  : new Date(2010, 0, 15),
                endDate    : Sch.util.Date.add(new Date(2010, 0, 15), Sch.util.Date.WEEK, 3),

                columns    : [
                    {
                        xtype       : 'treecolumn',
                        header      : 'Tasks',
                        sortable    : false,
                        dataIndex   : 'Name',
                        width       : 200,
                        editor: new Ext.form.TextField()
                    }
                ],
                plugins         : [
                   // Ext.create('Gnt.plugin.Printable')
                     Ext.create("Gnt.plugin.TaskContextMenu"),
                     Ext.create("Gnt.plugin.DependencyEditor", {
                         // default value
                         hideOnBlur      : true
                     }),
                     Ext.create("Gnt.plugin.TaskEditor", {
                         // window title
                         title           : 'Task Editor'
                     })
                ],

                taskStore       : taskStore
            });

            ganttPanel.render(Ext.getBody());
        });
        
        </script>
</head>
<body></body>
</html>