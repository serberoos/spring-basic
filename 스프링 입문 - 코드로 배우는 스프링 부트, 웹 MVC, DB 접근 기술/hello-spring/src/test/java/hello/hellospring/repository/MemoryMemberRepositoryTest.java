package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    // 지금은 MemoryMemberRepository를 작성한후 Test코드를 작성했지만 순서를 뒤집어서 코딩할 수도 있다.(미리 검증할 수 있는 틀을 만든다. => 테스트 주도개발 TDD)
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { //테스트 코드 작성 후 clear
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result); // error : equal to member null
        Assertions.assertEquals(member, result); // (result, null) 다르면 실패
        // System.out.println("result = " + (result == member)); # 일반적인 출력
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // Optional<Member> result = repository.findByName("spring1");
        Member result = repository.findByName("spring1").get(); // get을 쓰면 객체를 꺼낼 수 있음. Optional<Member>.get()

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
