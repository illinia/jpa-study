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

        Member member1 = new Member();
        member1.setUsername("test1");
        member1.setAge(10);
        member1.setTeam(team);
        em.persist(member1);

        Member noName = new Member();
        noName.setAge(10);
        noName.setTeam(team);
        em.persist(noName);

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

        // 페이징
//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC",
//                Member.class);
//
//        query.setFirstResult(0);
//        query.setMaxResults(20);
//        System.out.println(query.getResultList().toString());

//        GROUP BY, HAVING
//        String jpql = "SELECT t.name, COUNT(m.age), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) " +
//                "FROM Member m LEFT JOIN m.team t " +
//                "GROUP BY t.name " +
//                "HAVING AVG(m.age) >= 10";

//        ORDER BY
//        String jqpl = "SELECT m FROM Member m ORDER BY m.age DESC, m.username ASC";

//        내부조인
//        String teamName = "team";
//        String query = "SELECT m FROM Member m INNER JOIN m.team t " +
//                "WHERE t.name = :teamName " +
//                "ORDER BY m.age DESC";
//
//        List<Member> members = em.createQuery(query, Member.class)
//                .setParameter("teamName", teamName)
//                .getResultList();

//        String query = "SELECT m, t FROM Member m JOIN m.team t";
//        List<Object[]> result = em.createQuery(query).getResultList();
//        for (Object[] row : result) {
//            Member member = (Member) row[0];
//            Team team = (Team) row[1];
//        }

//        외부조인
//        "SELECT m FROM Member m LEFT JOIN m.team t";

//        컬렉션 조인
//        "SELECT t, m FROM Team t LEFT JOIN t.members m";

//        세타 조인
//        "SELECT COUNT(m) FROM Member m, Team t WHERE m.username = t.name"

//        JOIN ON절
//        "SELECT m, t FROM Member m LEFT JOIN m.team t on t.name = 'A'";

//        엔티티 페치 조인
//        String jpql = "SELECT m FROM Member m JOIN FETCH m.team";
//
//        List<Member> members = em.createQuery(jpql, Member.class).getResultList();
//
//        for (Member member : members) {
//            System.out.println("username = " + member.getUsername() + ", " + "teamname = " + member.getTeam().getName());
//        }

//        컬렉션 페치 조인
//        String jpql = "SELECT t FROM Team t JOIN FETCH t.members WHERE t.name = 'team'";
//        List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
//
//        for (Team team : teams) {
//            System.out.println("teamname = " + team.getName() + ", team = " + team);
//
//            for (Member member : team.getMembers()) {
//                System.out.println("->username = " + member.getUsername() + ", member = " + member);
//            }
//        }

//        페치 조인과 DISTINCT
//        String jpql = "SELECT DISTINCT t FROM Team t JOIN FETCH t.members WHERE t.name = 'team'";
//        List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
//
//        for (Team team : teams) {
//            System.out.println("teamname = " + team.getName() + ", team = " + team);
//
//            for (Member member : team.getMembers()) {
//                System.out.println("->username = " + member.getUsername() + ", member = " + member);
//            }
//        }

//        경로 표현식
//        상태 필드 경로 탐색
//        "SELECT m.username, m.age from Member m"

//        단일 값 연관 경로 탐색
//        "SELECT o.member from Order o";

//        서브쿼리
//        나이가 평균보다 많은 회원 검색
//        "SELECT m from Member m WHERE m.age > (SELECT AVG(m2.age) FROM Member m2)"
//        한 건이라도 주문한 고객 검색
//        "SELECT m FROM Member m WHERE (SELECT COUNT(o) FROM Order o WHERE m = o.member) > 0"
//        "SELECT m FROM Member m WHERE m.orders.size > 0"

//        팀A에 소속된 회원
//        "SELECT m FROM Member m WHERE EXISTS (SELECT t FROM m.team t WHERE t.name = '팀A')"

//        전체 상품 각각의 재고보다 주문량이 많은 주문들
//        String jpql = "SELECT o FROM Order o WHERE o.orderAmount > ALL (SELECT p.stockAmount FROM Product p)";
//        List resultList = em.createQuery(jpql).getResultList();
//        System.out.println(resultList);

//        어떤 팀이든 팀에 소속된 회원
//        "SELECT m FROM Member m WHERE m.team = ANY (SELECT t from Team t)"

//        20세 이상을 보유한 팀
//        "SELECT t from Team t WHERE t IN (SELECT t2 FROM Team t2 JOIN t2.members m2 WHERE m2.age >= 20)"

//        나이가 10~20인 회원 검색
//        "SELECT m FROM Member m WHERE m.age BETWEEN 10 and 20"

//        이름이 team, team1인 회원 검색
//        "SELECT m from Member m WHERE m.username in ('team','team1')"

//        중간에 원 이라는 글자가 들어간 회원
//        "SELECT m FROM Member m WHERE m.username like '%원%'"

//        처음에 회원이라는 단어가 포함
//        "WHERE m.username like'회원%'"

//        마지막에 회원이라는 단어가 포함
//        "WHERE m.username like '%회원'"

//        회원A, 회원1
//        "WHERE m.username like '회원_'"

//        회원3
//        "WHERE m.username like '__3'"

//        회원%
//        "WHERE m.username like '회원\%' ESCAPE '\'"

//        NULL 비교식
//        "WHERE m.username is NULL"
//        "WHERE NULL = NULL" 거짓
//        "WHERE 1 = 1" 참

//        컬렉션 식
//        "SELECT m FROM Member m WHERE m.orders is not empty";

//        컬렉션 맴버 식
//        "select t from Team t where :memberParam member of t.members"

//        종료 이벤트 조회
//        "select e from Event e where e.endDate < CURRENT_DATE"

//        기본 CASE 식
//        "select" +
//                "case when m.age <= 10 then '학생요금'" +
//                    "when m.age >= 60 then '경로요금'" +
//                    "else '일반요금'" +
//                "end" +
//        "from Member m";

//        심플 CASE
//        "select " +
//                "case t.name " +
//                    "when '팀A' then '인센티브110%'" +
//                    "when '팀B' then '인센티브120%'" +
//                    "else '인센티브105%'" +
//                "end" +
//        "from Team t";

//        COALESCE
//        username이 null이면 이름 없는 회원을 반환
//        String jpql = "select coalesce(m.username,'이름 없는 회원') from Member m";
//        List<String> resultList = em.createQuery(jpql, String.class).getResultList();
//        System.out.println(resultList);

//        사용자 이름이 관리자면 null 반환 나머지는 본인 이름 반환
//        "select NULLIF(m.username, '관리자') from Member m";

//        다형성 쿼리
//        "select i from Item i where type(i) IN (Book, Movie)";

        "select i from Item i where treat(i as Book).author = 'kim'"
    }

}
