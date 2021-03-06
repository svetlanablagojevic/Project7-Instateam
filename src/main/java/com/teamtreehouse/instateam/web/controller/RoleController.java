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

/**
 * Created by GoranB on 2017-01-26.
 */
@Controller
public class RoleController {


    @Autowired
    private RoleService roleService;


    @RequestMapping("/roles")
    public String listRoles(Model model){
        if(!model.containsAttribute("role")){
            model.addAttribute("role", new Role());
        }
        model.addAttribute("roles", roleService.findAll());
        return "role/index";
    }

    //add role
    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public String addRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("The name you have used for the role is invalid. Please try again.",
                            FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("role", role);
            return "redirect:/roles";
        }
        roleService.save(role);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Role '%s' has been successfully added.", role.getName()),
                FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }

    //detail role page
    @RequestMapping(value = "/roles/{roleId}/detail")
    public String roleDetail(@PathVariable Long roleId, Model model){
        Role role = roleService.findById(roleId);
        model.addAttribute("role", role);
        return "role/detail";
    }



    //  edit role
    @RequestMapping(value = "/roles/{roleId}/edit", method = RequestMethod.POST)
    public String editRole(@Valid Role role, @PathVariable Long roleId,BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("The name you have used for the role is invalid. Please try again.",
                    FlashMessage.Status.FAILURE));
            return String.format("redirect:/roles/%s/detail", role.getId());
        }
        String oldName = roleService.findById(roleId).getName();
        roleService.save(role);
        String newName = role.getName();

        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Role '%s' has been renamed to '%s'.", oldName, newName) ,
                FlashMessage.Status.SUCCESS));

        return "redirect:/roles";
    }

    //delete role
    @RequestMapping(value = "/roles/{roleId}/delete", method = RequestMethod.POST)
    public String deleteRole(@PathVariable Long roleId, RedirectAttributes redirectAttributes){
        Role role = roleService.findById(roleId);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Role '%s' has been deleted successfuly.", role.getName()),
                FlashMessage.Status.SUCCESS));
        roleService.delete(role);
        return "redirect:/roles";
    }
}
