package DongYu.WebBase.Service.Tester;

import DongYu.WebBase.Tester.PublicJUnit4ClassRunner;
import Yao.EBusiness.Entity.Orders;
import Yao.EBusiness.Service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


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
		Orders[] orders=new Orders[1];
		Orders order=new Orders();
		order.setDistributor("Distributor");
		orders[0]=order;
		service.save(orders);

//		Module[] modules=new Module[3];
//		modules[0]=new Module();
//		modules[0].setRadioAddress("863703034889001");
//		modules[0].setMesPackNum("000001");
//		modules[1]=new Module();
//		modules[1].setRadioAddress("863703034892369");
//		modules[1].setMesPackNum("000002");
//		modules[2]=new Module();
//		modules[2].setRadioAddress("863703034892849");
//		modules[2].setMesPackNum("000003");
//		WebMessage msg = moduleService.updateMesPackNUm(modules);
//		Assert.assertEquals(msg.getSuccess(),Boolean.FALSE);
	}

}

