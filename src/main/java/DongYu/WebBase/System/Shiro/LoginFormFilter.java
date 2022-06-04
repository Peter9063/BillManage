package DongYu.WebBase.System.Shiro;


import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("LoginFormFilter")
public class LoginFormFilter extends FormAuthenticationFilter {

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException, IOException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (this.isAjax(request)) {// ajax请求
            Map<String, String> resultMap = new HashMap<String, String>();
//            LoggerUtils.debug(getClass(), "当前用户没有登录，并且是Ajax请求！");
            httpServletResponse.setCharacterEncoding("UTF-8");
            WebMessage webMessage = new WebMessage();
            webMessage.setSuccess(true);
            webMessage.setMessage("登录认证失效，请重新登录!");
            httpServletResponse.getWriter().write(
                    JSONObject.fromObject(webMessage).toString());
        } else {//如果是普通请求进行重定向
            httpServletResponse.sendRedirect("/DongYuWebBase/manage/login.jsp");
        }


//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json");
//        WebMessage webMessage = new WebMessage();
//        webMessage.setSuccess(true);
//        webMessage.setMessage("登录认证失效，请重新登录!");
//        httpServletResponse.getWriter().write(
//                JSONObject.fromObject(webMessage).toString()
//        );
        return false;
    }

    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
//            LoggerUtils.debug(CLAZZ, "当前请求为Ajax请求");
            return Boolean.TRUE;
        }
//        LoggerUtils.debug(CLAZZ, "当前请求非Ajax请求");
        return Boolean.FALSE;

    }

}
