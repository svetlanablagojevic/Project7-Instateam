package com.teamtreehouse.instateam.model;


import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "collaborators")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", columnDefinition = "VARCHAR")
    @Pattern(regexp = "\\s*[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*\\s*",
            message = "Name must consist of alphanumeric characters: a-Z, 0-9")
    private String name;

    // relation to role class, Many Collaborators can have one Role
    // role removal detaches collaborators. Collaborators will get NULL
    // in their role_id primary key, see `RoleDaoImpl.delete` method for more
    @ManyToOne(cascade = CascadeType.DETACH)
    private Role role;



    public Collaborator(){

    }

    //getters and setters

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

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collaborator that = (Collaborator) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        return role != null ? role.equals(that.role) : that.role == null;

    }


}
