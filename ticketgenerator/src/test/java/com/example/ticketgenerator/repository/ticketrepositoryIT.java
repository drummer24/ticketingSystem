package com.example.ticketgenerator.repository;

import com.example.ticketgenerator.dto.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ticketrepositoryIT {
    @Autowired
    TicketRepository sut;

    @BeforeEach
    public void init(){
        sut.clear();
    }

    @Test
    public void shouldGenerateTicket(){
        Ticket ticket = new Ticket(LocalDateTime.now(), sut.getLastTicketNumber() + 1, sut.getTicketCount());
        sut.generateTicket(ticket);
        List<Ticket> tickets = sut.getAll();
        ticket = tickets.get(0);

        assertEquals(tickets.size(), 1);
        assertEquals(ticket.getPosition(), 0);
        assertEquals(ticket.getNumber(), 1);
        assertNotNull(ticket.getDateTime());
    }

    @Test
    public void shouldGetAllTickets(){
        Ticket ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        List<Ticket> tickets = sut.getAll();
        assertEquals(tickets.size(),3);

        ticket = tickets.get(0);

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
    public void shouldGetLastNumber(){
        Ticket ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);

        assertEquals(3, sut.getLastTicketNumber());
    }

    @Test
    public void shouldRemoveLast(){
        Ticket ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);

        sut.RemoveLast();

        int size = sut.getAll().size();

        assertEquals(2, size);
    }

    @Test
    public void shouldGetCurrent(){
        Ticket ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        ticket = new Ticket(LocalDateTime.now(), sut.nextNumber(), sut.getTicketCount());
        sut.generateTicket(ticket);
        List<Ticket> tickets = sut.getAll();
        assertEquals(tickets.size(),3);

        ticket = sut.getCurrent();

        assertEquals(0, ticket.getPosition());
        assertEquals(1, ticket.getNumber());
        assertNotNull(ticket.getDateTime());

    }
}
