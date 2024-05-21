package dev.mvc.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  @Autowired
  private MemberDAOInter memberDAO;

  @Override
  public int create(MemberVO memberVO) {
    return this.memberDAO.create(memberVO);
  }

  @Override
  public int checkId(String id) {
    return this.memberDAO.checkId(id);
  }

  @Override
  public MemberVO readById(String id) {
    return this.memberDAO.readById(id);
  }

  @Override
  public MemberVO readByMemberno(int id) {
    return this.memberDAO.readByMemberno(id);
  }

  @Override
  public int update(MemberVO memberVO) {
    System.out.println("memberVO: "+memberVO.toString());
    return this.memberDAO.update(memberVO);
  }

  @Override
  public int deleteByMember(int memberno) {
    return this.memberDAO.deleteByMember(memberno);
  }

  @Override
  public ArrayList<MemberVO> list_all() {
    return this.memberDAO.list_all();
  }

}
