package dev.mvc.team5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

//import dev.mvc.shoes.ShoesDAOInter;
import dev.mvc.shoes.ShoesProcInter;

@SpringBootTest
class Team5_v2sbm3cApplicationTests {
  //private ShoesDAOInter shoesDAO;
  @Autowired // CateProcInter를 구현한 클래스의 객체를 생성하여 cateProc 변수에 할당
  @Qualifier("dev.mvc.cate.CateProc")
  private ShoesProcInter cateProc;
	@Test
	public void test() {
	}

}
