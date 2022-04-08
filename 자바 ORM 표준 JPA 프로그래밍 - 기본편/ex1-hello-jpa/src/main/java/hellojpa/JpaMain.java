package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // database Transaction 시작

        try {
            // 비영속
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");

            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member); // 생성
//            em.detach(member); // 영속성 컨텍스트에서 제거
//            System.out.println("=== AFTER ===");

//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);

//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//            Member findMember = em.find(Member.class, 1L); //em에서 member 객체 가져오기
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName()); 조회

//            em.remove(findMember); // 삭제 (DB에서 삭제)

//            findMember.setName("HelloJPA"); // 수정

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();//JPQL로 전체 회원 검색
//
//            for (Member member: result){
//                System.out.println("member.name = " + member.getName());
//            }
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);

            Member member = em.find(Member.class, 150L);

//            member.setName("AAAAA");

//            em.detach(member); //준 영속 상태로
//            em.clear(); // 완전히 초기화

//            Member member2 = em.find(Member.class, 150L); //select가 두번 나감

            //em.close() 완전히 영속성 컨텍스트를 종료료

           System.out.println("=================================");

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
