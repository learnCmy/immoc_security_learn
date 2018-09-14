package com.imooc.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


public class User  implements Serializable {

    private static final long serialVersionUID = -3962455663307765887L;
    private Integer id;
    @NotEmpty
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}
