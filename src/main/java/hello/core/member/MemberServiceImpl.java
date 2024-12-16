package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현체 의존
    // 추상화에도 의존 구체화에도 의존 => DIP 위반

    // 다형성에 의해서 MemberRepository를 구현한 MemoryMemberRepository.save()가 호출된다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
