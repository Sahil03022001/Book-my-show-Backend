package com.example.BookmyshowApplication.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowReqDto {

    private LocalDate showDate;
    private LocalTime showStartTime;
    private LocalTime showEndTime;

    private int movieId;
    private int theatreId;
}
