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
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;
    private int age;

    @Column(unique = true)
    private String mobileNo;

    @Column(unique = true)
    private String emailId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Ticket> ticketList = new ArrayList<>();
}
