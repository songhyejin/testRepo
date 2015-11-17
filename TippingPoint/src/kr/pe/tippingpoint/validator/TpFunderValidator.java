package kr.pe.tippingpoint.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.pe.tippingpoint.vo.TpFunder;

public class TpFunderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(TpFunder.class);
	}
	
	@Override
	//등록/수정 공통 체크.
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tpfId", "required", new Object[]{"ID"}, "필수입력사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tpfPassword", "requried", new Object[]{"비밀번호"}, "필수입력사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tpfName", "requried", new Object[]{"이름"}, "필수입력사항입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tpfEmail", "requried", new Object[]{"이메일"}, "필수입력사항입니다.");
	
	}
	
}
