package com.ict.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.service.BoardService;
import com.ict.board.vo.BoardVO;
import com.ict.common.Paging;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;
	
	@GetMapping("/board_list")
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board/list");
		
		// 전체 게시물의 수
		int count = boardService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수
		if (paging.getTotalRecord()<= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage()); // 나누기
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				// 페이지가 딱 떨어지지 않으면 하나 더 넣는다
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		
		// 파라미터에서 넘어오는 cPage(보고싶은 페이지번호)를 구하자
		String cPage = request.getParameter("cPage");
		
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// cPage 기준으로 begin, end, beginBlock, endBlock을 구함
		// MySQL, Mariadb는 limit, offset을 이용해야 한다.
		// offset은 limit*(현재 페이지 -1)
		// limit는 numPerPage
		
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() -1));
		
		//시작블록, 끝블록
		paging.setBeginBlock(count);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() -1);
		
		paging.setBeginBlock(
				(int)(((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock() +1)
				);
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		// DB
		List<BoardVO> board_list = boardService.getBoardList(paging.getOffset(), paging.getNumPerPage());
		if (board_list != null) {
			mv.addObject("board_list", board_list);
			mv.addObject("paging", paging);
			return mv;
		}
				
		
		return null;
	}
}
