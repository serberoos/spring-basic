package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //Test 끝난 후 DB의 내용을 롤백해주는 어노테이션 | DB 커밋단계에서 지움.
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test // 최대한 스프링 컨테이너 없이 테스트를 수행하는 것이 좋은 테스트일 확률이 높다.
    //@Commit 이 어노테이션을 사용하면 Commit을 함.
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