package com.example.BookmyshowApplication.Service;

import com.example.BookmyshowApplication.Convertors.MovieConvertor;
import com.example.BookmyshowApplication.DTOs.Request.MovieReqDto;
import com.example.BookmyshowApplication.Models.Movie;
import com.example.BookmyshowApplication.Repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public String addMovie(MovieReqDto movieReqDto) {
        Movie movie = MovieConvertor.movieReqDtoToMovie(movieReqDto);
        movieRepository.save(movie);
        return "Movie Added";
    }
}