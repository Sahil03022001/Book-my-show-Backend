package com.example.BookmyshowApplication.Convertors;

import com.example.BookmyshowApplication.DTOs.Request.ShowReqDto;
import com.example.BookmyshowApplication.Models.Show;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShowConvertor {

    public static Show showReqDtoToShow(ShowReqDto showReqDto) {
        return Show.builder()
                .showDate(showReqDto.getShowDate())
                .showStartTime(showReqDto.getShowStartTime())
                .showEndTime(showReqDto.getShowEndTime())
                .build();
    }
}
