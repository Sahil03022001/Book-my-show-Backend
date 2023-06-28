package com.example.BookmyshowApplication.Controllers;

import com.example.BookmyshowApplication.DTOs.Request.ShowReqDto;
import com.example.BookmyshowApplication.DTOs.Request.ShowSeatsReqDto;
import com.example.BookmyshowApplication.Service.ShowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/add")
    public String addShow(@RequestBody ShowReqDto showReqDto) {
        try {
            return showService.addShow(showReqDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/associate-seats")
    public String associateShowSeats(@RequestBody ShowSeatsReqDto showSeatsReqDto) {
        try {
            return showService.associateShowSeats(showSeatsReqDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}