package com.bugratasdemir.graduationproject.entity;

import com.bugratasdemir.graduationproject.enums.RateState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity{

    @SequenceGenerator(name = "sequenceGenerator",sequenceName = "S_COMMENT",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenceGenerator")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "CONTENT",length = 500,nullable = false)
    private String content;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User user;

    @Column(name = "COMMENT_DATE")
    private LocalDateTime commentDate;

    @Column(name = "RESTAURANT_ID",nullable = false)
    private Long restaurantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "RATE",length = 30,nullable = false)
    private RateState rate;

}
