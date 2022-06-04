package DongYu.WebBase.System.Utils;


import DongYu.WebBase.System.Entity.Site;

import java.util.HashMap;
import java.util.Map;

public class AppConstant {
	
	public static final String FullDateFormat="yyyy-MM-dd HH:mm:ss";
	
	public static final String ShortDateFormat="yyyy-MM-dd";

	public static final Integer TestNoExecute = 0;
	public static final Integer TestNoPass = 1;
	public static final Integer TestPass = 2;
	public static final Integer DeviceRepair = 3;
	public static final Integer DeviceRepairFinished = 4;
	public static final Integer TestItemNormal = 11;
	public static final Integer TestItemUNNormal = 12;
	
	public static final Integer TestItemFunTest = 1;//'功能测试'
	
	public static final Integer UserRoleAdmin = 0;//'系统管理员'
	public static final Integer UserRoleQC = 1;//'品管'
	public static final Integer UserRoleREP = 2;//'维修员'
	public static final Integer UserRoleGRE = 3;//'生产管理'
	
	public static final Integer RepairStateUnDo = 0;//'未执行'
	public static final Integer RepairStateDo = 1;//'完成'
	public static final Integer RepairStateWaiteCheck = 2;//'未审核'
	public static final Integer RepairStateCancel= 3;//'撤消'
	public static final Integer RepairStateMergeFlag = 4;//'并单'
	
	public static final String TestItemName = "TestItem";
	public static final String TestRezultName = "TestRezult";
	public static final String UpdateDire = "UpdateDire";

	public static final String DIFFSTATE = "diffState";
	public static final String AREACHECKSTATE = "areaCheckState";
	public static final String READINGTYPE = "readingType";
	public static final String MESMODULECOMMTYPE="mesModuleCommType";

	public static final Integer CONCENTRATORPOINTDISTANCEUNDIFF= 0;//集中器未计算
	public static final Integer CONCENTRATORPOINTDISTANCEDIFF= 1;//集中器冲突
	public static final Integer CONCENTRATORPOINTDISTANCESVALIDATION= 2;//集中器校验

	public static final Integer AREACHECKFALSE= 1;// 未确认
	public static final Integer AREACHECKTRUE= 2;// 已确认

	public static final Integer PNUsageKindCurrentVer= 0;
	public static final Integer PNUsageKindTempVer= 1;
	public static final Integer PNUsageKindTempCalculateVer= 2;
	
	public static final Integer PNUsageUpdateDireSame= 1;
	public static final Integer PNUsageUpdateDireDiff= 2;
	public static final Integer PNUsageUpdateDireAdd= 3;
	public static final Integer PNUsageUpdateDireDel= 4;
	public static final Integer PNUsageUpdateDireManual= 5;
	public static final Integer PNUsageUpdateDireFix= 6;

	//约定 数据启用 1   逻辑删除 2  //2019/10/09
	/**
	 * 约定 数据启用 1   逻辑删除 2
	 */
	public static final Integer ENABLE = 1;
	public static final Integer DISABLE = 2;
	
	
	public static final Map<String,Map<Integer,String>> Constants=new HashMap<String,Map<Integer,String>>();

	public static final Map<String, Site> Sites=new HashMap<String,Site>();
	static  
    { 
		Map<Integer,String> testItem=new HashMap<Integer,String>();
		testItem.put(0, "写终端地址");
		testItem.put(1, "功能测试");
		testItem.put(2, "读版本频偏");
		testItem.put(3, "整机功耗");
		testItem.put(4, "终端参数初始化");
		testItem.put(5, "半成器计数准确性测试");
		testItem.put(6, "信号强度测试");
		testItem.put(7, "终端时钟");
		testItem.put(8, "自动校频功能测试");
		testItem.put(9, "成品计数准确性测试");
		testItem.put(10, "维修");
		testItem.put(11, "传感器-电感值测试");
		testItem.put(12, "传感器-整机功耗");
		testItem.put(13, "传感器-机电同步测试");
		testItem.put(14, "NBIOT-PCB_a功能项目");
		testItem.put(15, "NBIOT-整机功耗");
		testItem.put(16, "NBIOT-半成品机电同步测试(设置)");
		testItem.put(17, "NBIOT-半成品机电同步测试(读取)");
		testItem.put(18, "NBIOT-半成品上线次数测试");
		testItem.put(19, "NBIOT-成品机电同步测试(设置)");
		testItem.put(20, "NBIOT-成品机电同步测试(读取)");
		testItem.put(21, "NBIOT-成品上线次数测试");
		testItem.put(22, "NBIOT-初始化测试");
		testItem.put(23, "NBIOT-初始化测试");
		testItem.put(24, "NBIOT-PCB_a功能项目(阀控 )");
		testItem.put(25, "NBIOT-成品阀测试");
		testItem.put(10001, "NBIOT整表初值设置");
		testItem.put(10002, "NBIOT整表机电同步测试");
		
		Constants.put("TestItem", testItem);
		Map<Integer,String> TestRezult=new HashMap<Integer,String>();
		TestRezult.put(0, "未执行");
		TestRezult.put(1, "未通过");
		TestRezult.put(2, "通过");
		TestRezult.put(3, "维修中");
		TestRezult.put(4, "维修完成");
		Constants.put("TestRezult", TestRezult);
		Map<Integer,String> TestValue=new HashMap<Integer,String>();
		TestValue.put(0, "未执行");
		TestValue.put(1, "未通过");
		TestValue.put(2, "通过");
		TestValue.put(3, "维修中");
		TestValue.put(4, "维修完成");
		TestValue.put(11, "正常");
		TestValue.put(12, "异常");
		Constants.put("TestValue", TestValue);
		Map<Integer,String> UpdateDire=new HashMap<Integer,String>();
		UpdateDire.put(0, "未知值");
		UpdateDire.put(1, "相同");
		UpdateDire.put(2, "差异");
		UpdateDire.put(3, "新增");
		UpdateDire.put(4, "删除");
		UpdateDire.put(5, "手动");
		UpdateDire.put(6, "固定");
		Constants.put("UpdateDire", UpdateDire);
		Map<Integer,String> diffState=new HashMap<Integer,String>();
		diffState.put(null,"未计算");
		diffState.put(0, "未计算");
		diffState.put(1, "正常");
		diffState.put(2, "冲突");
		diffState.put(3, "参数有误");
		Constants.put("diffState", diffState);
		Map<Integer,String> areaCheckState=new HashMap<Integer,String>();
		areaCheckState.put(1, "未审核");
		areaCheckState.put(2, "已审核");
		Constants.put("areaCheckState", areaCheckState);
		Map<Integer,String> readingType=new HashMap<Integer,String>();
		readingType.put(1, "非直传");
		readingType.put(2, "直传");
		Constants.put("readingType", readingType);
		Map<Integer,String>  mesModuleCommType=new HashMap<Integer,String>();
		mesModuleCommType.put(1, "LoRa");
		mesModuleCommType.put(2, "FSK");
		Constants.put("mesModuleCommType", mesModuleCommType);
	}

	public static String getCmdTypeText(Integer value) {
		return null;
	}
}
