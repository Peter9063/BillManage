package DongYu.WebBase.Service.Tester;

import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.Tester.PublicJUnit4ClassRunner;
import Yao.EBusiness.Entity.Orders;
import Yao.EBusiness.Service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;


@RunWith(PublicJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/DongYu/WebBase/Tester/serviceTestConfig.xml"})
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class TestService {

	private Logger logger = LoggerFactory.getLogger(TestService.class);

//	@Autowired(required=true)
//	org.springframework.jdbc.core.JdbcTemplate jdbcTemplate ;

	@Autowired(required=true)
	OrderService service;

	@Test
	public void test(){
//		Orders[] orders=new Orders[1];
//		Orders order=new Orders();
//		order.setDistributor("Distributor");
//		orders[0]=order;
//		service.save(orders);

		Orders record=new Orders();
		WebMessage webMes=service.findPage(record,0,Integer.MAX_VALUE,"");
		Long total=webMes.getTotal();
		System.out.println("Total"+total.toString());
		List<Orders> list= (List<Orders>) webMes.getData();
		System.out.print("Data:"+ JSONArray.fromObject(list));
	}

}

