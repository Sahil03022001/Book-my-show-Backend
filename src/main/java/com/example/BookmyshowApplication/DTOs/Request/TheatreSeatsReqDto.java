package com.example.BookmyshowApplication.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheatreSeatsReqDto {

    private int theatreId;
    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private int noOfSeatsInEachRow;
}
