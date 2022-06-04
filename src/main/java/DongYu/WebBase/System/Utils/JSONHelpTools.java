package DongYu.WebBase.System.Utils;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONHelpTools {
	
	private static Logger logger = LoggerFactory.getLogger(JSONHelpTools.class);
	
	public static final String dateFormat="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 属性字段转小写
	 * @param jsonStr
	 * @return
	 */
	@Deprecated
    public static String propertieToLower(String jsonStr){
        String regex = "\"[a-zA-Z0-9]+\":";
        
        Pattern pattern = Pattern.compile(regex);
        StringBuffer sb = new StringBuffer();
        // 方法二：正则替换
        Matcher m = pattern.matcher(jsonStr);
        while (m.find()) {
            m.appendReplacement(sb, m.group().toLowerCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
    
    /**
     * 格式华成bean
     * @param jsonStr
     * @param beanClass
     * @param classMap
     * @return
     */
    public static Object toBean(String jsonStr, Class beanClass, Map classMap){
    	Object returnValue=null;
    	if(jsonStr!=null && !jsonStr.equals("")){
			String[] dateFormats = new String[] {dateFormat};
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));  
    		
    		logger.debug(jsonStr);
    		JSONObject jsonObj=JSONObject.fromObject(jsonStr);

			returnValue=jsonObj.toBean(jsonObj, beanClass,classMap);
    	}
    	
    	return returnValue;
    }
    
    /**
     * 格式化为jsonStr
     * @return
     */
    public static String toJson(Object obj){
    	String returnValue=null;
    	if(obj!=null){
    	    JsonConfig jsonConfig = new JsonConfig();  
    	    jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor(){
    	          
    	 	    @Override  
    	 	    public Object processArrayValue(Object paramObject,  
    	 	            JsonConfig paramJsonConfig) {  
    	 	        return process(paramObject);  
    	 	    }  
    	 	  
    	 	    @Override  
    	 	    public Object processObjectValue(String paramString, Object paramObject,  
    	 	            JsonConfig paramJsonConfig) {  
    	 	        return process(paramObject);  
    	 	    }  
    	 	      
    	 	    private Object process(Object value){  
    	 	        if(value instanceof Date){    
    	 	            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,Locale.CHINA);    
    	 	            return sdf.format(value);  
    	 	        }    
    	 	        return value == null ? "" : value.toString();    
    	 	    }  
    	    	
    	    });  
    	    
    	    returnValue=JSONObject.fromObject(obj,jsonConfig).toString();
    		logger.debug(returnValue);
    	}
    	return returnValue;
    }
	    
	    


    public static Object toBeanByMapper(String string,Class clazz){
		ObjectMapper mapper = new ObjectMapper();
		if (string!=null&&string!="") {
			try {
				Object object = mapper.readValue(string, clazz);
				return object;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return null;
	}
    public static Object toBeanByMapper(String string,TypeReference clazz){
		ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        if (string!=null&&string!="") {
            try {
                Object object = mapper.readValue(string, clazz);
                return object;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
	    
}
