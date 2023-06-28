package com.example.BookmyshowApplication.DTOs.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {

    private String name;
    private int age;
    private String mobileNo;
    private String emailId;
}
