package com.example.BookmyshowApplication.Convertors;

import com.example.BookmyshowApplication.DTOs.Request.BookTicketReqDto;
import com.example.BookmyshowApplication.DTOs.Response.TicketBookedResponse;
import com.example.BookmyshowApplication.Models.Show;
import com.example.BookmyshowApplication.Models.Ticket;
import com.example.BookmyshowApplication.Models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketConvertor {

    public static Ticket makeTicket(Show show, User user, int totalBill) {
        Ticket ticket = new Ticket();
        ticket.setMovieName(show.getMovie().getName());
        ticket.setTheatreName(show.getTheatre().getName());
        ticket.setTotalBill(totalBill);
        ticket.setShowDate(show.getShowDate());
        ticket.setShowStartTime(show.getShowStartTime());
        ticket.setShowEndTime(show.getShowEndTime());
        return ticket;
    }

    public static TicketBookedResponse ticketToTicketBookedResponse(Ticket ticket) {
        return TicketBookedResponse.builder()
                .movieName(ticket.getMovieName())
                .theatreName(ticket.getTheatreName())
                .showStartTime(ticket.getShowStartTime())
                .showEndTime(ticket.getShowEndTime())
                .showDate(ticket.getShowDate())
                .totalBill(ticket.getTotalBill())
                .build();
    }
}
