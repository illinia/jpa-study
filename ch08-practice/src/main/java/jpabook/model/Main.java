package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            save(em);
//            checkProxy(em);
            logic(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void save(EntityManager em) {
        Member member = new Member();
        member.setId(1L);
        Team team = new Team();
        member.setTeam(team);
        em.persist(team);
        em.persist(member);
    }

//    public static void checkProxy(EntityManager em) {
//        Member member = em.find(Member.class, 1L);
//        boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
//
//        System.out.println("isLoad = " + isLoad);
//        System.out.println("memberProxy = " + member.getClass().getName());
//    }

    public static void logic(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        Team team = member.getTeam();
        System.out.println(team);
    }
}
