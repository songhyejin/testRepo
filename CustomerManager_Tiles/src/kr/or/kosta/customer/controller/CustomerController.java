package kr.or.kosta.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.validator.CustomerValidator;
import kr.or.kosta.customer.exception.DuplicatedIdException;
import kr.or.kosta.customer.model.service.CustomerService;
import kr.or.kosta.customer.vo.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	//고객 ID로 고객 조회 처리 Handler
	@RequestMapping("/findById")
	public String findById(@RequestParam String customerId, ModelMap model){
		Customer customer = service.findCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "customer/customer_info.tiles";
	}
	
	//고객 List 조회처리 Handler
	@RequestMapping("list")
	public String list(@RequestParam(defaultValue="1") String pageNo, ModelMap model){
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); //null일 경우 예외처리를 통해 page를 1로 처리한다..
		} catch (NumberFormatException e) {}
		Map attributes = service.getAllCustomersPaging(page);
		model.addAllAttributes(attributes);
		return "customer/list.tiles";
	}
	//고객 등록 처리 Handler
	@RequestMapping("add")
	public String add(@ModelAttribute Customer customer, Errors errors, ModelMap model) throws DuplicatedIdException{
		
		new CustomerValidator().validate(customer, errors);
		if(errors.hasErrors()){
			return "customer/register_form.tiles";
		}
		
		customer.setCustomerMileage(1000);//기본값.
		service.addCustomer(customer);
		model.addAttribute("customerId", customer.getCustomerId());
		return "redirect:/customer/registerSuccess.do"; 
	}
	//등록 후 성공페이지로 이동 처리.
	@RequestMapping("registerSuccess")
	public String registerSuccess(@RequestParam String customerId, ModelMap model){
		
		model.addAttribute("customer", service.findCustomerById(customerId));
		return "customer/register_success.tiles";
	}
	//수정폼 조회 처리 Handler
	@RequestMapping("modifyForm")
	public String modifyForm(@RequestParam(defaultValue="") String customerId, ModelMap model)
	throws Exception{
//		요청파라미터 검증
		if(customerId.trim().length()==0){
			throw new Exception("수정할 고객의 아이디가 없습니다.");
		}

		model.addAttribute("customer",service.findCustomerById(customerId));

		return "customer/modify_form.tiles";
	}
	//수정 처리 Handler
	@RequestMapping("modify")
	public String modify(@ModelAttribute Customer customer, Errors errors, @RequestParam(defaultValue="1") String pageNo, ModelMap model)
	throws Exception{
		//Validator를 이용해 요청파라미터 체크
		new CustomerValidator().validate(customer, errors);
		//마일리지 추가 체크
		if(customer.getCustomerMileage()<0){
			errors.rejectValue("customerMileage", "mileage", "마일리지는 양수를 넣으세요.");
		}
		if (errors.hasErrors()) {
			return "customer/modify_form.tiles";
		}
		service.updateCustomer(customer);
		model.addAttribute("customerId", customer.getCustomerId());
		model.addAttribute("pageNo", pageNo);
		return "redirect:/customer/findById.do";
	}
	//고객 삭제 처리 HandlerattributeValue
	@RequestMapping("remove.do")
	public String remove(@RequestParam(defaultValue="") String customerId, @RequestParam(defaultValue="1") String pageNo, ModelMap model)
	throws Exception{
		//요청파라미터 검증
		if(customerId.trim().length()==0){
			throw new Exception("삭제할 고객의 아이디가 없습니다.");
		}
		//비지니스 로직 - 삭제처리(removeCustomer())
		service.removeCustomer(customerId);
		model.addAttribute("pageNo",pageNo);
		//응답
		return "redirect:/customer/list.do"; 
	}
	
	@RequestMapping("idDuplicatedCheck")
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam String customerId){
		Customer cust = service.findCustomerById(customerId);
		return String.valueOf(cust!=null); //중복인 경우 "true" 리턴
	}
	
}
