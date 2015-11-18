package kr.pe.tippingpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.tippingpoint.exception.DuplicatedIdException;
import kr.pe.tippingpoint.service.TpFunderService;
import kr.pe.tippingpoint.validator.TpFunderValidator;
import kr.pe.tippingpoint.vo.TpFunder;

@Controller
public class tpFunderAccountAccessController {

	@Autowired
	private TpFunderService service;
	
	@RequestMapping("registerTpFunder")
	public String registerTpFunder(TpFunder tpfunder, Errors errors, ModelMap model) throws DuplicatedIdException{
		new TpFunderValidator().validate(tpfunder, errors);
		if(errors.hasErrors()){
			return "/tpfunder/register_form.tp";
		}

		TpFunder tpfunder1 = new TpFunder(tpfunder.getTpfId(), tpfunder.getTpfName(), tpfunder.getTpfPassword(), 
				tpfunder.getTpfBirth(), tpfunder.getTpfGender(), tpfunder.getTpfEmail(), tpfunder.getTpfZipcode(), 
				tpfunder.getTpfAddress(), tpfunder.getTpfAddressD(), tpfunder.getTpfPhoneNum(), true, "f");
		
		service.addTpFunder(tpfunder1);
		model.addAttribute("tpfunder", tpfunder);
		return "redirect:/tpfunder/registerSuccess.tp";
	}
	
	@RequestMapping("registerSuccess")
	public String registerSuccess(@RequestParam String tpfId, ModelMap model){
		model.addAttribute("tpfunder",service.findTpFunderById(tpfId));
		return "tpfunder/registerSuccess.tiles";
	}
}


