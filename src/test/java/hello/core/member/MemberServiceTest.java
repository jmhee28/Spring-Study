package hello.core.member;


import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest
{
    MemberService memberService;

    // @BeforeEach : 각 테스트를 실행하기 전에 먼저 실행되어야 하는 메서드에 붙이는 어노테이션
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join(){
        // given : 뭔가가 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : 이걸 실행했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then : 결과가 이게 나와야 함
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
