package com.example.BookmyshowApplication.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatsReqDto {

    private int showId;
    private int priceOfClassicSeat;
    private int priceOfPremiumSeat;
}
