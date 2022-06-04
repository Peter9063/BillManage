package DongYu.WebBase.System.Shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({UnauthorizedException.class})
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("success", false);
        model.put("message", "无权限！");

        return new ModelAndView("jsonView", model);
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ModelAndView handleUnknownAccountException(UnknownAccountException e){
        Map<String, Object> model = new HashMap<String, Object>();
        logger.error("error:",e);
        model.put("success", false);
        model.put("message", "无此用户！");
        return new ModelAndView("jsonView", model);
    }
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView handleAuthorizationException(AuthorizationException e){
        ModelAndView mv = new ModelAndView();
        logger.error("error:",e);
        /**
         * 可以考虑把异常分类处理
         */
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", false);
        model.put("message", "session失效 重新登陆！");
        return new ModelAndView("jsonView", model);
    }


    private Logger log = LoggerFactory.getLogger(this.getClass());

//    /**
//     * 拦截业务异常
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(BussinessException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ErrorTip notFount(BussinessException e) {
//        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
//        getRequest().setAttribute("tip", e.getMessage());
//        log.error("业务异常:", e);
//        return new ErrorTip(e.getCode(), e.getMessage());
//    }

    /**
     * 用户未登录
     *
     * @author fengshuonan
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/login.html";
    }

//    /**
//     * 账号被冻结
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(DisabledAccountException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String accountLocked(DisabledAccountException e, Model model) {
//        String username = getRequest().getParameter("username");
//        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
//        model.addAttribute("tips", "账号被冻结");
//        return "/login.html";
//    }

//    /**
//     * 账号密码错误
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(CredentialsException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String credentials(CredentialsException e, Model model) {
//        String username = getRequest().getParameter("username");
//        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号密码错误", getIp()));
//        model.addAttribute("tips", "账号密码错误");
//        return "/login.html";
//    }
//
//    /**
//     * 验证码错误
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(InvalidKaptchaException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String credentials(InvalidKaptchaException e, Model model) {
//        String username = getRequest().getParameter("username");
//        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "验证码错误", getIp()));
//        model.addAttribute("tips", "验证码错误");
//        return "/login.html";
//    }

//    /**
//     * 无权访问该资源
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(UndeclaredThrowableException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public ErrorTip credentials(UndeclaredThrowableException e) {
//        getRequest().setAttribute("tip", "权限异常");
//        log.error("权限异常!", e);
//        return new ErrorTip(BizExceptionEnum.NO_PERMITION);
////    }
//
//    /**
//     * 拦截未知的运行时异常
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ErrorTip notFount(RuntimeException e) {
//        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
//        getRequest().setAttribute("tip", "服务器未知运行时异常");
//        log.error("运行时异常:", e);
//        return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
//    }

    /**
     * session失效的异常拦截
     *
     * @author dh
     * @Date 2017/6/7 21:02
     */
    @ExceptionHandler(InvalidSessionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String sessionTimeout(InvalidSessionException e, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("tips", "session超时");
        assertAjax(request, response);
        return "/login.html";
    }

    /**
     * session异常
     *
     * @author dh
     * @Date 2017/6/7 21:02
     */
    @ExceptionHandler(UnknownSessionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String sessionTimeout(UnknownSessionException e, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("tips", "session超时");
        assertAjax(request, response);
        return "/login.html";
    }

    private void assertAjax(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有，x-requested-with
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
        }
    }

}
