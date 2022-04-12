package com.example.bitdefenderbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "team")
    private Set<Employee> employees = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

}
