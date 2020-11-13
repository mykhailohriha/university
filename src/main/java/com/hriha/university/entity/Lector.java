package com.hriha.university.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lectors")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "degree")
    private Lector.Degree degree;

    @Column(name = "salary")
    private int salary;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "headOfDepartment")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "lector_departments",
            joinColumns = {@JoinColumn(name = "lector_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    private Set<Department> departments = new HashSet<>();

    public Lector() {

    }

    public Lector(String fullName, int salary, Degree degree) {
        this.fullName = fullName;
        this.salary = salary;
        this.degree = degree;
    }

    public enum Degree {
        assistant, associateProfessor, professor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
