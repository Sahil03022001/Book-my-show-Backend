package com.example.BookmyshowApplication.Controllers;

import com.example.BookmyshowApplication.DTOs.Request.TheatreReqDto;
import com.example.BookmyshowApplication.DTOs.Request.TheatreSeatsReqDto;
import com.example.BookmyshowApplication.Service.TheatreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping("/add")
    public String createTheatre(@RequestBody TheatreReqDto theatreReqDto) {
        return theatreService.createTheatre(theatreReqDto);
    }

    @PostMapping("/addTheatreSeats")
    public String addTheatreSeats(@RequestBody TheatreSeatsReqDto theatreSeatsReqDto) {
        return theatreService.addTheatreSeats(theatreSeatsReqDto);
    }
}
