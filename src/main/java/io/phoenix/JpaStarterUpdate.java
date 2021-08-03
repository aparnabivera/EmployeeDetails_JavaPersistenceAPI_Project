package io.phoenix;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterUpdate {


    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("myApp");

        EntityManager entityManager = entityManagerFactory.createEntityManager();  //is an object/service which JPA provides which manages the entities

        Employee employee = entityManager.find(Employee.class, 2);
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 8);

        employee.addEmailSubscription(emailGroup);
        emailGroup.addMembers(employee);

        EntityTransaction entityTransaction =entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(employee);
        entityManager.persist(emailGroup);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }


}
