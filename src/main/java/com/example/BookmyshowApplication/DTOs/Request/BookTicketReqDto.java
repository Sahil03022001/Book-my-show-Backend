package com.example.BookmyshowApplication.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketReqDto {

    private Integer showId;
    private Integer userId;
    List<String> requestedSeats;
}
