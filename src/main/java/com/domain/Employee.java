package com.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(allowGetters = true)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Employee implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  //定义主键生成策略
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String gender;
    @Column
    private Integer age;
//
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}