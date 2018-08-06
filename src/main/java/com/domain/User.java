package com.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")

public class User implements Serializable{

//    @GeneratedValue(strategy = GenerationType.IDENTITY)  //定义主键生成策略
    @Id
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

}
