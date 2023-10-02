package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("start");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Team teamA = new Team();
        teamA.setTeamname("teamA");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setTeamname("teamB");
        em.persist(teamB);

        Member member = new Member();
        member.setUsername("memberA");
        member.setAge(10);
        member.setTeam(teamA);
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("memberB");
        member2.setAge(20);
        member2.setTeam(teamB);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("memberC");
        member3.setAge(30);
        member3.setTeam(teamB);
        em.persist(member3);

        em.flush();
        em.clear();

        /*
        String query = "select " +
                                "case " +
                                    "when m.age <= 10 then '학생요금' " +
                                    "when m.age >= 60 then '경로요금' " +
                                    "else '일반요금' " +
                                "end " +
                       "from Member m";
        */
        
        /*
        // 지연로딩(Lazy)를 설정 해두어도 jpql의 fetch join이 우선함
        String query = "SELECT distinct t FROM Team t join fetch t.members"; // 일대다인 경우는 distinct로 중복을 제거해줘야 함
         */

        /*
        // 1. 결과를 미리 캐싱해둠  2. load 시점에 해당 쿼리를 검증함 3. data jpa로 주로 대체하여 사용
        List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                                    .setParameter("username", "memberA")
                                    .getResultList();
        */

        /* 벌크연산 1. 영속성 컨텍스트 무시하고 바로 쿼리 (벌크 연산 먼저 실행 or 벌크 연산 수행 후 영속성 컨텍스트 초기화 : 벌크 연산 수행 전 값에 영향을 받지 않기 위함)
        int i = em.createQuery("UPDATE Member m SET m.age = 100")
                  .executeUpdate();
        System.out.println("i = " + i);
        em.clear();
         */

        tx.commit();

        em.close();
        emf.close();
    }

}
