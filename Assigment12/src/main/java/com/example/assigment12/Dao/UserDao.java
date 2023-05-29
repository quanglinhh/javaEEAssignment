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

    public UserDao() {
        em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        tran = em.getTransaction();
    }

    public void saveUser(UserEntity user) {
        tran.begin();
        em.persist(user);
        tran.commit();
    }

    public List<UserEntity> getAllUser() {
        em.getTransaction().begin();
        String sql = "SELECT * FROM User";
        Query query = em.createNativeQuery(sql, UserEntity.class);
        List<UserEntity> users = query.getResultList();
        System.out.println(users);
        em.getTransaction().commit();
        return users;
    }

    public void deleteUserById(int id) {
        em.getTransaction().begin();

        String sql = "DELETE FROM User  WHERE User.id = :id";
        Query query = em.createNativeQuery(sql, UserEntity.class);
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public void updateUser(int userID, UserEntity user) {
        em.getTransaction().begin();
        String sql = "UPDATE User u SET u.name = :name, u.email = :email WHERE u.id = :userId";
        Query query = em.createNativeQuery(sql, UserEntity.class);
        query.setParameter("name", user.getName());
        query.setParameter("userId", userID);
        query.setParameter("email", user.getEmail());
        query.executeUpdate();

        tran.commit();
    }

    public UserEntity getUserById(int id) {
        em.getTransaction().begin();
        String sql = "SELECT * FROM User u WHERE u.id =:id";
        Query query = em.createNativeQuery(sql, UserEntity.class);
        query.setParameter("id", id);
        UserEntity user = (UserEntity) query.getSingleResult();
        em.getTransaction().commit();
        return user;
    }

    public List<UserEntity> getUserByName(String name) {
        em.getTransaction().begin();
        String sql = "SELECT * FROM User u WHERE u.name =:name";
        Query query = em.createNativeQuery(sql, UserEntity.class);
        query.setParameter("name", name);
        List<UserEntity> users = query.getResultList();
        em.getTransaction().commit();
        return users;
    }

}
