package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (!model.containsAttribute("role")) {
            // add empty newRole so that we can fill it with
            // data, when making POST request on this page
            model.addAttribute("role", new Role());
        }
        return "role/index";
    }

    // Form for adding new role
    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public String addNewRole(@Valid Role role,BindingResult result,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println(result);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.newRole", result);
            redirectAttributes.addFlashAttribute("role", role);
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

    @RequestMapping(value = "/role/{id}")
    public String editRole(Model model, @PathVariable Long id) {
        Role role= roleService.findById(id);
        if (!model.containsAttribute("role")){
            model.addAttribute("role", role);
        }

        return "/role/edit";
    }

    //update role
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public String updateRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("role", role);
            return String.format("redirect:/role/%s", role.getId());
        }

        roleService.save(role);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role:'" + role.getName() + "'updated!", FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }

  //delete role

    @RequestMapping(value = "/roles/{roleId}/delete")
    public String deleteRole(@PathVariable Long roleId, RedirectAttributes redirectAttributes) {
        //find role by id
        Role role = roleService.findById(roleId);
        // if not found throw not found exception
        if (role == null) {
            //throw new NotFoundException("Role not Found");
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role not found!", FlashMessage.Status.FAILURE));
        }
        // delete from database
        roleService.delete(role);
        // success flash message
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role: '" + role.getName() + "' was successfully deleted!", FlashMessage.Status.SUCCESS));
        // redirect back to page with roles
        return "redirect:/roles";
    }

}