package com.bugratasdemir.graduationproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Version
    @Column(columnDefinition = "bigint default 0")
    private Long version = 0L;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
    @PrePersist
    void onCreate() {
        this.setCreateDate(new Timestamp((new Date()).getTime()).toLocalDateTime());
    }

    @PreUpdate
    void onPersist() {
        this.setUpdateDate(new Timestamp((new Date()).getTime()).toLocalDateTime());
    }

}
