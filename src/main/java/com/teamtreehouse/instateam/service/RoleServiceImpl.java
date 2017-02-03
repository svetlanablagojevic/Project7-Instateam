package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.dao.RoleDao;
import com.teamtreehouse.instateam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GoranB on 2017-01-26.
 */
@Service
public class RoleServiceImpl implements RoleService{

    // The RoleDao object is autowired/injected at runtime.
    @Autowired
    private RoleDao roleDao;

    // This method retrieves a list with all the available role objects from the database.
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    // This method retrieves a specific role object from the database, using an identifier.
    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    // This method saves a new role object or updates an existing one from the database.
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    // This method deletes an existing role object from the database.
    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }
}