package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.model.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GoranB on 2017-01-25.
 */
@Service
public class CollaboratorServiceImpl implements CollaboratorService{

    // The collaboratorDao object is autowired/injected at runtime.
    @Autowired
    private CollaboratorDao collaboratorDao;

    // This method retrieves a list with all the available collaborators from the database.
    @Override
    public List<Collaborator> findAll() {
        return collaboratorDao.findAll();
    }

    // This method retrieves a a specific collaborator object from the database.
    @Override
    public Collaborator findById(Long id) {
        return collaboratorDao.findById(id);
    }

    // This method saves a new collaborator object or updates an existing one from the database.
    @Override
    public void save(Collaborator collaborator) {
        collaboratorDao.save(collaborator);
    }

    // This method deletes an existing collaborator from the database.
    @Override
    public void delete(Collaborator collaborator) {
        collaboratorDao.delete(collaborator);
    }
}