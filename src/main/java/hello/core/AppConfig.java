package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

@Configuration
// 스프링 컨테이너가 해당 어노테이션이 붙은 클래스를 설정 정보로 사용한다.
// 클래스 내부에 @Bean 어노테이션이 적힌 메서드를 모두 호출하여 얻은 객체를 스프링 컨테이너에 등록하게 된다.
// 스프링 컨테이너에 등록된 객체를 스프링 빈(Bean)이라 한다.
public class AppConfig {
    // 역할이 분명히 보임
    @Bean
    public MemberService memberService() { // 역할을 세우고 구현이 그안에 들어가도록
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
