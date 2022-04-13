package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.Team;

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
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();            // 양방향 연관관계 member -> team -> member

            for (Member m : members){
                System.out.println("m = " + m.getUsername());
            }
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
