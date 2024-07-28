package com.a2m.project.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    @JsonProperty("email")
    private String email;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
