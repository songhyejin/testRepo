package kr.or.kosta.customer.model.dao;

import java.util.List;

import kr.or.kosta.customer.vo.Customer;

public interface CustomerDao {

	int insertCustomer(Customer customer);

	int deleteCustomerById(String customerId);

	int updateCustomer(Customer customer);

	Customer selectCustomerById(String customerId);

	List<Customer> selectCustomers();

	List<Customer> selectCustomersPaging(int pageNo);

	List<Customer> selectCustomersByName(String customerName);

	int selectCountCustomers();

}