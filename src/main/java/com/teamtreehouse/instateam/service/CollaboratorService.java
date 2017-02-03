package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Collaborator;

import java.util.List;

/**
 * Created by GoranB on 2017-01-25.
 */
public interface CollaboratorService {
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}