package com.example.BookmyshowApplication.Controllers;

import com.example.BookmyshowApplication.DTOs.Request.MovieReqDto;
import com.example.BookmyshowApplication.Service.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieReqDto movieReqDto) {
        return movieService.addMovie(movieReqDto);
    }
}
