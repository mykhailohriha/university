package com.hriha.university.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "departments")
    private Set<Lector> lectors = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "up_id")
    private Lector headOfDepartment;

    public Department() {

    }

    public Department(String name, Lector headOfDepartment) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
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

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }

    public Lector getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Lector headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }
}
