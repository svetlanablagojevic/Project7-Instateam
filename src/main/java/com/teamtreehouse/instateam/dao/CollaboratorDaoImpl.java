package com.teamtreehouse.instateam.dao;

import com.teamtreehouse.instateam.model.Collaborator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GoranB on 2017-01-26.
 */
@Repository
public class CollaboratorDaoImpl implements CollaboratorDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Collaborator> findAll() {
        //Open a session
        Session session=sessionFactory.openSession();
        //Get all categories with a Hibernate criteria
        List<Collaborator> collaborators=session.createCriteria(Collaborator.class).list();

        //Close the session
        session.close();

        return collaborators;
    }

    @Override
    public Collaborator findById(Long id) {
        Session session=sessionFactory.openSession();
        Collaborator collaborator=session.get(Collaborator.class, id);
        //Hibernate.initialize(collaborator.get);
        session.close();
        return collaborator;
    }

    @Override
    public void save(Collaborator collaborator) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(collaborator);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(Collaborator collaborator) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(collaborator);
        session.getTransaction().commit();
        session.close();

    }
}
