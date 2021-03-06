package io.phoenix;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class JpaStarterWriter {


    public static void main(String[] args)
    {





        Employee emp = new Employee();
        //emp.setId(1); Id is auto generated by JPA
        emp.setName("Harry");
        emp.setSsn("678");
        emp.setDate(new Date());
        emp.setType(EmployeeType.FULL_TIME);


        Employee emp1 = new Employee();
        //emp1.setId(1);
        emp1.setName("Jose");
        emp.setSsn("123");
        emp1.setDate(new Date());
        emp1.setType(EmployeeType.CONTRACTOR);

        Employee emp2 = new Employee();
        //emp2.setId();
        emp2.setName("Manu");
        emp.setSsn("456");
        emp2.setDate(new Date());
        emp2.setType(EmployeeType.FULL_TIME);

        AccessCard card = new AccessCard();
        card.setIssueDate(new Date());
        card.setActive(false);
        card.setFirmwareVersion("1.2.3");
        card.setOwner(emp);
        emp.setCard(card);

        AccessCard card1 = new AccessCard();
        card1.setIssueDate(new Date());
        card1.setActive(false);
        card1.setFirmwareVersion("1.0.0");
        card1.setOwner(emp1);
        emp1.setCard(card1);

        AccessCard card2 = new AccessCard();
        card2.setIssueDate(new Date());
        card2.setActive(false);
        card2.setFirmwareVersion("1.2.3");
        card2.setOwner(emp2);
        emp2.setCard(card2);

        PayStub payStub = new PayStub();
        payStub.setPayPeriodEnd(new Date());
        payStub.setPayPeriodStart(new Date());
        payStub.setSalary(10000);
        payStub.setEmployee(emp);
        emp.addPayStub(payStub);

        PayStub payStub1 = new PayStub();
        payStub1.setPayPeriodEnd(new Date());
        payStub1.setPayPeriodStart(new Date());
        payStub1.setSalary(45000);
        payStub.setEmployee(emp);
        emp1.addPayStub(payStub1);


       EmailGroup group = new EmailGroup();
       group.setName("Company watercooler discussions");
       group.addMembers(emp);
       group.addMembers(emp1);
       emp.addEmailSubscription(group);
       emp1.addEmailSubscription(group);


        EmailGroup group1 = new EmailGroup();
        group1.setName("Engineering");
        emp.addEmailSubscription(group1);
        group1.addMembers(emp);


        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("myApp");

        EntityManager entityManager = entityManagerFactory.createEntityManager();  //is an object/service which JPA provides which manages the entities
        EntityTransaction entityTransaction =entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(emp);
        entityManager.persist(emp1);
        entityManager.persist(emp2);

        entityManager.persist(card);
        entityManager.persist(card1);
        entityManager.persist(card2);

        entityManager.persist(payStub);
        entityManager.persist(payStub1);

        entityManager.persist(group);
        entityManager.persist(group1);

        entityTransaction.commit();


        //Read operation in JPA

        Employee employee = entityManager.find(Employee.class, 1); // mention entity class name and primary key value Select * Employee_Data where id =1;
        Employee employee1 = entityManager.find(Employee.class, 2);
        Employee employee3 = entityManager.find(Employee.class, 3);
        System.out.println(employee);
        System.out.println(employee1);
        System.out.println(employee3); // if there is not emp value for id 3 it is going to return null*/

        //update operation in jpa

        emp.setName("Nannu");
        emp1.setSsn("956");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(emp);
        entityManager.persist(emp1);
        transaction.commit();

        //delete operation in jpa
        Employee empRemove = entityManager.find(Employee.class, 1);
        transaction.begin();
        entityManager.remove(empRemove);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
