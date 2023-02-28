package com.app.clientsdataapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDTO {
    private String firstname;
    private String lastname;
    private String middlename;
    private Integer yearOfBirth;
    private String gender;
    private String occupation;
}
