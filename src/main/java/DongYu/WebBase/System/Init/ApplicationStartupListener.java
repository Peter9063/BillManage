package DongYu.WebBase.System.Init;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.util.JSONUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

	private Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

	/**
	 * Spring 初始化后立刻执行的方法
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 //root application context
		 if (event.getApplicationContext().getParent() == null) {
			 logger.info("WebApp Context init OK!");
			 //全局JSONObject日期格式配置
			 String[] dateFormats = new String[] {"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"};
			 JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));

			 DateConverter dateConverter = new DateConverter();
			 dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"});
			 ConvertUtils.register(dateConverter, Date.class);
		 }
		 //mvc application context
		 if (event.getApplicationContext().getParent() != null) {
			 logger.info("WebApp MVC Context init OK!");
		 }
	}




}
