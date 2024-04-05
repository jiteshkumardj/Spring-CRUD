package com.Projects.springcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer age;
    private String address;
}
