package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
  @Test // 상태를 유지하는 필드 때문에 발생하는 문제점 확인
  // statefulService1, statefulService2가 같은 인스턴스임
  // 따라서, A사용자가 10000원 주문한 후에 B사용자가 20000원 주문하면
  // statefulService1.getPrice()를 호출하면 20000원이 나옴
  // 즉, 특정 클라이언트에 대한 상태가 아닌, 모든 클라이언트에 대한 공유 필드가 되어버림
  // 해결책: 무상태(stateless)로 설계 변경
  void statefulServiceSingleton(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean( StatefulService.class);
    StatefulService statefulService2 = ac.getBean( StatefulService.class);

    //ThreadA: A사용자 10000원 주문
    int userAPrice = statefulService1.order("userA", 10000);

    //ThreadB: B사용자 20000원 주문
    int userBPrice = statefulService2.order("userB", 20000);

    //ThreadA: A사용자 주문 금액 조회

    System.out.println("price = " + userAPrice);

//    Assertions.assertEquals(statefulService1.getPrice(), 20000);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService(){
      return new StatefulService();
    }
  }
}