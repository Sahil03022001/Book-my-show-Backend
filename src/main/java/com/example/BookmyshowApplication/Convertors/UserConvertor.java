package com.example.BookmyshowApplication.Convertors;

import com.example.BookmyshowApplication.DTOs.Request.UserReqDto;
import com.example.BookmyshowApplication.Models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConvertor {

    public static User userReqDtoToUser(UserReqDto userReqDto) {
        return User.builder()
                .name(userReqDto.getName())
                .age(userReqDto.getAge())
                .mobileNo(userReqDto.getMobileNo())
                .emailId(userReqDto.getEmailId())
                .build();
    }
}
