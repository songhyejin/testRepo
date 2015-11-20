package kr.pe.tippingpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.tippingpoint.dao.TpFunderDao;
import kr.pe.tippingpoint.exception.DuplicatedIdException;
import kr.pe.tippingpoint.vo.TpFunder;

@Service("TpFunderService")
public class TpFunderAccountAccessServiceImpl implements TpFunderAccountAccessService{
	
	private TpFunderDao dao;
	
	@Autowired
	public TpFunderAccountAccessServiceImpl(TpFunderDao dao){
		this.dao = dao;
	}
	public TpFunderAccountAccessServiceImpl(){
		
	}
	
	@Override
	public void addTpFunder(TpFunder tpfunder) throws DuplicatedIdException{
		TpFunder tpf = dao.selectTpFunderById(tpfunder.getTpfId());
		/*if(tpf != null){
			throw new DuplicatedIdException(tpfunder.getTpfId()+"는 이미 등록된 아이디입니다.");			
		}*/
		dao.insertTpFunder(tpfunder);
	}

	@Override
	public TpFunder findTpFunderById(String tpfId){
		return dao.selectTpFunderById(tpfId);
	}
}
