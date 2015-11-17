package kr.or.kosta.customer.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.util.PagingBean;
import kr.or.kosta.customer.exception.CustomerNotFoundException;
import kr.or.kosta.customer.exception.DuplicatedIdException;
import kr.or.kosta.customer.model.dao.CustomerDao;
import kr.or.kosta.customer.vo.Customer;
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao dao;
	@Autowired
	public  CustomerServiceImpl(CustomerDao dao){
		this.dao = dao;
	}
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 고객을 등록하는 메소드.
	 *  - 고객 id (id)는 중복될 수 없다.  
	 *  	- 등록하려는 고객의 id와 같은 id의 고객이 이미 등록된 경우 등록 처리 하지 않는다. 
	 * @param customer 등록할 고객 정보를 가진 Customer객체를 받을 매개변수.
	 * @throws SQLException 
	 * @throws DuplicatedIdException 
	 */
	@Override
	public void addCustomer(Customer customer) throws DuplicatedIdException{
			//등록할 고객의 id로 고객조회
			Customer cust = dao.selectCustomerById(customer.getCustomerId());
			if(cust != null){
				//이미 있는 고객ID이므로 예외발생 시킨다.
				throw new DuplicatedIdException(customer.getCustomerId()+"는 이미 등록된 ID입니다.");
			}
			//DB에 insert 
			dao.insertCustomer(customer);
		
	}
	
	/**
	 * 전체 고객들을 조회하는 메소드.
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Customer> getAllCustomers(){
			return dao.selectCustomers();
	}
	@Override
	public Map getAllCustomersPaging(int pageNo){
		HashMap map = new HashMap();
		map.put("list", dao.selectCustomersPaging(pageNo));
		PagingBean pagingBean = new PagingBean(dao.selectCountCustomers(), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}
	/**
	 * id로 고객을 찾는 메소드
	 * @param id 조회할 고객의 ID
	 * @return customerList에서 조회한 고객객체. 찾는 고객이 없으면 null을 리턴한다.
	 * @throws SQLException 
	 */
	@Override
	public Customer findCustomerById(String customerId){
			return dao.selectCustomerById(customerId);
	}
	/**
	 * 이름으로 고객을 조회하는 메소드
	 * @param name 조회할 고객의 이름
	 * @return customerList에서 조회된 고객들을 담아 리턴할 ArrayList
	 */
	@Override
	public List<Customer>  findCustomerByName(String customerName){
			return dao.selectCustomersByName(customerName);
	}
	/**
	 * 매개변수로 받은 ID의 고객을 찾아 삭제 처리
	 *  - 매개변수로 받은 ID의 고객이 없으면 처리를 진행하지 않는다. 
	 * @param id
	 * @throws SQLException 
	 * @throws CustomerNotFoundException 삭제할 고객이 DB에 없으면 발생
	 */
	@Override
	public void removeCustomer(String customerId) throws CustomerNotFoundException{
			Customer cust = dao.selectCustomerById(customerId);
			if(cust==null){
				throw new CustomerNotFoundException(customerId+"는 없는 ID이므로 삭제할 수 없습니다.");
			}
			dao.deleteCustomerById(customerId);
	}
	/**
	 * 매개변수로 받은 고객과 같은 ID를 가진 고객정보를 찾아 수정 처리.
	 *  - 수정하려는 고객의 ID가 없는 경우 처리를 진행하지 않는다.
	 * @param newCust 변경할 고객 정보
	 * @throws SQLException 
	 * @throws CustomerNotFoundException 수정할 고객이 DB에 없으면 발생
	 */
	@Override
	public void updateCustomer(Customer newCust) throws CustomerNotFoundException{
			Customer cust = dao.selectCustomerById(newCust.getCustomerId());
			if(cust==null){
				throw new CustomerNotFoundException(newCust.getCustomerId()+"는 없는 ID이므로 수정할 수 없습니다.");
			}
			dao.updateCustomer(newCust);
	}
}
