package com.woo.dao;

import java.util.List;

public interface GuestBookDAO {
	
	public List<GuestBookVO> getlist();
	public boolean insertlist(GuestBookVO vo);
	public boolean deletelist(Long no);
}
