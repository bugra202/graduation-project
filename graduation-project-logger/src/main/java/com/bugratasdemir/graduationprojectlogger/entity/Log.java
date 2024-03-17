package com.bugratasdemir.graduationprojectlogger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "LOG")
public class Log{

    @SequenceGenerator(name = "sequenceGenerator",sequenceName = "S_LOG",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenceGenerator")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "DATE",nullable = false)
    private LocalDateTime date;

    @Column(name = "MESSAGE",length = 200,nullable = false)
    private String message;

    @Column(name = "DESCRIPTION",nullable = false)
    private String description;
}