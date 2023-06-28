package com.example.BookmyshowApplication.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "theatres")
public class Theatre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<TheatreSeat> theatreSeatList = new ArrayList<>();

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
}