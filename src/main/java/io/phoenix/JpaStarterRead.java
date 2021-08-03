package io.phoenix;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaStarterRead {

    public static void main(String[] args)
    {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager= entityManagerFactory.createEntityManager();

        /*Employee employee = entityManager.find(Employee.class, 1);
        System.out.println(employee);*/

       /* AccessCard card = entityManager.find(AccessCard.class, 2);
        System.out.println(card); // this prints only value within accesscaerd class*/

       EmailGroup emailGroup = entityManager.find(EmailGroup.class, 7);
        System.out.println("Got EMail group . Now accessing members");
        System.out.println(emailGroup.getMembers());
    }
}
