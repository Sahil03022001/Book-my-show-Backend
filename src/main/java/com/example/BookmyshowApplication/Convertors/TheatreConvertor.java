package com.example.BookmyshowApplication.Convertors;

import com.example.BookmyshowApplication.DTOs.Request.TheatreReqDto;
import com.example.BookmyshowApplication.Models.Theatre;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TheatreConvertor {

    public static Theatre theatreReqDtoToTheatre(TheatreReqDto theatreReqDto) {
        return Theatre.builder()
                .name(theatreReqDto.getName())
                .location(theatreReqDto.getLocation())
                .build();
    }
}
