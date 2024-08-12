package com.example.ticketgenerator.repository;

import com.example.ticketgenerator.dto.Ticket;

import java.util.List;

public interface TicketRepository {

    public Ticket generateTicket(Ticket ticket);

    public Ticket getCurrent();

    public Ticket RemoveLast();

    public List<Ticket> getAll();

    int getLastTicketNumber();

    int nextNumber();

    int getTicketCount();

    void clear();
}
