package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // HashMap 형태로 객체 넣어주기(value)
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // Member 정보를 저장할 때, 시스템이 id를 넣어준다.
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될경우 Optional을 통해서 감싸서 반환해주면 client에서 뭔가를 할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
