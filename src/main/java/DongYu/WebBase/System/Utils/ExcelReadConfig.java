package DongYu.WebBase.System.Utils;

import net.sf.json.JSONObject;

public interface ExcelReadConfig {
	/**
	 * 特殊值转化
	 * @param key
	 * @param value
	 * @return
	 */
	public  Object beanPropertyValueProcessor(String key, Object value);
	
	/**
	 * 具体的dao保存
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public <T> JSONObject  daoSaveValueProcessor(T dto)  throws Exception;
	
	/**
	 * dto初始值设置
	 * @param dto
	 * @return
	 */
	public <T> T initDto(T dto);
}
