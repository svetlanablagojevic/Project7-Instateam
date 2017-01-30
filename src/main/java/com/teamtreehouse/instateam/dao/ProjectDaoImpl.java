package com.teamtreehouse.instateam.dao;


import com.teamtreehouse.instateam.model.Project;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> findAll() {
        Session session=sessionFactory.openSession();
        //Get all categories with a Hibernate criteria
        List<Project> projects=session.createCriteria(Project.class).list();
        //Close the session
        session.close();

        return projects;
    }

    @Override
    public void save(Project project) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(project);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public Project findById(Long projectId) {
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, projectId);
        Hibernate.initialize(project.getRolesNeeded());
        Hibernate.initialize(project.getCollaboratorsAssigned());
        session.close();
        return project;
    }

    @Override
    public void delete(Project project) {

    }
}
