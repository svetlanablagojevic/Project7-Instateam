package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by GoranB on 2017-01-26.
 */

@Controller
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    //private Logger logger = Logger.getLogger(CollaboratorController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/collaborators")
    public String listCollaborators(Model model){
        if(!model.containsAttribute("collaborator")){
            model.addAttribute("collaborator", new Collaborator());
        }
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "collaborator/index";
    }


}
