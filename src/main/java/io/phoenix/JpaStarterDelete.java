package io.phoenix;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterDelete {


    public static void main(String[]args)
    {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("myApp");

        EntityManager entityManager = entityManagerFactory.createEntityManager();  //is an object/service which JPA provides which manages the entities

        Employee employee = entityManager.find(Employee.class, 12);


        EntityTransaction entityTransaction =entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.remove(employee);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }


}
