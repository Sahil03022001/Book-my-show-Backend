package com.example.BookmyshowApplication.Schedulers;

import com.example.BookmyshowApplication.Enums.ShowStatus;
import com.example.BookmyshowApplication.Models.Show;
import com.example.BookmyshowApplication.Repository.ShowRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class MyTaskScheduler {

    private final ShowRepository showRepository;

    public MyTaskScheduler(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateShowStatus() {

        List<Show> showList = showRepository.findAll();
        LocalDate currDate = LocalDate.now();
        LocalTime currTime = LocalTime.now();

        for(Show show : showList) {
            LocalDate showDate = show.getShowDate();
            LocalTime showStartTime = show.getShowStartTime();
            LocalTime showEndTime = show.getShowEndTime();

            if(currDate.isBefore(showDate)) {
                show.setShowStatus(ShowStatus.INACTIVE);
            } else if(currDate.isAfter(showDate)) {
                show.setShowStatus(ShowStatus.FINISHED);
            } else {
                if(currTime.isBefore(showStartTime)) {
                    show.setShowStatus(ShowStatus.INACTIVE);
                } else if(currTime.isAfter(showEndTime)) {
                    show.setShowStatus(ShowStatus.FINISHED);
                } else {
                    show.setShowStatus(ShowStatus.ACTIVE);
                }
            }
        }
    }
}