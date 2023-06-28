package com.example.BookmyshowApplication.Repository;

import com.example.BookmyshowApplication.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
