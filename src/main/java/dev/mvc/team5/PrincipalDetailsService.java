package dev.mvc.team5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

@Service
public class PrincipalDetailsService implements UserDetailsService {
  
  private final MemberProcInter memberProc;

  @Autowired // 또는 @Qualifier를 사용하여 빈을 선택할 수 있습니다.
  public PrincipalDetailsService(MemberProcInter memberProc) {
    this.memberProc = memberProc;
  }


  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    MemberVO memberVO = null;
    try {
      memberVO = memberProc.readById(id).orElseThrow(() -> {
        return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
      });
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new PrincipalDetails(memberVO);
  }
}