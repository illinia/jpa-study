package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;

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

//        Member member1 = new Member("member1", "회원1");
//        Member member2 = new Member("member2", "회원2");
//        Team team1 = new Team("team1", "팀1");
//
//        member1.setTeam(team1);
//        member2.setTeam(team1);
//
//        Team findTeam = member1.getTeam();

        try {

            tx.begin(); //트랜잭션 시작

//            testSaveNonOwner(em);
//            testSave(em);
//            queryLogicJoin(em);
//            updateRelation(em);
//            deleteRelation(em);
//            test순수한객체_양방향();
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void testSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        Member member = em.find(Member.class, "member1");
        Team team = member.getTeam();
        System.out.println("팀 이름 = " + team.getName());
    }

    public static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where " +
                "t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class).setParameter("teamName", "팀1").getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.getName = " + member.getName());
        }
    }

    public static void updateRelation(EntityManager em) {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    public static void deleteRelation(EntityManager em) {
        Member member = em.find(Member.class, "member1");
        member.setTeam(null);
    }

    public static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for (Member member : members) {
            System.out.println("member.name = " + member.getName());
        }
    }

    public static void testSaveNonOwner(EntityManager em) {
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team1 = new Team("team1", "팀1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);
    }

//    public static void test순수한객체_양방향() {
//        Team team1 = new Team("team1", "팀1");
//        Member member1 = new Member("member1", "회원1");
//        Member member2 = new Member("member2", "회원2");
//
//        member1.setTeam(team1);
//        team1.getMembers().add(member1);
//
//        member2.setTeam(team1);
//        team1.getMembers().add(member2);
//
//        List<Member> members = team1.getMembers();
//        System.out.println("members.size = " + members.size());
//    }

    public static void testORM_양방향(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");

        member1.setTeam(team1);
        team1.getMembers().add(member1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");

        member2.setTeam(team1);
        team1.getMembers().add(member2);
        em.persist(member2);
    }
}
