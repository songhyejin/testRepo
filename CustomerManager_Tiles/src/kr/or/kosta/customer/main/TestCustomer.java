package kr.or.kosta.customer.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.kosta.customer.model.service.CustomerService;
import kr.or.kosta.customer.vo.Customer;

public class TestCustomer {
	public static void main(String[] args) throws Exception{
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("common/config/spring/customer-spring.xml");

		//CustomerService 주입받
		CustomerService service =(CustomerService)context.getBean("customerService");
		//System.out.println("-----------------------전체 조회------------------");
		/*List<Customer> list = service.getAllCustomers();
		for(Customer cust : list){
			System.out.println(cust);
		}*/
		
		int cnt = (int)(Math.random()*1000+1);
		String id = String.format("myid-%d", cnt);
		Customer customer = new Customer(id, "1111", "My고객"+cnt, "k@k.com", 2000*cnt);
		service.addCustomer(customer);
		
		System.out.printf("------------------ID %s로 조회----------------%n", id);
		System.out.println(service.findCustomerById(id));
	}
}
