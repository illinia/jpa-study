package jpabook.model;

import jpabook.model.entity.*;

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

        // 임베디드 값 타입
        member.setHomeAddress(new Address("도시", "위치", "주소"));

        // 기본값 타입 컬렉션
        member.getFavoriteFoods().add("음식1");
        member.getFavoriteFoods().add("음식2");
        member.getFavoriteFoods().add("음식3");

        // 임베디드 값 타입 컬렉션
//        member.getAddressHistory().add(new Address("도시2", "위치2", "주소2"));
//        member.getAddressHistory().add(new Address("도시3", "위치3", "주소3"));

        em.persist(member);
    }
}
