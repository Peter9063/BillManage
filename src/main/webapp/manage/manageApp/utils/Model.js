Ext.define('manageApp.utils.Model', {
     statics: {
     	constantName_TestItem:'TestItem',//测试系统
     	constantName_UserRole:'UserRole',//测试系统
		constantName_WeekNumber:'WeekNumber',
     	/**
     	 * 系统常量表
     	 * @type 
     	 */
     	constants:{
     		'menuType':{
     		'1':'菜单',
			'2':'按钮'
			},
     		'cmmModelName':{
				'WPD水平旋翼干式智能水表':"WPD水平旋翼干式智能水表",
				'电磁水表':"电磁水表",
				'水平螺翼式干式智能水表':'水平螺翼式干式智能水表'
			},
     		'mesCommType':{
				'1':'GPRS',
				'2':'NB',
				'3':'GPRS+',
			},
     		'WeekNumber':{//周数量
     			'1':'1周',
				'2':'2周',
				'3':'3周',
				'4':'4周',
				'5':'5周',
				'6':'6周',
				'7':'7周',
				'8':'8周',
				'9':'9周'
			},
     		'TestItem':{//测试系统
     			'0':'写终端地址',
     			'1':'功能测试',
     			'2':'读版本频偏',
     			'3':'整机功耗',
     			'4':'终端参数初始化',
     			'5':'半成器计数准确性测试',
     			'6':'信号强度测试',
     			'7':'终端时钟',
     			'8':'自动校频功能测试',
     			'9':'成品计数准确性测试',
     			'10':'维修',
     			'11':'传感器-电感值测试',
     			'12':'传感器-整机功耗',
     			'13':'传感器-机电同步测试',
     			'14':'NBIOT-PCB_a功能项目',
     			'15':'NBIOT-整机功耗',
     			'16':'NBIOT-半成品机电同步测试(设置)',
     			'17':'NBIOT-半成品机电同步测试(读取)',
     			'18':'NBIOT-半成品上线次数测试',
     			'19':'NBIOT-成品机电同步测试(设置)',
     			'20':'NBIOT-成品机电同步测试(读取)',
     			'21':'NBIOT-成品上线次数测试',
     			'22':'NBIOT-初始化测试',
     			'23':'NBIOT-装箱',
     			'24':'NBIOT-PCB_a功能项目(阀控 )',
     			'25':'NBIOT-成品阀测试',
     			'26':'传感器-整机机电同步测试',
     			'10001':'NBIOT整表初值设置',
     			'10002':'NBIOT整表机电同步测试'
     		},
     		'UserRole':{
				'-1':'产线测试员',
				'1':'系统管理员',
				'2':'品管',
				'3':'维修员',
				'4':'生产管理',
				'5':'水道管理员',
				'6':'燃气表管理员',
				'7':'电磁水表管理员'
     			// '0':'系统管理员',
     			// '1':'品管',
     			// '2':'维修员',
     			// '3':'生产管理',
     			// '4':'水道测试员',
     			// '5':'燃气表测试员'
     		},
     		'RepairState':{
     			'0':'未执行',
     			'2':'待审核',
     			'1':'完成',
     			'3':'撤销',
     			'4':'并单'
     		},
     		'TestRezult':{
     			'0':'未执行',
     			'1':'未通过',
     			'2':'通过'
     		},     		
     		'BillStatus':{
     			'OPEN':'打开',
     			'CLOSED':'关闭'
     		},     		
     		'BussType':{
     			'in_wo_':'工单入库',
     			'in_po_':'采购入库',
     			'in_rma_':'回收入库',
     			'in_ln_':'借料入库',
     			'in_tr_':'移库入库',
     			'in_sws_':'换货入库',
     			'in_dw_':'拆解入库',
     			'in_other':'入库其他',
     			'out_so_':'销售出库',
     			'out_wo_':'工单领料',
     			'out_npi_':'非工单领料',
     			'out_rtv_':'退货发单',
     			'out_ln_':'借料出库',
     			'out_tr_':'移库出库',
     			'out_sws_':'换货出库',
     			'out_dw_':'拆解出库',
     			'out_other':'出库其他'
     		},
     		'InOutType':{
     			'in':'入库',
     			'out':'出库'
     		},
     		'updateDire':{
     			'1':'相同',
     			'2':'差异',
     			'3':'新增',
     			'4':'删除',
     			'5':'手工',
     			'6':'固定'
     		},
			'diffState':{
				'0':'未计算',
				'1':'正常',
				'2':'冲突',
				'3':'参数有误'
			},
			'areaCheckState':{
				'1':'未审核',
				'2':'已审核	'
			},
			'mesPd':{
     			'9,10,11,12':'9,10,11,12',
				'3,1,1,1':'3,1,1,1',
				'5,7,7,7':'5,7,7,7',
				'14,16,16,16':'14,16,16,16',
				'18,20,20,20':'18,20,20,20'
			},
			'readingType':{
     			'1':'非直传',
				'2':'直传'
			},

			'converMesModuleCommType' : {
				'1':'LoRa',
     			'2':'FSK'
			},
			'waterCompanyReadingDate' : {
				"1":"4、10、16、22、28",
				"2":"7、13、19、25、1",
				"3":"7、13、19、25、1",
				"4":"7、13、19、25、1",
				"5":"8、14、20、26、2",
				"6":"9、15、21、27、3",
				"7":"10、16、22、28、4",
				"8":"11、17、23、29、5",
				"9":"12、18、24、30、6",
				"10":"13、19、25、1、7",
				"11":"14、20、26、2、8",
				"12":"15、21、27、3、9",
				"13":"16、22、28、4、10",
				"14":"17、23、29、5、11",
				"15":"18、24、30、6、12",
				"16":"19、25、1、7、13",
				"17":"20、26、2、8、14",
				"18":"21、27、3、9、15",
				"19":"22、28、4、10、16",
				"20":"23、29、5、11、17",
				"21":"24、30、6、12、18",
				"22":"25、1、7、13、19",
				"23":"26、2、8、14、20",
				"24":"27、3、9、15、21",
				"25":"28、4、10、16、22",
				"26":"29、5、11、17、23",
				"27":"30、6、12、18、24",
				"28":"1、7、13、19、25",
				"29":"2、8、14、20、26",
				"30":"3、9、15、21、27",
				"31":"4、10、16、22、28",
				"待定":"4、10、16、22、28",
				"直传":"1、2、3、4、5、6、7、8、9、10、11、12、13、14、15、16、17、18、19、20、21、22、23、24、25、26、27、28、29、30、31"
			}
     	},
    	convertDiffStateValue:function(value){
    		if(value==0){
    			return '<font>未计算</font>';
    		}
    		if(value==1){
    			return '<font color="green">正常</font>';
    		}
    		if(value==2){
    			return '<font color="red">冲突</font>';
    		}
    		if(value==3){
    			return '<font color="orange">参数有误</font>';
    		}
    		return '<font color="red">未知值</font>';
    	},
		 converAreaCheckValue:function(value){
     		if (value==1) {
				return '<font>未审核</font>';
			}
     		if (value == 2){
				return '<font color="green">已审核</font>';
			}
			 return '<font>未知值</font>';
		 },
		 converDiffValue:function(value){
			 if (value==1) {
				 return '<font>正常</font>';
			 }
			 if (value == 2){
				 return '<font color="red">冲突</font>';
			 }
			 if (value == 3){
				 return '<font color="red">参数有误</font>';
			 }
			 return '<font >未计算</font>';
		 },
		 converReadingTypeValue:function(value){
			 if (value==1) {
				 return '<font>非直传</font>';
			 }
			 if (value == 2) {
				 return '<font >直传</font>';
			 }
			 return '<font color="red">未知值</font>';
		 },
		 converMesModuleCommTypeValue:function(value){
			 if (value==1) {
				 return '<font>LoRa</font>';
			 }
			 if (value == 2) {
				 return '<font >FSK</font>';
			 }
			 return '<font color="red">未知值</font>';
		 },
    	convertUpdateDireValue:function(value){
    		if(value==1){
    			return '<font color="green">相同</font>';
    		}
    		if(value==2){
    			return '<font color="red">差异</font>';
    		}
    		if(value==3){
    			return '<font color="orange">新增</font>';
    		}
    		if(value==4){
    			return '<font color="red">删除</font>';
    		}
    		if(value==5){
    			return '<font color="orange">手工</font>';
    		}
    		if(value==6){
    			return '<font color="orange">固定</font>';
    		}
    		return '<font color="red">未知值</font>';
    	},
    	convertResultValue:function(value){
    		if(value==0){
    			return '<font>未执行</font>';
    		}
    		if(value==1){
    			return '<font color="red">未通过</font>';
    		}
    		if(value==2){
    			return '<font color="green">通过</font>';
    		}
    		if(value==3){
    			return '<font color="orange">维修中</font>';
    		}
    		if(value==4){
    			return '<font color="orange">维修完成</font>';
    		}
    		if(value==11){
    			return '<font color="green">正常</font>';
    		}
    		if(value==12){
    			return '<font color="orange">异常</font>';
    		}
    		return '<font color="red">未知值</font>';
    	},
    	convertRepairStateValue:function(value){
    		if(value==0){
    			return '<font>未执行</font>';
    		}
    		if(value==1){
    			return '<font color="green">完成</font>';
    		}
    		if(value==2){
    			return '<font color="orange">待审核</font>';
    		}
    		if(value==3){
    			return '<font color="orange">撤销</font>';
    		}
    		if(value==4){
    			return '<font color="green">并单</font>';
    		}
    		return '<font color="red">未知值</font>';
    	},
    	convertSyncStateValue:function(value){
    		if(value==0){
    			return '<font color="green">通过</font>';
    		}
    		if(value==1){
    			return '<font color="red">A1告警</font>';
    		}
    		if(value==2){
    			return '<font color="red">A3告警</font>';
    		}
    		if(value==3){
    			return '<font color="red">差值</font>';
    		}
    		return '<font color="red">未知值</font>';
    	},
    	convertTestItemValue:function(value){
    		var returnValue=manageApp.utils.Model.constants[manageApp.utils.Model.constantName_TestItem][value];
    		if(returnValue!=null && 
    		   returnValue!='' && 
    		   typeof(reValue) == "undefined"){
    		   	return returnValue;
    		}
    		return '<font color="red">未知值</font>';			
    	},
    	convertUserRole:function(value){
    		var returnValue=manageApp.utils.Model.constants[manageApp.utils.Model.constantName_UserRole][value];
    		if(returnValue!=null && 
    		   returnValue!='' && 
    		   typeof(reValue) == "undefined"){
    		   	return returnValue;
    		}
    		return '<font color="red">未知值</font>';			
    	},
		 convertMenuTypeValue:function(value){
			 var returnValue=manageApp.utils.Model.constants['menuType'][value];
			 if(returnValue!=null &&
				 returnValue!='' &&
				 typeof(reValue) == "undefined"){
				 return returnValue;
			 }
			 return '';
		 },
    	getConstantStore:function(name){
    		var storeDate=[];
    		var dateMap=manageApp.utils.Model.constants[name];
    		for(var key in dateMap){
    			storeDate.push({'name':key+'-'+dateMap[key],
    							'value':key});
    		}
    		console.log(storeDate);
    		return Ext.create('Ext.data.Store', {
								fields : ['name', 'value'],
								data : storeDate
							})
    	},
		 getConstantStore2:function(name){
			 var storeDate=[];
			 var dateMap=manageApp.utils.Model.constants[name];
			 for(var key in dateMap){
				 storeDate.push({'name':key,
					 'value':key});
			 }
			 console.log(storeDate);
			 return Ext.create('Ext.data.Store', {
				 fields : ['name', 'value'],
				 data : storeDate
			 })
		 },
		 getConstantStore3:function(name){
			 var storeDate=[];
			 var dateMap=manageApp.utils.Model.constants[name];
			 for(var key in dateMap){
				 storeDate.push({'name':dateMap[key],
					 'value':key});
			 }
			 console.log(storeDate);
			 return Ext.create('Ext.data.Store', {
				 fields : ['name', 'value'],
				 data : storeDate
			 })
		 },
    	getResultColumns:function(testItem){
    		var columns=[];
    		if(testItem=='0'){//'0':'写终端地址',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
    		}
    		if(testItem=='1'){//'1':'功能测试',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
		         columns.push({
							dataIndex : 'eeprom',
							text : 'eeprom',
							renderer : manageApp.utils.Model.convertResultValue
						});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
				columns.push({
							dataIndex : 'sleepElec',
							text : '休眠电流',
							renderer : manageApp.utils.Model.convertResultValue
						});// 休眠电流 0:保留 1:通过 2:未通过 11:正常 12:异常
    			columns.push({ dataIndex: 'sleepCurrent', text: '休眠电流值(uA)' });//sleepCurrent
				columns.push({
							dataIndex : 'sendElec',
							text : '发射电流',
							renderer : manageApp.utils.Model.convertResultValue
						});// 接收电流 0:保留 1:通过 2:未通过 11:正常 12:异常
				columns.push({ dataIndex: 'txCurrent', text: '发射电流值(mA)' });//rxCurrent
				columns.push({
							dataIndex : 'recElec',
							text : '接收电流',
							renderer : manageApp.utils.Model.convertResultValue
						});// 接收电流 0:保留 1:通过 2:未通过 11:正常 12:异常
    			
    			columns.push({ dataIndex: 'rxCurrent', text: '接收电流值(mA)' });//txCurrent
				columns.push({
							dataIndex : 'outNullMagnet',
							text : '无外磁测试',
							renderer : manageApp.utils.Model.convertResultValue
						});// 无外磁测试 0:保留 1:通过 2:未通过 11:正常 12:异常
				columns.push({
							dataIndex : 'outMagnet',
							text : '有外磁测试',
							renderer : manageApp.utils.Model.convertResultValue
						});// 有外磁测试 0:保留 1:通过 2:未通过 11:正常 12:异常
				columns.push({
							dataIndex : 'portH',
							text : '端口高测试',
							renderer : manageApp.utils.Model.convertResultValue
						});// 端口高测试 0:保留 1:通过 2:未通过 11:正常 12:异常
				columns.push({
							dataIndex : 'portL',
							text : '端口低测试',
							renderer : manageApp.utils.Model.convertResultValue
						});//端口低测试 0:保留 1:通过 2:未通过 11:正常 12:异常 
				columns.push({
							dataIndex : 'lowElec',
							text : '电压测试',
							renderer : manageApp.utils.Model.convertResultValue
						});//低电压测试 0:保留 1:通过 2:未通过 11:正常 12:异常 
				columns.push({ dataIndex: 'normalVoltage', text: '电压值(V)' });//正常电压
    		}
     		if(testItem=='2'){//'2':'读版本频偏',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
     			columns.push({ dataIndex: 'frequencyOffset', text: '频偏' });//频偏
	         	columns.push({ dataIndex: 'softVer', text: '软件版本  ' });//软件版本   
    			columns.push({ dataIndex: 'hardVer', text: '硬件版本  ' });//硬件版本  
    		}   		
      		if(testItem=='3'){//'3':'整机功耗',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
	    	     columns.push({ dataIndex: 'electricity', text: '整机功耗值(uA)' });//整机功耗
		         columns.push({
						dataIndex : 'electricityFlag',
						text : '整机功耗',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常

    		}     			
      		if(testItem=='4'){//'4':'终端参数初始化',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
//	         	columns.push({ dataIndex: 'softVer', text: '软件版本  ' });//软件版本   
//    			columns.push({ dataIndex: 'hardVer', text: '硬件版本  ' });//硬件版本 
    			columns.push({ dataIndex: 'initParam', text: '初始化参数  ' });//初始化参数
    		}	
      		if(testItem=='5'){//'5':'计数准确性测试',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
		        columns.push({ dataIndex: 'initReading', text: '机械初始值' });//机械初始值
		        columns.push({ dataIndex: 'reading', text: '机械最终值' });//机械最终值
		        columns.push({ dataIndex: 'initEleReading', text: '电子初始值' });//电子初始值
		        columns.push({ dataIndex: 'eleReading', text: '电子最终值' });//电子最终值
		        columns.push({ text: '机电误差(脉冲数)',
						       renderer :function(value,metaData,record){
						    	   if(record!=null){
						    		   return record.get('reading')-record.get('eleReading')
						    	   }					        		
					           }
		        	         });//机电误差
    		}	
  			if(testItem=='6'){//'6':'信号强度测试',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
    			columns.push({ dataIndex: 'rssi1', text: '信号强度1' });//信号强度1
       			columns.push({ dataIndex: 'rssi2', text: '信号强度2' });//信号强度2
    		}	
  			if(testItem=='7'){//'7':'终端时钟',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
    			columns.push({ dataIndex: 'moduleTime', text: '终端时间',xtype: 'datecolumn',format:'Y-m-d h:i:s' });//终端时间
    		}	
  			if(testItem=='8'){//'8':'自动校频功能测试',
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
		        columns.push({ dataIndex: 'frequencyOffsetCheck', text: '校频后频偏值' });//校频后频偏值
    		}	
   			if(testItem=='9'){//'9':'成品计数准确性测试'
		         columns.push({
						dataIndex : 'commTest',
						text : '通讯',
						renderer : manageApp.utils.Model.convertResultValue
					});// eeprom 0:保留 1:通过 2:未通过 11:正常 12:异常
		        columns.push({ dataIndex: 'initReading', text: '机械初始值' });//机械初始值
		        columns.push({ dataIndex: 'reading', text: '机械最终值' });//机械最终值
		        columns.push({ dataIndex: 'initEleReading', text: '电子初始值' });//电子初始值
		        columns.push({ dataIndex: 'eleReading', text: '电子最终值' });//电子最终值
		        columns.push({ text: '机电误差(脉冲数)',
						       renderer :function(value,metaData,record){
						    	   if(record!=null){
						    		   return record.get('reading')-record.get('eleReading')
						    	   }					        		
					           }
		        	         });//机电误差
    			
    		}
   			if(testItem=='10'){//'10':'维修'
    			
    		}
   			if(testItem=='11'){//'11':'电感值测试'
   				columns.push({ dataIndex: 'l1', text: '电感L1' });//电感L1
   				columns.push({ dataIndex: 'l2', text: '电感L2' });//电感L2
   				columns.push({ dataIndex: 'l3', text: '电感L3' });//电感L3
    		}
   			if(testItem=='12'){//'12':'整机功耗'
   				columns.push({ dataIndex: 'txCurrent', text: '静态电流' });//静态电流
    		}
   			if(testItem=='13' || testItem=='26'){//'13':'机电同步测试'//'26':'传感器-整机机电同步测试'
		        columns.push({ dataIndex: 'initReading', text: '机械初始值' });//机械初始值
		        columns.push({ dataIndex: 'reading', text: '机械最终值' });//机械最终值
		        columns.push({ dataIndex: 'initEleReading', text: '电子初始值' });//电子初始值
		        columns.push({ dataIndex: 'eleReading', text: '电子最终值' });//电子最终值
		        columns.push({ text: '机电误差(脉冲数)',
						       renderer :function(value,metaData,record){
						    	   if(record!=null){
						    		   return record.get('reading')-record.get('eleReading')
						    	   }					        		
					           }
		        	         });//机电误差
		        columns.push({ dataIndex: 'synState',  text: '同步状态' , renderer : manageApp.utils.Model.convertSyncStateValue });//同步状态
    		}
   			if(testItem=='14'){//'14':'PCB_a 功能项目'
   				columns.push({ dataIndex: 'eeprom', text: 'eeprom' });//eeprom
   				columns.push({ dataIndex: 'portH', text: '端口高' });//端口高
   				columns.push({ dataIndex: 'portL', text: '端口低' });//端口低
   				columns.push({ dataIndex: 'normalVoltage', text: '采样电压' });//采样电压
   				columns.push({ dataIndex: 'simId', text: '卡号' });//卡号
   				columns.push({ dataIndex: 'reedState', 
   										text: '干簧管状态' ,
   										renderer : manageApp.utils.Model.convertResultValue});//干簧管状态
   				columns.push({ dataIndex: 'txCurrent', text: '静态电流' });//静态电流
    		}
   			if(testItem=='15'){//'15':'整机功耗'
   				columns.push({ dataIndex: 'txCurrent', text: '静态电流' });//静态电流
    		}
   			if(testItem=='16'){//'16':'半成品机电同步测试(设置)'
		        columns.push({ dataIndex: 'initReading', text: '机械初始值' });//机械初始值
		        columns.push({ dataIndex: 'initEleReading', text: '电子初始值' });//电子初始值
    		}
   			if(testItem=='17'){//'17':'半成品机电同步测试(读取)'
		        columns.push({ dataIndex: 'reading', text: '机械最终值' });//机械最终值
		        columns.push({ dataIndex: 'eleReading', text: '电子最终值' });//电子最终值
		        columns.push({ text: '机电误差(脉冲数)',
				       renderer :function(value,metaData,record){
				    	   if(record!=null){
				    		   return record.get('reading')-record.get('eleReading')
				    	   }					        		
			           }
     	         });//机电误差
    		}
   			if(testItem=='18'){//'18':'半成品上线次数测试'
   				columns.push({ dataIndex: 'onLineTimes', text: '上线次数' });//上线次数
   				columns.push({ dataIndex: 'succOnLineTimes', text: '上线成功次数' });//上线成功次数
    		}
   			if(testItem=='19'){//'19':'半成品机电同步测试(设置)'
		        columns.push({ dataIndex: 'initReading', text: '机械初始值' });//机械初始值
		        columns.push({ dataIndex: 'initEleReading', text: '电子初始值' });//电子初始值
    		}
   			if(testItem=='20'){//'20':'半成品机电同步测试(读取)'
		        columns.push({ dataIndex: 'reading', text: '机械最终值' });//机械最终值
		        columns.push({ dataIndex: 'eleReading', text: '电子最终值' });//电子最终值
		        columns.push({ text: '机电误差(脉冲数)',
				       renderer :function(value,metaData,record){
				    	   if(record!=null){
				    		   return record.get('reading')-record.get('eleReading');
				    	   }					        		
			           }
     	         });//机电误差
    		}
   			if(testItem=='21'){//'21':'成品上线次数测试'
   				columns.push({ dataIndex: 'onLineTimes', text: '上线次数' });//上线次数
   				columns.push({ dataIndex: 'succOnLineTimes', text: '上线成功次数' });//上线成功次数
    		}
   			if(testItem=='22'){//'22':'初始化测试'
   				columns.push({ dataIndex: 'itf25', text: 'ITF25' });//ITF25
   				columns.push({ dataIndex: 'simId ', text: '卡号',
   						renderer:function(value,metaData,record){
 				    	   if(record!=null){
				    		   return ''+record.get('simId');
				    	   }
   						}
   				});//卡号
   				columns.push({ dataIndex: 'initParam', text: '初始化参数' });//初始化参数
	         	columns.push({ dataIndex: 'softVer', text: '软件版本' });//软件版本
    			columns.push({ dataIndex: 'hardVer', text: '硬件版本' });//硬件版本 
    		}
   			if(testItem=='23'){//'23':'NBIOT-装箱'
   				columns.push({ dataIndex: 'itf25', text: 'ITF25' });//ITF25
   				columns.push({ dataIndex: 'simId ', text: '卡号',
   						renderer:function(value,metaData,record){
 				    	   if(record!=null){
				    		   return ''+record.get('simId');
				    	   }
   						}
   				});//卡号
   				columns.push({ dataIndex: 'packNum', text: '箱号' });//箱号
    		}
   			if(testItem=='24'){//'24':'NBIOT-PCB_a功能项目(阀控 )'
   				columns.push({ dataIndex: 'eeprom', text: 'eeprom' });//eeprom
   				columns.push({ dataIndex: 'buzzer', 
   										text: '蜂鸣器',
   										renderer : manageApp.utils.Model.convertResultValue});//蜂鸣器
   				columns.push({ dataIndex: 'valveControl', 
   										 text: '阀控状态',
   										renderer : manageApp.utils.Model.convertResultValue});//阀控状态
   				columns.push({ dataIndex: 'normalVoltage', text: '采样电压' });//采样电压
   				columns.push({ dataIndex: 'simId', text: '卡号' });//卡号
   				columns.push({ dataIndex: 'reedState', 
   										text: '干簧管状态',
   										renderer : manageApp.utils.Model.convertResultValue});//干簧管状态
   				columns.push({ dataIndex: 'txCurrent', text: '静态电流' });//静态电流
    		}
   			if(testItem=='25'){//'25':'NBIOT-PCB_a功能项目(阀控 )'
   				columns.push({ dataIndex: 'openValve', 
   										 text: '开阀状态' ,
   										renderer : manageApp.utils.Model.convertResultValue});//开阀状态
   				columns.push({ dataIndex: 'closeValve', 
   										text: '关阀状态',
   										renderer : manageApp.utils.Model.convertResultValue});//关阀状态
    		}
   			if(testItem=='10001'){//'10001'：'NBIOT整表初值设置',
		        columns.push({ dataIndex: 'initReading', text: '机械初始值' });//机械初始值
		        columns.push({ dataIndex: 'initEleReading', text: '电子初始值' });//电子初始值
    		}
   			if(testItem=='10002'){//'10002'：'NBIOT整表机电同步测试'，
		        columns.push({ dataIndex: 'reading', text: '机械最终值' });//机械最终值
		        columns.push({ dataIndex: 'eleReading', text: '电子最终值' });//电子最终值
		        columns.push({ text: '机电误差(脉冲数)',
				       renderer :function(value,metaData,record){
				    	   if(record!=null){
				    		   return record.get('reading')-record.get('eleReading');
				    	   }					        		
			           }
     	         });//机电误差
		        columns.push({ dataIndex: 'normalVoltage', text: '电压值(V)' });//正常电压
    		}
    		return columns;
    	},
    	convertResultValue2:function(value){
    		if(value==11){
    			return '合格';
    		}
    		else{
    			return '不合格';
    		}
    	},
		 converWeekNumber:function (value) {
				 var returnValue=manageApp.utils.Model.constants[manageApp.utils.Model.constantName_WeekNumber][value];
				 if(returnValue!=null &&
					 returnValue!='' &&
					 typeof(reValue) == "undefined"){
					 return returnValue;
				 }
				 return '<font color="red">未知值</font>';
				 },
		 comFailMesTesIdName:function (value) {
			 if (value=="2"){
			 	return "终端"
			 } else if (value=="3"){
				return "中继器"
			 }else if (value=="null"){
				return ""
			 }
		 }
    	
     }
});