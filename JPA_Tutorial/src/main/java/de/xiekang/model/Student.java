package de.xiekang.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false, unique = false, length = 50)
    private String name;

    @Transient
    private int age;

    @Temporal(TemporalType.DATE)
    private Date birthDate;
}
