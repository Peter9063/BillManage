package DongYu.WebBase.System.Utils;


import DongYu.WebBase.System.Entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.text.SimpleDateFormat;
import java.util.*;

public class WebUtil {
	public static final String lang_zh = "zh_CN";
	public static final String lang_en = "en_US";
	
	public static final String attr_lang  ="_attr_language";
	public static final String attr_sort  ="_attr_sort";
	public static final String attr_page = "_attr_page";
	public static final String attr_request = "_attr_request";
	public static final String attr_response = "_attr_response";
	
	

	public static String messageFile ;//= "messages_";
	

	public static String getMessageFile() {
		return messageFile;
	}


	public static void setMessageFile(String messageFile) {
		if(messageFile!=null){
			WebUtil.messageFile = messageFile+"_";
		}		
	}


	private static WebApplicationContext ctx = null; 
	
	private static Map<String, Properties> pps = new HashMap<String, Properties>();
	

	/**
	 * 获取当前用户
	 * @return
	 */
	public static User getCurrentUser() {
		User user = null;
		try{
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			if(requestAttributes.getAttribute("user",RequestAttributes.SCOPE_SESSION)!=null){
				 user = (User) requestAttributes.getAttribute("user",RequestAttributes.SCOPE_SESSION);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
	}
	
	public static Object getReqAttr(String key) {
		RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
		return attrs.getAttribute(key,  RequestAttributes.SCOPE_REQUEST);
	}
	
	/**
	 * 格式化
	 * flag:true 是00:00:00
	 * flag:false是23:59:59
	 * @return
	 */
	public static Date formatZeroTime(Date date,boolean flag){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		if(flag==true){
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
		}
		else{
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE,59);
			cal.set(Calendar.SECOND,59);
		}
		return cal.getTime();
	}
	
	
	public static void main(String[] args) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();
		
		System.out.println(sd.format(formatZeroTime(d, false)));
	}

//
//	/**
//	 * 获取资源文件
//	 * @param key
//	 * @return
//	 */
//	public static String getLangMessage(String key) {
//		return getLangMessage( key, null);
//	}
//	
//	/**
//	 * 获取资源文件
//	 * @param key
//	 * @param values
//	 * @return
//	 */
//	public static String getLangMessage( String key, Object[] values) {
//		String lang = getLang();
//		Properties props = getPropertie(lang);
//		String v = props.getProperty(key);
// 		if (values != null) {
//			v = java.text.MessageFormat.format(v, values);
//		}
//		return v;
//	}
//	
//	
//	/**
//	 * 获取资源文件
//	 * @param key
//	 * @param values
//	 * @param lang
//	 * @return
//	 */
//	public static String getLangMessage( String key, Object[] values, String lang) {
//		if(CommonUtil.isNullString(lang)) {
//			lang = getLang();
//		}
//		Properties props = getPropertie(lang);
//		String v = props.getProperty(key);
// 		if (values != null) {
//			v = java.text.MessageFormat.format(v, values);
//		}
//		return v;
//	}
//	
//	/**
//	 * 获取资源文件
//	 * @param key
//	 * @return
//	 */
//	public static String getLangMessageBylang(String key, String lang) {
//		return getLangMessageBylang( key, null, lang);
//	}
//	
//	/**
//	 * 获取资源文件
//	 * @param key
//	 * @param values
//	 * @return
//	 */
//	public static String getLangMessageBylang( String key, Object[] values, String lang) {
////		String lang = getLang();
//		Properties props = getPropertie(lang);
//		String v = props.getProperty(key);
// 		if (values != null) {
//			v = java.text.MessageFormat.format(v, values);
//		}
//		return v;
//	}
//	
//	

//	
//	
//	/**
//	 * 
//	 * @return the current sort, or <code>null</code> if not found
//	 */
//	public static JSONArray getSort() {
//		RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
//		Object obj = attrs.getAttribute(attr_sort,  RequestAttributes.SCOPE_REQUEST);
//		if(obj == null)
//			return null;
//		return JSONArray.fromObject(obj.toString());
//	}
//	
//	public static Page getPage() {
//		RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
//		Object obj = attrs.getAttribute(attr_page,  RequestAttributes.SCOPE_REQUEST);
//		if(obj == null)
//			return new Page(null,null);
//		return (Page)obj;
//	}
//	public static RowBounds getRowBounds() {
//		Page page = getPage();
//		if(null != page.getStart() && null != page.getLimit())
//			return new RowBounds(Integer.parseInt(page.getStart()),Integer.parseInt(page.getLimit()));
//		else 
//			return null;
//	}
//	
//	public static String getLang(){
//		return getLang(null);
//	}
//	
//	public static String getLangByReq(HttpServletRequest request){
//		String lang=null;
//		if(request!=null){
//			Cookie[] cookies=request.getCookies();
//			if(cookies!=null){
//				for(Cookie cookie:cookies){
//					if(cookie.getName().equalsIgnoreCase("locale")){
//						lang=cookie.getValue();
//						break;
//					}
//				}
//			}
//		}
//		return localeCategory(lang);
//	}
//	
//	public static String getLang(String lang) {
////		String lang=null;
//		if(lang==null){
//			try{
//				if(RequestContextHolder.currentRequestAttributes()!=null){
//					RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
//					lang = attrs.getAttribute(attr_lang,  RequestAttributes.SCOPE_REQUEST).toString();
//					
//					HttpServletRequest request = (HttpServletRequest) attrs.getAttribute(attr_request,  RequestAttributes.SCOPE_REQUEST);
//					Cookie[] cookies=request.getCookies();
//					if(cookies!=null){
//						for(Cookie cookie:cookies){
//							if(cookie.getName().equalsIgnoreCase("locale")){
//								lang=cookie.getValue();
//								break;
//							}
//						}
//					}
//				}
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		
//		return localeCategory(lang);
//	}
//	
//	public static String localeCategory(String lang){
//		if (lang == null || "".equals(lang)|| "null".equals(lang)) {
//			return lang_zh;
//		} else if (lang.toLowerCase().contains("zh")) {
//			return lang_zh;
//		} else{
//			return lang_en;
//		}
//	}
//	
//	public static Locale localeCategory(Locale lang){
//		Locale returnValue;
//		if(lang!=null){
//			returnValue=new Locale(localeCategory(lang.getLanguage()));
//		}
//		else{
//			returnValue=new Locale(lang_zh);
//		}
//		return returnValue;
//	}
//
//	public static void clearPropertie(){
//		pps.clear();
//	}
//	
//	private static Properties getPropertie(String lang) {
//		if (lang == null || lang.isEmpty())
//			lang = lang_zh;
//		String filename = messageFile + lang;
//		if (!pps.containsKey(filename)) {
//			Properties props = new Properties();
//			InputStream is = Message.class.getResourceAsStream("/" + filename
//					+ ".properties");
//			try {
//				props.load(is);
//				is.close();
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			pps.put(filename, props);
//		}
//		return pps.get(filename);
//	}
	
	
//	public static Object getBean(String beanName){
//		HttpServletRequest req = (HttpServletRequest)getReqAttr(attr_request);
//		if(ctx == null)
//			ctx = 
//	            WebApplicationContextUtils.
//	                getRequiredWebApplicationContext(
//	                		req.getServletContext());
//		
//	    return ctx.getBean(beanName);
//	}
	
	/**
	 * 获得当前WebApp的根访问路径
	 * @return
	 */
//	public static String getWebAppURL(){
//		String urlBasePath=null;
//		if(RequestContextHolder.currentRequestAttributes()!=null){		
//			RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
//			HttpServletRequest request = (HttpServletRequest) attrs.getAttribute(WebUtil.attr_request,  RequestAttributes.SCOPE_REQUEST);
//			urlBasePath="http://"+request.getServerName();
//			if(request.getServerPort()!=80){
//				urlBasePath=urlBasePath+":"+request.getServerPort();
//			}
//			urlBasePath=urlBasePath+request.getContextPath()+"/";			
//		}
//		return urlBasePath;
//	}

}
