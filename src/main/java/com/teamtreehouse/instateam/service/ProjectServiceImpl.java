package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.dao.ProjectDao;
import com.teamtreehouse.instateam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GoranB on 2017-01-26.
 */
@Service
public class ProjectServiceImpl implements ProjectService{

    // The ProjectDao object is autowired/injected at runtime.
    @Autowired
    private ProjectDao projectDao;

    // This method retrieves a list with all the available project objects from the database.
    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    // This method retrieves a specific project object from the database, passing an identifier.
    @Override
    public Project findById(Long id) {
        return projectDao.findById(id);
    }

    // This method saves a new project object or updates an existing one from the database.
    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    // This method deletes an existing project from the database.
    @Override
    public void delete(Project project) {
        projectDao.delete(project);
    }
}

