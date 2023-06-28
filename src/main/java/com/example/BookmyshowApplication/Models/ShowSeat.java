package com.example.BookmyshowApplication.Models;

import com.example.BookmyshowApplication.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "show_seats")
public class ShowSeat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private int price;
    private boolean isAvailable;

    @JoinColumn
    @ManyToOne
    private Show show;

    @JoinColumn
    @ManyToOne
    private Ticket ticket;
}