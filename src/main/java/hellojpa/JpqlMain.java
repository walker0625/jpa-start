package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("start");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Team team = new Team();
        team.setTeamname("teamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
        member.setAge(11);
        member.setTeam(team);
        member.setMemberType(MemberType.ADMIN);
        em.persist(member);

        em.flush();
        em.clear();
        
        /* 조건절 쿼리
        Member fm = em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class)
                      .setParameter("username", member.getUsername())
                      .getSingleResult();

        System.out.println("fm.getUsername() = " + fm.getUsername());
        */

        /* 프로젝션
        List<Member> fml = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
        fml.forEach(m -> System.out.println("m.getUsername() = " + m.getUsername()));

        List<Team> ftl = em.createQuery("SELECT m.team FROM Member m", Team.class).getResultList();
        ftl.forEach(t -> System.out.println("t.getTeamname() = " + t.getTeamname()));

        List<Address> fal = em.createQuery("SELECT o.address FROM Order o", Address.class).getResultList();
        fal.forEach(a -> System.out.println("a = " + a.getCity()));

        List<String> fsl = em.createQuery("SELECT m.username FROM Member m", String.class).getResultList();
        fsl.forEach(s -> System.out.println("s = " + s));

        // 전체 패키지명을 입력해야 하고, 생성자와 select하는 필드의 순서가 일치해야 함
        List<MemberDto> fmdl = em.createQuery("SELECT new hellojpa.MemberDto(m.id, m.username) FROM Member m", MemberDto.class).getResultList();
        fmdl.forEach(md -> System.out.println("md.toString() = " + md.toString()));
        */

        /* 페이징
        List<Member> ml = em.createQuery("SELECT m FROM Member m ORDER BY m.age DESC", Member.class)
                               .setFirstResult(0)
                               .setMaxResults(2)
                                .getResultList();
        System.out.println("ml.size() = " + ml.size());
        */

        /* 조인
        String query = "SELECT m FROM Member m INNER JOIN m.team t WHERE t.teamname = :teamname";
        List<Member> tl = em.createQuery(query, Member.class)
                            .setParameter("teamname", "teamA")
                            .getResultList();
        System.out.println("tl = " + tl.get(0).getTeam().getTeamname());

        String query = "SELECT m FROM Member m LEFT JOIN m.team ON t.name = teamA";
        List<Member> ml = em.createQuery(query, Member.class).getResultList();
         */

        /* Enum 활용 조건절
        String query = "SELECT m FROM Member m WHERE m.memberType = :memberType";
        List<Member> ml = em.createQuery(query, Member.class)
                            .setParameter("memberType", MemberType.ADMIN)
                            .getResultList();
        System.out.println("ml.get(0).getUsername() = " + ml.get(0).getUsername());
         */

        String query = "SELECT" +
                "CASE WHEN m.age <= " +
                "FROM Member m";

        tx.commit();
    }

}
