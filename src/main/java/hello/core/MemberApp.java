package hello.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;


import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // 순수 자바
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl();

        //ApplicationContext를 스프링 컨테이너라고 하며, 인터페이스로 구현되어 있다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // applicationContext.getBean(”이름”, 타입);
        // 스프링 빈은 getBean() 메서드를 이용하여 얻을 수 있다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member" + member.getName());
        System.out.println("findMember" + findMember.getName());

    }
}
