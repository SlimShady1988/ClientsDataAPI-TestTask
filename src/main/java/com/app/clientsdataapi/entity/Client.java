package com.app.clientsdataapi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="clients")
public class Client {
    @Id
    private String id;
    private String firstname;

    private String middlename;
    private String lastname;
    private Integer yearOfBirth;
    private Gender gender;
    private String occupation;

    public Client(String firstname,
                  String lastname,
                  String middlename,
                  Integer yearOfBirthday,
                  Gender gender,
                  String occupation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.yearOfBirth = yearOfBirthday;
        this.gender = gender;
        this.occupation = occupation;
    }
}
