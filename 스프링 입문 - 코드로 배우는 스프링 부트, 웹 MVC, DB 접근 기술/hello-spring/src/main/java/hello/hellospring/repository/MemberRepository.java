package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
/*
Optional?
findById 할때 값이 없으면 null이 반환되는데, 요새는 null반환 보다는 Optional로 감싸서 반환하는 방법이 선호됨
 */

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 회원리스트를 전부 반환
}
