package kr.pe.tippingpoint.service;

import kr.pe.tippingpoint.exception.DuplicatedIdException;
import kr.pe.tippingpoint.vo.TpFunder;

public interface TpFunderAccountAccessService {

	void addTpFunder(TpFunder tpfunder) throws DuplicatedIdException;
	
	TpFunder findTpFunderById(String tpfId);
	
}
