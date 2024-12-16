package hello.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest
{
    MemberService memberService = new MemberServiceImpl();
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
