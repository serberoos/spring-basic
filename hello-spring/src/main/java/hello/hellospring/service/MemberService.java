package hello.hellospring.service; // hello.hellospring 하위 패키지를 뒤져서 Spring bean으로 등록한다.

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// 서비스의 변수명은 비즈니스와 가까워야함 인터페이스는 정 반대
//@Service //@Component라는 어노테이션이 있음.
public class MemberService { //ctrl + shift+ t 를 하면 테스트 케이스를 쉽게 작성할 수 있음.
    private final MemberRepository memberRepository;// = new MemoryMemberRepository();

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /*
        회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원 X
        validateDuplicateMember(member); // 중복 회원 검증
        /* 아래처럼 쓰면 Optional이 모양이 안 예쁨.
            Optional<Member> result = memberRepository.findByName(member.getName());
            result.ifPresent(m ->{ // 어떤 값이 있으면 이 로직이 실행됨 Option이기 때문에 가능한 것 기존에 null일 경우는 힘듬.
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
        */


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //이런 경우 메소드로 뽑는게 좋다. Extract Method
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
