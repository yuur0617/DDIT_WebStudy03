package kr.or.ddit.member.servlet11;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 가입 처리
 * 1. 가입 양식 제공(GET)
 * 2. 양식을 통해 입력 및 전송된 데이터 처리(POST)
 *
 */
@Slf4j
@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String logicalViewName = "member/memberForm";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.특수문자 디코딩 설정
//		2. 17개의 파라미터를 받고 -> Command Object 로 캡슐화(MemberVO)
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
//		member.setMemPass(req.getParameter("memPass"));
		Map<String, String[]> parameterMap = req.getParameterMap();
		
			PopulateUtils.populate(member, parameterMap);
		log.info("====>\n{}", member);
		
		// 2-1. 검증 작업 : 통과, 불통
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
	//		3. 가입 로직 호출
				ServiceResult result = service.createMember(member);
	//		4. 로직의 실행 결과에 따른 뷰 선택
				String message = null;
				switch (result) {
				case OK:
					logicalViewName = "redirect:/"; // Post-Redirect-Get
					break;
				case FAIL:
					logicalViewName = "member/memberForm";
					break;
					
				default: // 아이디 중복
					logicalViewName = "member/memberForm";
					message = "아이디 중복, 바꾸셈;";
					break;
				}
				
				req.setAttribute("message", message);
		}else {
			logicalViewName = "member/memberForm";
		}
		
		
//		5. 해당 뷰로 이동.
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		}else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
		
	}

}



















