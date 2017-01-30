package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by GoranB on 2017-01-26.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    // index of all categories
    @SuppressWarnings("unchecked")
    @RequestMapping("/roles")
    public String listRoles(Model model) {
        // Get all roles
        List<Role> roles = roleService.findAll();
        // add roles to model
        model.addAttribute("roles", roles);
        // if user typed wrong data it will be shown
        if (!model.containsAttribute("newRole")) {
            // add empty newRole so that we can fill it with
            // data, when making POST request on this page
            model.addAttribute("newRole", new Role());
        }
        return "role/index";
    }

    // Form for adding new role
    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public String addNewRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.newRole", result);
            redirectAttributes.addFlashAttribute("newRole", role);
            return "redirect:/roles";
        }
        // saveOrUpdate role to database
        roleService.save(role);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("New Role: '" + role.getName() +
                        "' was successfully added!",
                        FlashMessage.Status.SUCCESS));
        // redirect back to same page
        return "redirect:/roles";
    }




}
