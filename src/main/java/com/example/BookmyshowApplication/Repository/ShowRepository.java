package com.example.BookmyshowApplication.Repository;

import com.example.BookmyshowApplication.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
}
