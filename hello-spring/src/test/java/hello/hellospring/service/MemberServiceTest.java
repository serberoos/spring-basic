package hello.hellospring.service; // 같은 패키지에 생성됨.

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @BeforeEach
    public void beforeEach(){ // 테스트 실행될때 마다 실행됨. 외부에서 넣어줌 DI
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //Dependancy Injection | DI
    }
    @AfterEach
    public void afterEach() { //테스트 코드 작성 후 clear
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트는 과감하게 한글로 바꿔도 된다.
        //given 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then 결과로 이게 나와야 되
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1); // member1 회원가입
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 이런식으로 쓸 수도 있음.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join(member2); //member2 회원가입
//            fail("예외가 발생해야 합니다.");
//        }catch(IllegalStateException e) { //이름 중복
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}