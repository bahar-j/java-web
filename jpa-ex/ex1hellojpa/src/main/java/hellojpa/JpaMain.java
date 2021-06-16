package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 영속
//            System.out.println("=== BEFORE ===");
//            entityManager.persist(member);
//            System.out.println("=== AFTER ===");
//
//            // 저장할 때 1차 캐시에도 저장하므로 select 쿼리 안날림
//            Member findMember = entityManager.find(Member.class, 101L);
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName());

            
//            Member member = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            entityManager.persist(member);
//            entityManager.persist(member2);
//            System.out.println("=======================");


            Member findMember = entityManager.find(Member.class, 101L);
            findMember.setName("updatedName");

            Member findAgainMember = entityManager.find(Member.class, 101L);
            System.out.println(findAgainMember.getName()); // DB 반영 전이지만 1차 캐시에서 가져오니까 변경되어있음!

            //실제로 DB에 쿼리 날리는 시점 - commit 직전
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
