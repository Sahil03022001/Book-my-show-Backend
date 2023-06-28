package com.example.BookmyshowApplication.Service;

import com.example.BookmyshowApplication.Convertors.ShowConvertor;
import com.example.BookmyshowApplication.DTOs.Request.ShowReqDto;
import com.example.BookmyshowApplication.DTOs.Request.ShowSeatsReqDto;
import com.example.BookmyshowApplication.Enums.SeatType;
import com.example.BookmyshowApplication.Exceptions.MovieNotFoundException;
import com.example.BookmyshowApplication.Exceptions.ShowNotFoundException;
import com.example.BookmyshowApplication.Exceptions.TheatreNotFoundException;
import com.example.BookmyshowApplication.Models.*;
import com.example.BookmyshowApplication.Repository.MovieRepository;
import com.example.BookmyshowApplication.Repository.ShowRepository;
import com.example.BookmyshowApplication.Repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;

    public ShowService(ShowRepository showRepository,
                       TheatreRepository theatreRepository,
                       MovieRepository movieRepository) {
        this.showRepository = showRepository;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
    }


    public String addShow(ShowReqDto showReqDto) {

        Optional<Theatre> optionalTheatre = theatreRepository.findById(showReqDto.getTheatreId());
        if(optionalTheatre.isEmpty()) {
            throw new TheatreNotFoundException("Theatre not found");
        }

        Optional<Movie> optionalMovie = movieRepository.findById(showReqDto.getMovieId());
        if(optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie not found");
        }

        Theatre theatre = optionalTheatre.get();
        Movie movie = optionalMovie.get();

        Show show = ShowConvertor.showReqDtoToShow(showReqDto);

        show.setTheatre(theatre);
        show.setMovie(movie);

        theatre.getShowList().add(show);
        movie.getShowList().add(show);

        showRepository.save(show);
        theatreRepository.save(theatre);
        movieRepository.save(movie);

        return "Show registered in Database with ID " + show.getId();
    }

    public String associateShowSeats(ShowSeatsReqDto showSeatsReqDto) {
        Optional<Show> showOptional = showRepository.findById(showSeatsReqDto.getShowId());
        if(showOptional.isEmpty()) {
            throw new ShowNotFoundException("Show not Found");
        }

        int priceOfClassicSeat = showSeatsReqDto.getPriceOfClassicSeat();
        int priceOfPremiumSeat = showSeatsReqDto.getPriceOfPremiumSeat();

        Show show = showOptional.get();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        List<TheatreSeat> theatreSeatList = show.getTheatre().getTheatreSeatList();
        for(TheatreSeat theatreSeat : theatreSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theatreSeat.getSeatNo());
            showSeat.setSeatType(theatreSeat.getSeatType());
            showSeat.setAvailable(true);

            if(theatreSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setPrice(priceOfClassicSeat);
            } else {
                showSeat.setPrice(priceOfPremiumSeat);
            }

            showSeat.setShow(show);
            showSeatList.add(showSeat);
        }

        showRepository.save(show);
        return "Seats Made available";
    }
}
