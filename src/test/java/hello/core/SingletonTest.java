package hello.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.MemberService;


public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();     
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);


        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }


    // 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인
    // 참고: 싱글톤 패턴을 구현하는 방법은 여러 가지가 있으나 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택

    // 싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있음.
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    }

    //최상단 AppConfig 클래스에는 싱글톤을 구현하기 위한 어떠한 코드도 존재하지 않는다.
    // 하지만, 스프링 컨테이너를 통해 객체를 여러 번 호출하더라도 항상 같은 객체를 호출하게 된다.
    // 따라서, 스프링 컨테이너로 인해 클라이언트의 요청이 올 때마다 객체를 생성하는 것이 아닌, 이미 생성된 객체를 공유하여 효율적으로 재사용할 수 있게 된다.

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainerAndSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}