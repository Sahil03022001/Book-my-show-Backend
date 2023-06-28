package com.example.BookmyshowApplication.Repository;

import com.example.BookmyshowApplication.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
