package com.example.ticketgenerator.service;

import com.example.ticketgenerator.dto.Ticket;
import com.example.ticketgenerator.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    private TicketService sut;

    @BeforeEach
    public void init(){
        ticketRepository = mock(TicketRepository.class);
        sut = new TicketService(ticketRepository);
    }

    @Test
    public void shouldGenerateTicket(){
        when(ticketRepository.nextNumber()).thenReturn(1);
        when(ticketRepository.generateTicket(any())).thenReturn(new Ticket(LocalDateTime.now(), 1, 0));
        Ticket ticket = sut.generateTicket();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

    @Test
    public void shouldGetAllTickets(){
        when(ticketRepository.getAll()).thenReturn(List.of(new Ticket(LocalDateTime.now(), 1, 0),
                new Ticket(LocalDateTime.now(), 2, 1),
                new Ticket(LocalDateTime.now(), 3, 2)));

        List<Ticket> tickets = sut.getAll();
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
    public void shouldRemoveLast(){
        when(ticketRepository.RemoveLast()).thenReturn(new Ticket(LocalDateTime.now(), 1, 0));
        Ticket ticket = sut.RemoveLast();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

    @Test
    public void shouldGetCurrent(){
        when(ticketRepository.getCurrent()).thenReturn(new Ticket(LocalDateTime.now(), 1, 0));
        Ticket ticket = sut.getCurrent();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());
    }

}
