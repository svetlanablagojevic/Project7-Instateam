package com.teamtreehouse.instateam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "projects")
@Entity
public class Project {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // project name: alphanumeric VARCHAR
    @Column(name = "NAME", columnDefinition = "VARCHAR")
    @NotNull
    @Pattern(regexp = "\\s*[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*\\s*",
            message = "Name must consist of alphanumeric characters: a-Z, 0-9")
    private String name;

    // project description: for now it simply cannot be empty or null
    // can be changed later
    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR")
    @NotNull(message = "Description cannot be empty")
    private String description;

    @Enumerated
    private ProjectStatus status;

    @Column
    private Date dateCreated;

    @ManyToMany
    private List<Role> rolesNeeded = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Collaborator> collaboratorsAssigned = new ArrayList<>();

    public Project(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Role> getRolesNeeded() {
        return rolesNeeded;
    }

    public void setRolesNeeded(List<Role> rolesNeeded) {
        this.rolesNeeded = rolesNeeded;
    }

    public List<Collaborator> getCollaboratorsAssigned() {
        return collaboratorsAssigned;
    }

    public void setCollaboratorsAssigned(List<Collaborator> collaboratorsAssigned) {
        this.collaboratorsAssigned = collaboratorsAssigned;
    }

    public void addRole(Role role){
        rolesNeeded.add(role);
    }

    public void addCollaborator(Collaborator collaborator){
        collaboratorsAssigned.add(collaborator);
    }

}
