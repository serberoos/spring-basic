package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Interface만 만들어 두면..
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //JpaRepository를 추가 해두면..
    //Spring Boot Jpa 가 인터페이스를 알아서 구현해 내고 Spring Bean에 등록함.

    @Override
    Optional<Member> findByName(String name); // 인터페이스 이름 만으로 개발을 끝낼 수 있음.
}
