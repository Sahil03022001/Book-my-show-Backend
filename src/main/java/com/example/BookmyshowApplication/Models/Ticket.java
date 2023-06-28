package com.example.BookmyshowApplication.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class Ticket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private int totalBill;
    private String movieName;
    private String theatreName;

    private LocalDate showDate;
    private LocalTime showStartTime;
    private LocalTime showEndTime;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    List<ShowSeat> showSeatList = new ArrayList<>();

    @JoinColumn
    @ManyToOne
    private User user;
}