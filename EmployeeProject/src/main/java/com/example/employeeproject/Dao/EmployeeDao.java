package com.example.employeeproject.Dao;

import com.example.employeeproject.Util.PersistenceUtil;
import com.example.employeeproject.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class EmployeeDao {
    EntityManager em;
    EntityTransaction tran;

    public EmployeeDao() {
        em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        tran = em.getTransaction();
    }

    public void saveEmployee(Employee employee){
        tran.begin();
        em.persist(employee);
        tran.commit();
    }

    public List<Employee> getAllEmployee() {
        em.getTransaction().begin();
        String sql = "SELECT * FROM User";
        Query query = em.createNativeQuery(sql, Employee.class);
        List<Employee> users = query.getResultList();
        System.out.println(users);
        em.getTransaction().commit();
        return users;
    }

}
