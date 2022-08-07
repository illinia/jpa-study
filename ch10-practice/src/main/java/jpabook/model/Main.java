package jpabook.model;

import jpabook.model.entity.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
            addTest(em);
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

    public static void addTest(EntityManager em) {
        Team team = new Team();
        team.setName("team");
        em.persist(team);

        Member member = new Member();
        member.setUsername("test");
        member.setAge(10);
        member.setTeam(team);
        em.persist(member);

        Product product = new Product();
        product.setName("product");
        product.setPrice(10000);
        product.setStockAmount(10);
        em.persist(product);

        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");
        address.setZipcode("12345");

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        order.setOrderAmount(1);
        order.setAddress(address);
        em.persist(order);
    }

    public static void logic(EntityManager em) {
//        String jpql = "select m from Member as m where m.username = 'kim'";
//        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//        Root<Member> m = query.from(Member.class);
//        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
//        List<Member> resultList = em.createQuery(cq).getResultList();

        // TypeQuery 사용
//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
//
//        List<Member> resultList = query.getResultList();

        // Query 사용
//        Query query = em.createQuery("SELECT m.username, m.age from Member m");
//        List resultList = query.getResultList();

        String usernameParam = "User1";
        // 이름 기준 파라미터 사용
//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);
//
//        query.setParameter("username", usernameParam);

        // 위치 기준 파라미터 사용
//        List<Member> members = em.createQuery("SELECT m FROM Member m where m.username = ?1", Member.class)
//                .setParameter(1, usernameParam)
//                .getResultList();

        // 엔티티 프로션
//        SELECT m FROM Member m
//        SELECT m.team FROM Member m

//        String query = "SELECT o.address FROM Order o";
//        List<Address> addresses = em.createQuery(query, Address.class).getResultList();
//        System.out.println("addresses = " + addresses);

        // 스칼라 타입
//        List<String> username = em.createQuery("SELECT username FROM Member m", String.class)
//                .getResultList();
//        System.out.println("username = " + username);
        // SELECT DISTINCT username FROM Member m

        // 스칼라타입 여러개 조회 제네릭 Object[] 사용
//        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
//        List<Object[]> resultList = query.getResultList();
//        for (Object[] row : resultList) {
//            String username = (String) row[0];
//            Integer age = (Integer) row[1];
//            System.out.println("username = " + username);
//            System.out.println("age = " + age);
//        }

        // 엔티티타입 여러개 조회
//        List<Object[]> resultList = em.createQuery("SELECT o.member, o.product, o.orderAmount FROM Order o")
//                .getResultList();
//
//        for (Object[] row : resultList) {
//            Member member = (Member) row[0];
//            Product product = (Product) row[1];
//            int orderAmount = (Integer) row[2];
//            System.out.println("member = " + member);
//            System.out.println("product = " + product);
//            System.out.println("orderAmount = " + orderAmount);
//        }

        // NEW 명령어 사용 전
//        List<Object[]> resultList = em.createQuery("SELECT m.username, m.age FROM Member m")
//                .getResultList();
//
//        List<UserDTO> userDTOs = new ArrayList<>();
//        for (Object[] row : resultList) {
//            UserDTO userDTO = new UserDTO((String) row[0], (Integer) row[1]);
//            userDTOs.add(userDTO);
//        }
//
//        System.out.println("userDTOs = " + userDTOs);

        // NEW 명령어 사용 후
//        TypedQuery<UserDTO> query = em.createQuery("SELECT new jpabook.model.entity.UserDTO(m.username, m.age) FROM Member m", UserDTO.class);
//
//        List<UserDTO> resultList = query.getResultList();
//        System.out.println("resultList = " + resultList);
    }

}
