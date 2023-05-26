package com.example.assigment12.Dao;

import com.example.assigment12.Util.PersistenceUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.example.assigment12.model.UserEntity;

import java.util.List;


public class UserDao {
    EntityManager em;
    EntityTransaction tran;

    public UserDao (){
        em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        tran = em.getTransaction();
    }


    public void deleteUserByName(String name) {
        em.getTransaction().begin();

        String sql = "DELETE FROM UserEntity u WHERE u.name = :name";
        Query query = em.createNativeQuery(sql);
        query.setParameter("name", name);
        query.executeUpdate();

        em.getTransaction().commit();
    }

    public void updateUser(int userID, UserEntity user){
      em.getTransaction().begin();
      String sql = "UPDATE UserEntity u SET u.name =:name WHERE u.id=:userId";
      Query query = em.createNativeQuery(sql);
        query.setParameter("name", user.getName());
        query.setParameter("userId",userID);
        query.executeUpdate();

        em.getTransaction().commit();
    }

    public List<UserEntity> getUserByName(String name){
        em.getTransaction().begin();
        String sql = "SELECT FROM UserEntity u WHERE u.name =: name";
        Query query = em.createNativeQuery(sql);
        query.setParameter("name", name);
        List<UserEntity> users = query.getResultList();
        em.getTransaction().commit();
        return users;
    }
    
}
