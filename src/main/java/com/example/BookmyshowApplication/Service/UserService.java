package com.example.BookmyshowApplication.Service;

import com.example.BookmyshowApplication.Convertors.TicketConvertor;
import com.example.BookmyshowApplication.Convertors.UserConvertor;
import com.example.BookmyshowApplication.DTOs.Request.BookTicketReqDto;
import com.example.BookmyshowApplication.DTOs.Request.UserReqDto;
import com.example.BookmyshowApplication.DTOs.Response.TicketBookedResponse;
import com.example.BookmyshowApplication.Exceptions.SeatsNotAvailableException;
import com.example.BookmyshowApplication.Exceptions.ShowNotFoundException;
import com.example.BookmyshowApplication.Exceptions.UserNotFoundException;
import com.example.BookmyshowApplication.Models.Show;
import com.example.BookmyshowApplication.Models.ShowSeat;
import com.example.BookmyshowApplication.Models.Ticket;
import com.example.BookmyshowApplication.Models.User;
import com.example.BookmyshowApplication.Repository.ShowRepository;
import com.example.BookmyshowApplication.Repository.TicketRepository;
import com.example.BookmyshowApplication.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.tools.DiagnosticListener;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public UserService(UserRepository userRepository,
                       ShowRepository showRepository,
                       TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
    }

    public String createUser(UserReqDto userReqDto) {
        User user = UserConvertor.userReqDtoToUser(userReqDto);
        userRepository.save(user);
        return "User Added";
    }

    public TicketBookedResponse bookTicket(BookTicketReqDto bookTicketReqDto) {

        //check if user exists
        Optional<User> userOptional = userRepository.findById(bookTicketReqDto.getUserId());
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        //check if show exists
        Optional<Show> showOptional = showRepository.findById(bookTicketReqDto.getShowId());
        if(showOptional.isEmpty()) {
            throw new ShowNotFoundException("Show not found");
        }

        User user = userOptional.get();
        Show show = showOptional.get();
        List<String> reqSeats = bookTicketReqDto.getRequestedSeats();

        //check if seats are available or not
        int totalBill = checkSeatsAvailabilityAndGetTotalBill(reqSeats, show);
        if(totalBill == -1) {
            throw new SeatsNotAvailableException("Seats not available");
        }

        //make the ticket instance
        Ticket ticket = TicketConvertor.makeTicket(show, user, totalBill);

        //make seats unavailable and add all show seats to ticket show seat list
        makeSeatsUnAvailableAndAddInTicketShowList(reqSeats, show, ticket.getShowSeatList());

        ticket.setUser(user);
        user.getTicketList().add(ticket);

        ticketRepository.save(ticket);
        userRepository.save(user);
        showRepository.save(show);

        //make and return the Ticket Response
        TicketBookedResponse ticketBookedResponse = TicketConvertor.ticketToTicketBookedResponse(ticket);
        String ticketsBooked = getBookedTickets(reqSeats);
        ticketBookedResponse.setBookedSeats(ticketsBooked);
        return ticketBookedResponse;
    }

    private String getBookedTickets(List<String> seats) {
        return String.join(", ", seats);
    }

    private void makeSeatsUnAvailableAndAddInTicketShowList(List<String> requestedSeats, Show show, List<ShowSeat> ticketShowSeatList) {
        Set<String> allReqSeatsSet = new HashSet<>(requestedSeats);
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList) {
            if(allReqSeatsSet.contains(showSeat.getSeatNo())) {
                ticketShowSeatList.add(showSeat);
                showSeat.setAvailable(false);
            }
        }
    }

    private int checkSeatsAvailabilityAndGetTotalBill(List<String> requestedSeats, Show show) {
        Set<String> allReqSeatsSet = new HashSet<>(requestedSeats);
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int totalBill = 0;
        for(ShowSeat showSeat : showSeatList) {
            if(allReqSeatsSet.contains(showSeat.getSeatNo()) && !showSeat.isAvailable()) {
                return -1;
            }
            totalBill += showSeat.getPrice();
        }
        return totalBill;
    }

    public List<TicketBookedResponse> getAllTickets(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        List<Ticket> tickets = user.getTicketList();
        List<TicketBookedResponse> ticketBookedResponseList = new ArrayList<>();
        for(Ticket ticket : tickets) {
            TicketBookedResponse ticketBookedResponse = TicketConvertor.ticketToTicketBookedResponse(ticket);
            StringBuilder bookedSeats = new StringBuilder();
            int count = ticket.getShowSeatList().size();
            for(ShowSeat showSeat : ticket.getShowSeatList()) {
                bookedSeats.append(showSeat.getSeatNo());
                if(count != 1)
                    bookedSeats.append(", ");
                count--;
            }
            ticketBookedResponse.setBookedSeats(bookedSeats.toString());
            ticketBookedResponseList.add(ticketBookedResponse);
        }
        return ticketBookedResponseList;
    }
}