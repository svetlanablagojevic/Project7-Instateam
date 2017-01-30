package com.teamtreehouse.instateam.dao;

import com.teamtreehouse.instateam.model.Project;

import java.util.List;

/**
 * Created by GoranB on 2017-01-26.
 */
public interface ProjectDao {
    List<Project> findAll();
    void save(Project project);
    Project findById(Long projectId);
    void delete(Project project);
}
