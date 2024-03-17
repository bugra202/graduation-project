package com.bugratasdemir.graduationproject.entity;

import com.bugratasdemir.graduationproject.enums.GenderState;
import com.bugratasdemir.graduationproject.enums.UserState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @SequenceGenerator(name = "sequenceGenerator",sequenceName = "S_USER",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenceGenerator")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "USER_NAME",length = 200,nullable = false)
    private String name;

    @Column(name = "USER_SURNAME",length = 200,nullable = false)
    private String surname;

    @Column(name = "BIRTH_DATE",nullable = false)
    private LocalDate birthDate;

    @Column(name = "USER_EMAIL",length = 200,nullable = false)
    private String email;

    @Column(name = "LATITUDE",scale = 4,nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE",scale = 4,nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER",length = 30,nullable = false)
    private GenderState gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS",length = 30,nullable = false)
    private UserState status;

    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "user",
    targetEntity = Comment.class)
    private Set comments = new HashSet<>();
}