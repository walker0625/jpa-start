package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("start");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        em.flush();
        em.clear();

        Member selectMFromMemberM = em.createQuery("select m from Member m where m.username = :username", Member.class).getSingleResult();




        tx.commit();

        em.close();
        emf.close();
    }

}
