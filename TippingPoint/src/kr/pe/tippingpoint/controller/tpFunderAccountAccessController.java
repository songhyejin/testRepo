package kr.pe.tippingpoint.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.pe.tippingpoint.service.TpFunderAccountAccessServiceImpl;
import kr.pe.tippingpoint.vo.TpFunder;

/**
 * 회원 로그인 작업을 처리하는 Controller
 */
@Controller
public class tpFunderAccountAccessController {

	@Autowired
	private TpFunderAccountAccessServiceImpl service;

	/**
	 * 로그인체크 컨트롤러
	 * 
	 * @param request
	 * @return
	 */
/*	@RequestMapping(value ="loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@RequestParam String tpfId ,@RequestParam String tpfPw, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/page1.tp");
		
		String loginId = tpfId;
		String loginPwd = tpfPw;
		
		if (loginId == null || loginId.trim().length() == 0) {//로그인 Id가 값이없을때
			mav.setViewName("redirect:/login/login.tp");
			return mav;
		}
		String TpFunderId = (service.findTpFunderById(loginId)).getTpfId();
		String TpFunderPwd = (service.findTpFunderById(loginId)).getTpfPassword();
		System.out.println(TpFunderId);
		
		if(TpFunderId == null){
			mav.setViewName("redirect:/login/login.tp");
		}
		if(loginId.equals(TpFunderId) && loginPwd.equals(TpFunderPwd)) { //로그인 ID 및 비밀번호 확인
			session.setAttribute("userLoginInfo", loginId); // 세션생성
		} else {
			mav.setViewName("redirect:/login/login.tp");
		}
		return mav;
	}*/
	
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
		return "administrator/admin_funderList.tiles";
	}

}
