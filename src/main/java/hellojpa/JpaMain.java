package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            Insert
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            */

            /*
            Select

            Member member = em.find(Member.class, 2L);

            Update
            member.setName("modi");
            */

            /*
            Select
            Member member = em.find(Member.class, 2L);

            Delete
            em.remove(member);
            */

            /*
            JPQL
            List<Member> memberList= em.createQuery("select m from Member as m", Member.class)
                                       .setFirstResult(1)
                                       .setMaxResults(10)
                                       .getResultList();

            for (Member member : memberList) {
                System.out.println("member.getName() = " + member.getName());
            }
             */

            // Application -> 영속성 컨텍스트 -> DB
            /*
            1. 1차 캐시 : 한 쓰레드의 한 트랜잭션 내에서만 사용됨 - 일회성
            2. repeatable read 수준의 트랜잭션 격리를 지원(영속성 컨텍스트 내의 정보 읽기)
            3. 트랜잭션을 통해 쓰기 지연을 지원(commit 이전까지 query 지연)
            4. dirty checking(변경 감지) - 스냅샷(db에서 읽어온 값)과 entity(변경된 값)을 비교하여 차이가 있으면 update
            */

            // 비영속 상태
            Member member = new Member(3L, "C");

            // 영속 상태
            em.persist(member);

            // 준영속 상태 - 영속 상태 제거
            em.detach(member); // 특정 entity 제거
            em.clear(); // 1차 캐시 전체 제거

            // DB에 Query 전송(1차 캐시에는 계속 남아 있음)
            em.flush(); // 실제 사용은 거의 하지 않음(query 확인용) / jpql 쿼리 실행하는 경우도 자동 호출됨

            tx.commit(); // + em.flush();
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 1차 캐시 종료
        }

        emf.close();
    }

}
