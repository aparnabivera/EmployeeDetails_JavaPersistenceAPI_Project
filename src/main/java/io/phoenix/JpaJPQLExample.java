package io.phoenix;

import org.hibernate.query.QueryProducer;

import javax.persistence.*;
import java.util.List;

public class JpaJPQLExample {

    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ///fetch Employee name is  "Foo Bar"
        //how do you run a query

        // getting a generic list
        Query query= entityManager.createQuery("select e from Employee e");   // Employee name of entity class
        List resultList = query.getResultList();

        //getting a typed query list
        TypedQuery<Employee>  typedQuery= entityManager.createQuery("select e from Employee e", Employee.class);   // Employee name of entity class
                                                                                                            // e is an Alias for * in sql. This is JPQL query format
        List<Employee> typeresultList = typedQuery.getResultList();
        typeresultList.forEach(System.out::println);

        // writing a specific query
        TypedQuery<Employee>  typedQuery1= entityManager.createQuery("select e from Employee e where e.age >25", Employee.class);   // Employee name of entity class
                                                                                                                         // e is an Alias for * in sql. This is JPQL query format
        List<Employee> typeresultList1 = typedQuery1.getResultList();
        typeresultList1.forEach(System.out::println);



        //order by query , ordering Employee records in name descending order
        TypedQuery<Employee>  typedQuery2= entityManager.createQuery("select e from Employee e order by e.name desc", Employee.class);   // Employee name of entity class
        // e is an Alias for * in sql. This is JPQL query format
        List<Employee> typeresultList2 = typedQuery2.getResultList();
        typeresultList2.forEach(System.out::println);

        // using Like, to find employees whose we dont know fully
        TypedQuery<Employee>  typedQuery3= entityManager.createQuery("select e from Employee e where e.name like '%Bar%'",
                Employee.class

        );

        List<Employee> typeresultList3 = typedQuery3.getResultList();
        typeresultList3.forEach(System.out::println);

        //Between
        TypedQuery<Employee>  typedQuery4= entityManager.createQuery("select e from Employee e where e.age between 22 and 32",
                Employee.class

        );

        List<Employee> typeresultList4 = typedQuery4.getResultList();
        typeresultList4.forEach(System.out::println);

        // Joins with relationship

        TypedQuery<Employee> typedQuery5= entityManager.createQuery("select e from Employee e where e.card_id = true",  //sql query = select e from EMPLOYEE_DATA e JOIN e.card_id = a.id and a.isactive = true
                Employee.class                                                                                   // since in jpa these tables are already joined using relationship we dont have to mention join in the jpql query
              /*  "select e from Employee e join AccessCard a on e.id = a.id" implementing jpql join query if required*/
        );

        List<Employee> typeresultList5 = typedQuery5.getResultList();
        typeresultList5.forEach(System.out::println);

        //freeform queries and custom result types
        //getting Employee names from the db without getting the whole table values

        TypedQuery<String> typedQuery6= entityManager.createQuery("select e.name from Employee e ",
                String.class

        );
        List<String> empNames = typedQuery6.getResultList();
        empNames.forEach(System.out::println);

        //freeform queries and custom result
        //get name and age of all employee

        TypedQuery<Object[]> typedQuery7= entityManager.createQuery("select e.name from Employee e ",
                Object[].class

        );
        List<Object[]> empNamesandempAge = typedQuery7.getResultList();
        empNamesandempAge.forEach(e -> System.out.println(e[0]+" "+e[1]));

        //freeform queries and custom result
        //get emp name from employee table and issuedDate from Accesscard table using join funtionality

        TypedQuery<Object[]> joinTableResultquery= entityManager.createQuery("select e.name, e.card.issuedDate from Employee e ",  //select e.name, c.issuedDate from Employee e, AccessCard c where e.card.id = c.id
                Object[].class

        );
        List<Object[]> joinTableList = joinTableResultquery.getResultList();
        joinTableList.forEach(e -> System.out.println(e[0]+" "+e[1]));

        //Return List of Employee older than 25
        // using setParameter to prevent sql injection
        // setParameter prevents adding another query into the variable minAge thus preventing SQL injection

        int minAge = 25;
        TypedQuery<Employee> typedQuery8 = entityManager.createQuery(
                "select e from Employee e where e.age > :minAge",
                Employee.class
        );
        typedQuery8.setParameter("minAge", minAge);
        List<Employee> empList = typedQuery8.getResultList();
        empList.forEach(System.out::println);

          //named query usage
        TypedQuery<Employee>  namedQuery= entityManager.createQuery("emp name asc", Employee.class);

        List<Employee> typeresultNamedList = namedQuery.getResultList();
        typeresultNamedList.forEach(System.out::println);


        //named query with parameter
        TypedQuery<Employee>  namedQuerywithParam= entityManager.createQuery("emp age greater", Employee.class);
        namedQuerywithParam.setParameter("age", 25);

        List<Employee> typeresultNamedListwithParam = namedQuerywithParam.getResultList();
        typeresultNamedListwithParam.forEach(System.out::println);



        entityManager.close();
        entityManagerFactory.close();
    }


}
