package com.example.ticketgenerator.service;

import com.example.ticketgenerator.dto.Ticket;
import com.example.ticketgenerator.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket() {
        Ticket ticket = new Ticket(LocalDateTime.now(), ticketRepository.nextNumber(), ticketRepository.getTicketCount());
        return ticketRepository.generateTicket(ticket);
    }

    public Ticket getCurrent() {
        return ticketRepository.getCurrent();
    }

    public Ticket RemoveLast() {
        return ticketRepository.RemoveLast();
    }

    public List<Ticket> getAll() {
        return ticketRepository.getAll();
    }

}
