package DongYu.WebBase.Mapping.Tester;

import DongYu.WebBase.Tester.PublicJUnit4ClassRunner;
import Yao.EBusiness.Entity.Orders;
import Yao.EBusiness.Mapping.OrdersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(PublicJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/DongYu/WebBase/Tester/mappingTestConfig.xml"})
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class TestDemo {

	private Logger logger = LoggerFactory.getLogger(TestDemo.class);

	@Autowired
	OrdersMapper ordersMapper;

	@Test
	public void test(){
		Orders orders=new Orders();
		orders.setDistributor("distributor");
		ordersMapper.insert(orders);

//		Module module=new Module();
//		module.setRadioAddress("863703034889001");
//		module.setMesPackNum("000001");
//		moduleMapper.updateMesPackNUm(module);
//		module=moduleMapper.findOne(module);
//		Assert.assertEquals(module.getRadioAddress(),"863703034889001");
//		Assert.assertNotNull(module.getId());
//		Assert.assertEquals(module.getMesPackNum(),"000001");
	}
}
