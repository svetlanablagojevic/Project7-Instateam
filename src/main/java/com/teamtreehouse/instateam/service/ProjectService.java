package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Project;

import java.util.List;

/**
 * Created by GoranB on 2017-01-26.
 */
public interface ProjectService {

    List<Project> findAll();
    Project findById(Long id);
    void save(Project project);
    void delete(Project project);
}
