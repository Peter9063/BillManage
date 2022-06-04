package DongYu.WebBase.System.Service.Exception.SysBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler4ExtjsEnv  implements HandlerExceptionResolver{
	
	private  Logger logger = LoggerFactory.getLogger(ExceptionHandler4ExtjsEnv.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		logger.error("error:",ex);
		
		/**
		 * 可以考虑把异常分类处理
		 */
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("success", false);
		model.put("message", "系统处理遇到错误，请与系统管理员联系！");
		if(ex instanceof org.springframework.dao.DuplicateKeyException) {
			model.put("message", "系统不允许保存重复记录！");
		}

		
		return new ModelAndView("jsonView", model);
	}

}
