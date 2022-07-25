package jpabook.model;

import jpabook.model.entity.Child;
import jpabook.model.entity.Member;
import jpabook.model.entity.Parent;
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
//            save(em);
//            checkProxy(em);
//            logic(em);
//            saveNoCascade(em);
            saveWithCascade(em);
//            removeNoCascade(em);
            removeWithCascade(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

//    public static void save(EntityManager em) {
//        Team team = new Team();
//        em.persist(team);
//
//        Member member = new Member();
//        member.setId("member1");
//        em.persist(member);
//
//        member.setTeam(team);
//    }

//    public static void checkProxy(EntityManager em) {
//        Member member = em.find(Member.class, 1L);
//        boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
//
//        System.out.println("isLoad = " + isLoad);
//        System.out.println("memberProxy = " + member.getClass().getName());
//    }

//    public static void logic(EntityManager em) {
//        Member member = em.find(Member.class, "member1");
//        Team team = member.getTeam();
////        System.out.println(team);
//    }

//    private static void saveNoCascade(EntityManager em) {
//        Parent parent = new Parent();
//        em.persist(parent);
//
//        Child child1 = new Child();
//        child1.setParent(parent);
//        parent.getChildren().add(child1);
//        em.persist(child1);
//
//        Child child2 = new Child();
//        child2.setParent(parent);
//        parent.getChildren().add(child2);
//        em.persist(child2);
//    }

    private static void saveWithCascade(EntityManager em) {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        em.persist(parent);
    }

//    private static void removeNoCascade(EntityManager em) {
//        Parent parent = em.find(Parent.class, 1L);
//        Child child1 = em.find(Child.class, 1L);
//        Child child2 = em.find(Child.class, 2L);
//
//        System.out.println(parent);
//        System.out.println(child1);
//        System.out.println(child2);
//
//        em.remove(child1);
//        em.remove(child2);
//        em.remove(parent);
//    }

    private static void removeWithCascade(EntityManager em) {
        Parent parent = em.find(Parent.class, 1L);
        em.remove(parent);
    }
}
