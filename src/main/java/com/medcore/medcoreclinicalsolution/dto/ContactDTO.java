package com.medcore.medcoreclinicalsolution.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactDTO {

    @Size(min = 2, max = 50)
    private String name;

    private String company;

    private String fields;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;


    @Size(min = 5, max = 500)
    private String message;

    private Boolean status;

    private LocalDate created_at;

}