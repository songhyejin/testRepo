package kr.pe.tippingpoint.controller;

//막내막내막내 불만쩌러



import java.util.Map;

import javax.servlet.http.HttpSession;


	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.pe.tippingpoint.exception.DuplicatedIdException;
import kr.pe.tippingpoint.service.TpFunderAccountAccessServiceImpl;
import kr.pe.tippingpoint.validator.TpFunderValidator;
import kr.pe.tippingpoint.vo.TpFunder;

/**
 * 회원 로그인 작업을 처리하는 Controller
 */
@Controller
public class tpFunderAccountAccessController {

	@Autowired
	private TpFunderAccountAccessServiceImpl service; 
	
	/**
	 * 회원가입
	 * @param tpfunder
	 * @param errors
	 * @param model
	 * @return
	 * @throws DuplicatedIdException
	 */
	@RequestMapping(value = "registerTpFunder", method = RequestMethod.POST)
	public String registerTpFunder(@ModelAttribute TpFunder tpfunder, Errors errors, ModelMap model)
			throws DuplicatedIdException {

		tpfunder.setTpfQualifyTpProposer("true");
		tpfunder.setTpfAccountType("f");
		TpFunderValidator validate = new TpFunderValidator();
		validate.validate(tpfunder, errors); // ★

		if (errors.hasErrors()) {
			return "/tpfunder/register_form.tp";
		}

		service.addTpFunder(tpfunder);
		model.addAttribute("tpfunder", tpfunder);
		return "redirect:/tpfunder/registerSuccess.tp";
	}


	/**
	 * 회원가입 성공
	 * @param tpfId
	 * @param model
	 * @return
	 */
	@RequestMapping("registerSuccess")
	public String registerSuccess(@RequestParam String tpfId, ModelMap model) {
		model.addAttribute("tpfunder", service.findTpFunderById(tpfId));
		return "tpfunder/registerSuccess.tiles";
	}

	@RequestMapping("idDuplicatedCheck")
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam String tpfId) {
		TpFunder tpfunder = service.findTpFunderById(tpfId);
		System.out.println(String.valueOf(tpfunder!=null));
		return String.valueOf(tpfunder != null);
	}
	
	/**
	 * 로그인체크 컨트롤러
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value ="loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public String loginProcess(@RequestParam String tpfId ,@RequestParam String tpfPw, HttpSession session) {
		String loginId = tpfId;
		String loginPwd = tpfPw;
		
		if (loginId == null || loginId.trim().length() == 0) {//로그인 Id가 값이없을때
			return "잘못된 입력입니다"; 
		}
		try {//로그인 ID가 DB에 존재 유무 확인
			String TpFunderId = (service.findTpFunderById(loginId)).getTpfId();
			String TpFunderPwd = (service.findTpFunderById(loginId)).getTpfPassword();
			if(loginId.equals(TpFunderId) && loginPwd.equals(TpFunderPwd)) { //로그인 ID 및 비밀번호 확인
				session.setAttribute("userLoginInfo", loginId); // 세션생성
			} else {//비밀번호가 틀렸을경우
				return "비밀번호가 틀렸습니다";
			}
			return "success";
		} catch (NullPointerException e) {//로그인한 ID가 가입된회원이아님
			return "등록된 ID가 없습니다";
		}
		
	}
	
	@RequestMapping(value ="loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/page1.tp");
		return mav;
	}
	

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.setAttribute("userLoginInfo", null);
		return "redirect:index.tp";
	}
	
	@RequestMapping("/findByTpfId")
	public String findById(@RequestParam String tpfId, ModelMap model){
		TpFunder tpFunder = service.findTpFunderById(tpfId);
		model.addAttribute("tpFunder", tpFunder);
		return "admin/tpFunder_info.tiles";
	}
	
	
	@RequestMapping("/findAllTpFunderList")
	public String list(@RequestParam(defaultValue="1") String pageNo, ModelMap model){
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); //null일 경우 예외처리를 통해 page를 1로 처리한다..
		} catch (NumberFormatException e) {}
		Map attributes = service.getAllTpFundersPaging(page);
		model.addAllAttributes(attributes);
		return "/admin/list.tp";
	}

}
