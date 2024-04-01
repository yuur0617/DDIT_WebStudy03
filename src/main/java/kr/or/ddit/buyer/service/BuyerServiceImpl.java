package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements BuyerService {
	private BuyerDAO dao = new BuyerDAOImpl();

	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = dao.selectBuyer(buyerId);
		if(buyer==null)
			throw new PKNotFoundException(String.format("%s 제조사 없음.", buyerId));
		return buyer;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectBuyerList(paging);
	}

	@Override
	public void createBuyer(BuyerVO buyer) {
		dao.insertBuyer(buyer);
	}

	@Override
	public void modifyBuyer(BuyerVO buyer) {
		dao.updateBuyer(buyer);
	}

}
