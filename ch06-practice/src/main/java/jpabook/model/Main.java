package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.MemberProduct;
import jpabook.model.entity.MemberProductId;
import jpabook.model.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

//            testSave(em);
//            save(em);
//            find(em);
//            findInverse(em);
            save(em);
            find(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

//    public static void testSave(EntityManager em) {
//        Member member1 = new Member();
//        Member member2 = new Member();
//
//        Team team1 = new Team();
//        team1.getMembers().add(member1);
//        team1.getMembers().add(member2);
//
//        em.persist(member1);
//        em.persist(member2);
//        em.persist(team1);
//    }

//    public static void save(EntityManager em) {
//        Product productA = new Product();
//        productA.setId("productA");
//        productA.setName("상품A");
//
//        em.persist(productA);
//
//        Member member1 = new Member();
//        member1.setId("member1");
//        member1.setName("회원1");
//        member1.addProduct(productA);
//        em.persist(member1);
//    }
//
//    public static void find(EntityManager em) {
//        Member member = em.find(Member.class, "member1");
//        List<Product> products = member.getProducts();
//        for (Product product : products) {
//            System.out.println("product.name = " + product.getName());
//        }
//    }
//
//    public static void findInverse(EntityManager em) {
//        Product product = em.find(Product.class, "productA");
//        List<Member> members = product.getMembers();
//        for (Member member : members) {
//            System.out.println("member = " + member.getName());
//        }
//    }

    public static void save(EntityManager em) {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setName("회원1");
        em.persist(member1);

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);
    }

    public static void find(EntityManager em) {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

        Member member = memberProduct.getMember();
        Product product = memberProduct.getProduct();

        System.out.println("member = " + member.getName());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + memberProduct.getOrderAmount());
    }
}
