package com.example.BookmyshowApplication.Convertors;

import com.example.BookmyshowApplication.DTOs.Request.MovieReqDto;
import com.example.BookmyshowApplication.Models.Movie;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieConvertor {

    public static Movie movieReqDtoToMovie(MovieReqDto movieReqDto) {
        return Movie.builder()
                .name(movieReqDto.getName())
                .directorName(movieReqDto.getDirectorName())
                .duration(movieReqDto.getDuration())
                .genre(movieReqDto.getGenre())
                .rating(movieReqDto.getRating())
                .build();
    }
}
