package com.ashokit.UAM.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String passKey;
    private long phoneNumber;
    private Date birthDate;
}
