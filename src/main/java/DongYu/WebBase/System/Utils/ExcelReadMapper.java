package DongYu.WebBase.System.Utils;

public  class ExcelReadMapper <T> {
	
//  mapperingStr格式的定义;
//	"{'集中器无线地址':[{'proName':'ccr.meqRadioAddr'},{'proName':'ccrStation.mesRadioAddr'}]," +
//			"'集中器名称':[{'proName':'ccrStation.mesName'}]," +
//			"'集中器型号':[{'proName':'ccr.meqType'},{'proName':'ccrStation.mesType'}]," +
//			"'集中器软件版本':[{'proName':'ccr.meqVersion'},{'proName':'ccrStation.mesVersion'}]," +
//			"'集中器状态':[{'proName':'ccr.meqStatus'},{'proName':'ccrStation.mesStatus'}],"+
//			"'所属区域名称':[{'proName':'ccr.caiId'},{'proName':'ccrStation.caiId'}]," +
//			"'通信服务器IP地址':[{'proName':'ccr.meqCcrCserver'}]," +
//			"'通信服务器端口':[{'proName':'ccr.meqCcrCport'}]," +
//			"'全局服务器IP地址':[{'proName':'ccr.meqCcrGserver'}]," +
//			"'全局服务器端口':[{'proName':'ccr.meqCcrGport'}]," +
//			"'SIM号':[{'proName':'ccr.meqCcrTel'}]," +
//			"'安装地址':[{'proName':'ccr.meqLocation'},{'proName':'ccrStation.mesAddr'}]" +
//			"}";
	
	
	private String sheetName;
	private  T bean;
	private String mappingStr;
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public T getBean() {
		return bean;
	}
	public void setBean(T bean) {
		this.bean = bean;
	}
	public String getMappingStr() {
		return mappingStr;
	}
	public void setMappingStr(String mappingStr) {
		this.mappingStr = mappingStr;
	}
	
	
	
}
