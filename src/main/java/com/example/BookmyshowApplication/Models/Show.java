package com.example.BookmyshowApplication.Models;

import com.example.BookmyshowApplication.Enums.ShowStatus;
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
@Table(name = "shows")
public class Show {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private LocalDate showDate;
    private LocalTime showStartTime;
    private LocalTime showEndTime;

    @Enumerated(EnumType.STRING)
    private ShowStatus showStatus;

    @ManyToOne
    @JoinColumn
    private Movie movie;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    List<ShowSeat> showSeatList = new ArrayList<>();
}