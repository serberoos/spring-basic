package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //자동으로 만들어진 것을 인젝션 받으면 된다.
                                    // DB와 통신하고 하는 것을 내부적으로 처리한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //select
        return Optional.ofNullable(member); // 값이 없을 수도 있으므로..
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m form Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class) //객체를 대상으로 쿼리를 날림.
                .getResultList();
//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//        return result;
    } // Spring Data Jpa로 jpQL 안짜도 만들 수 있음.
}
