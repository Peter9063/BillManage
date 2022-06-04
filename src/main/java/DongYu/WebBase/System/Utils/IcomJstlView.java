package DongYu.WebBase.System.Utils;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

public class IcomJstlView extends InternalResourceView {
	  
    public boolean checkResource(Locale locale) throws Exception {  
        File file = new File(this.getServletContext().getRealPath("/")+getUrl());  
        return file.exists();//判断该jsp页面是否存在  
    } 

}
