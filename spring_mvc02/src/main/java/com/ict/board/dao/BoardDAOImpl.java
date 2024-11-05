package com.ict.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("board.count");
	}

	@Override
	public List<BoardVO> getBoardList(int offset, int limit) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("board.list", map);
	}

	@Override
	public int getBoardInsert(BoardVO bovo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBoardHit(String bo_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardVO getBoardDetail(String bo_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAnsInsert(BoardVO bovo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBoardDelete(BoardVO bovo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBoardUpdate(BoardVO bovo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
