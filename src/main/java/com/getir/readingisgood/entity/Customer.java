package com.getir.readingisgood.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode
@Document(collection ="customer")
public class Customer implements BaseEntity{


    @Id
    private String id;

    private String name;

    private String surname;

    private String email;

    private String password;

}
