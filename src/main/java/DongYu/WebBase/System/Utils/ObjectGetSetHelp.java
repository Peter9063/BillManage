package DongYu.WebBase.System.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ObjectGetSetHelp {
	
	private static Logger logger = LoggerFactory.getLogger(ObjectGetSetHelp.class);
	/**
	 * 返射设置值
	 * @param record
	 * @param methodName
	 * @param value
	 */
	public static <T> void setValue(T record, String methodName, Object value) {
		String setFlowResultMethodName=methodName;
		Class<?> clazz = record.getClass();
		Field setFlowResultField;
		try {
			setFlowResultField = clazz.getDeclaredField(setFlowResultMethodName);
			setFlowResultField.setAccessible(true);
			setFlowResultField.set(record,value);
		} catch (NoSuchFieldException e) {
			logger.error("error",e);
		} catch (IllegalArgumentException | IllegalAccessException ex){
			logger.error("error",ex);
		}
		catch (SecurityException se) {
			logger.error("error",se);
		}
	}
	
	/**
	 * 返射获得属性值
	 * @param record
	 * @param methodName
	 * @return
	 */
	public  static <T> Object getValue(T record, String methodName) {
		String setFlowResultMethodName=methodName;
		
		Class<?> clazz = record.getClass();
		Field getFlowResultField;
		try {
			getFlowResultField = clazz.getDeclaredField(setFlowResultMethodName);
			getFlowResultField.setAccessible(true);
			return getFlowResultField.get(record);
		} catch (NoSuchFieldException e) {
			logger.error("error",e);
		} catch (IllegalArgumentException ex){
			logger.error("error",ex);
		}
		catch (SecurityException se) {
			logger.error("error",se);
		} catch (IllegalAccessException e) {
			logger.error("error",e);
		}
		return null;
	}

}
