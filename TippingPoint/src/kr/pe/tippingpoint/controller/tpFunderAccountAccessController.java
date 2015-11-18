package kr.pe.tippingpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.pe.tippingpoint.exception.DuplicatedIdException;
import kr.pe.tippingpoint.service.TpFunderService;
import kr.pe.tippingpoint.validator.TpFunderValidator;
import kr.pe.tippingpoint.vo.TpFunder;

@Controller
public class tpFunderAccountAccessController {

	@Autowired
	private TpFunderService service;
	
	@RequestMapping(value="registerTpFunder",method=RequestMethod.POST)
	public String registerTpFunder(@ModelAttribute TpFunder tpfunder, Errors errors, ModelMap model) throws DuplicatedIdException{

		tpfunder.setTpfQualifyTpProposer(true);
		tpfunder.setTpfAccountType("f");
		TpFunderValidator validate = new TpFunderValidator();
		validate.validate(tpfunder, errors); //★
		
		if(errors.hasErrors()){
			System.out.println(errors.getErrorCount());
			return "/tpfunder/register_form.tp";
		}
		
		service.addTpFunder(tpfunder);
		model.addAttribute("tpfunder", tpfunder);
		return "redirect:/tpfunder/registerSuccess.tp";
	}
	
	@RequestMapping("registerSuccess")
	public String registerSuccess(@RequestParam String tpfId, ModelMap model){
		model.addAttribute("tpfunder",service.findTpFunderById(tpfId));
		return "tpfunder/registerSuccess.tiles";
	}
	
	@RequestMapping("idDuplicatedCheck")
	public String idDuplicatedCheck(@RequestParam String tpfId){
		TpFunder tpfunder = service.findTpFunderById(tpfId);
		return String.valueOf(tpfunder!=null);
	}
}


