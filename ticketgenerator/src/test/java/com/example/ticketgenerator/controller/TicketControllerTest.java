package com.example.ticketgenerator.controller;

import com.example.ticketgenerator.dto.Ticket;
import com.example.ticketgenerator.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    private TicketController sut;

    @BeforeEach
    public void init(){
        ticketService = mock(TicketService.class);
        sut = new TicketController(ticketService);
    }
    @Test
    public void shouldGenerateTicket() {
        when(ticketService.generateTicket()).thenReturn(new Ticket(LocalDateTime.now(), 1, 0));
        Ticket ticket = sut.createTicket().getBody();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

    @Test
    public void shouldGetAllTickets() {
        when(ticketService.getAll()).thenReturn(List.of(new Ticket(LocalDateTime.now(), 1, 0),
                new Ticket(LocalDateTime.now(), 2, 1),
                new Ticket(LocalDateTime.now(), 3, 2)));

        List<Ticket> tickets = sut.getAll().getBody();
        assertEquals(3, tickets.size());

        Ticket ticket = tickets.get(0);
        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());

        ticket = tickets.get(1);
        assertEquals(1, ticket.getPosition());
        assertEquals(2, ticket.getNumber());
        assertNotNull(ticket.getDateTime());

        ticket = tickets.get(2);
        assertEquals(2, ticket.getPosition());
        assertEquals(3, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

    @Test
    public void shouldRemoveLast() {
        when(ticketService.RemoveLast()).thenReturn(new Ticket(LocalDateTime.now(), 1, 0));
        Ticket ticket = sut.removeLast().getBody();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

    @Test
    public void shouldGetCurrent() {
        when(ticketService.getCurrent()).thenReturn(new Ticket(LocalDateTime.now(), 1, 0));
        Ticket ticket = sut.getCurrent().getBody();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

}
