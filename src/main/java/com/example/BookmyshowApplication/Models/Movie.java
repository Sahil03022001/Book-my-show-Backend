package com.example.BookmyshowApplication.Models;

import com.example.BookmyshowApplication.Enums.Genre;
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
@Table(name = "movies")
public class Movie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @Column(scale = 2)
    private double rating;

    @Column(scale = 2)
    private double duration;

    private String directorName;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<Show> showList = new ArrayList<>();
}