package com.ra.model.dao;

import com.ra.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            list = session.createQuery("from Category", Category.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Category findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Category category = session.get(Category.class, id);
            return category;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean save(Category category) {
        Session session = sessionFactory.openSession();
        try {
            if (category.getId() == null) {
                session.beginTransaction();
                session.save(category);
                session.getTransaction().commit();
                return true;
            } else {
                session.beginTransaction();
                session.update(category);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
