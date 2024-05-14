package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberProc implements MemberProcInter {
  @Autowired
  private MemberDAOInter memberDAO;

  @Override
  public int create(MemberVO memberVO) {
    return this.memberDAO.create(memberVO);
  }

}
