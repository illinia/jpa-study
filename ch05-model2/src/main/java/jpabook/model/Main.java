package jpabook.model;

import jpabook.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

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

    public static void logic(EntityManager em) {
        String memberName =  "member1";
        String memberCity = "city";
        String memberStreet =  "street";
        String memberZipcode= "1234";

        Member member = new Member();
        member.setName(memberName);
        member.setCity(memberCity);
        member.setStreet(memberStreet);
        member.setZipcode(memberZipcode);

        em.persist(member);
        System.out.println("member toString" + member);

        String itemName = "item";
        int itemPrice = 10000;
        int itemStockQuantity = 5;

        Item item = new Item();
        item.setName(itemName);
        item.setPrice(itemPrice);
        item.setStockQuantity(itemStockQuantity);

        em.persist(item);

        Date orderDate = new Date();
        OrderStatus orderStatus = OrderStatus.ORDER;

        Order order = new Order();
        order.setMember(member);
        order.setOrderDate(orderDate);
        order.setStatus(orderStatus);
        em.persist(order);

        int orderItemPrice = 20000;
        int orderItemCount = 2;

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderPrice(orderItemPrice);
        orderItem.setCount(orderItemCount);
        orderItem.setOrder(order);
        orderItem.setItem(item);
        em.persist(orderItem);

        order.addOrderItem(orderItem);

        Order findOrder = em.find(Order.class, order.getId());
        Member findOrderMember = findOrder.getMember();

        OrderItem findOrderItem = order.getOrderItems().get(0);
        Item findItem = findOrderItem.getItem();

        System.out.println("findOrder" + findOrder + "findOrderMember" + findOrderMember + "findOrderItem" + findOrderItem + "findItem" + findItem);
    }
}
