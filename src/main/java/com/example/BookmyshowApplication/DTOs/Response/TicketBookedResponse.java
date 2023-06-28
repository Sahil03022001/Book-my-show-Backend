package com.example.BookmyshowApplication.DTOs.Response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketBookedResponse {

    private int totalBill;
    private String movieName;
    private String theatreName;
    private LocalDate showDate;
    private LocalTime showStartTime;
    private LocalTime showEndTime;
    private String bookedSeats;
}