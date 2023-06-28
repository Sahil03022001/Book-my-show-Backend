package com.example.BookmyshowApplication.Service;

import com.example.BookmyshowApplication.Convertors.TheatreConvertor;
import com.example.BookmyshowApplication.DTOs.Request.TheatreReqDto;
import com.example.BookmyshowApplication.DTOs.Request.TheatreSeatsReqDto;
import com.example.BookmyshowApplication.Enums.SeatType;
import com.example.BookmyshowApplication.Exceptions.TheatreNotFoundException;
import com.example.BookmyshowApplication.Models.Theatre;
import com.example.BookmyshowApplication.Models.TheatreSeat;
import com.example.BookmyshowApplication.Repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }


    public String createTheatre(TheatreReqDto theatreReqDto) {
        Theatre theatre = TheatreConvertor.theatreReqDtoToTheatre(theatreReqDto);
        theatreRepository.save(theatre);
        return "Theatre registered in DB";
    }

    public String addTheatreSeats(TheatreSeatsReqDto theatreSeatsReqDto) {
        int theatreId = theatreSeatsReqDto.getTheatreId();
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if(theatreOptional.isEmpty()) {
            throw new TheatreNotFoundException("Theatre not found");
        }

        Theatre theatre = theatreOptional.get();
        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();

        int noOfSeatsInEachRow = theatreSeatsReqDto.getNoOfSeatsInEachRow();
        int noOfClassicSeats = theatreSeatsReqDto.getNoOfClassicSeats();
        int noOfPremiumSeats = theatreSeatsReqDto.getNoOfPremiumSeats();

        int globalCounter = 1;
        char currSeatCharacter = 'A';

        for(int count = 1; count <= noOfClassicSeats; count++) {
            String seatNo = globalCounter + "" + currSeatCharacter;
            currSeatCharacter++;

            if((currSeatCharacter - 'A') == noOfSeatsInEachRow) {
                currSeatCharacter = 'A';
                globalCounter++;
            }

            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatNo(seatNo);
            theatreSeat.setSeatType(SeatType.CLASSIC);
            theatreSeat.setTheatre(theatre);
            theatreSeatList.add(theatreSeat);
        }

        for(int count = 1; count <= noOfPremiumSeats; count++) {
            String seatNo = globalCounter + "" + currSeatCharacter;
            currSeatCharacter++;

            if((currSeatCharacter - 'A') == noOfSeatsInEachRow) {
                currSeatCharacter = 'A';
                globalCounter++;
            }

            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatNo(seatNo);
            theatreSeat.setSeatType(SeatType.PREMIUM);
            theatreSeat.setTheatre(theatre);
            theatreSeatList.add(theatreSeat);
        }

        theatreRepository.save(theatre);
        return "Seats registered in given theatre";
    }
}