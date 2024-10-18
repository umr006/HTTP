package service;

import dao.TicketDao;
import dto.FlightDto;
import dto.TicketDto;
import entity.Flight;
import entity.Ticket;

import java.util.List;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService(){}

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlight_id(),
                        ticket.getSeat_no()
                )).toList();
    }
}
