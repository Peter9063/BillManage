package DongYu.WebBase.Service.Tester;

import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Utils.ExcelReadUtils;
import DongYu.WebBase.Tester.PublicJUnit4ClassRunner;
import Yao.EBusiness.Entity.Orders;
import Yao.EBusiness.Service.OrderService;
import Yao.EBusiness.ServiceImp.OrderServiceImp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;


@RunWith(PublicJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/DongYu/WebBase/Tester/serviceTestConfig.xml"})
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class TestService {

	private Logger logger = LoggerFactory.getLogger(TestService.class);

//	@Autowired(required=true)
//	org.springframework.jdbc.core.JdbcTemplate jdbcTemplate ;

	@Autowired(required=true)
	OrderServiceImp service;

	@Test
	public void excelInput() throws Exception{

//		InputStream in =this.getClass().getClassLoader().getResourceAsStream("Yao/EBusiness/Test/orders.xlsx");
		System.out.println("=================================");

//		System.out.println(this.getClass().getResource("").getPath());
//		System.out.println(System.getProperty("user.dir")+"/src/main/webapp/WEB-INF/classes/Yao/EBusiness/Test/orders.xlsx");

		String fileName=System.getProperty("user.dir")+"/src/main/webapp/WEB-INF/classes/Yao/EBusiness/Test/orders.xls";
		FileInputStream excelFilein=new FileInputStream(fileName);

		service.inputOrders(excelFilein);

	}

	@Test
	public void excelOutput() throws Exception{


	}

}

