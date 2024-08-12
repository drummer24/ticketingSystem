package com.example.ticketgenerator.repository.impl;

import com.example.ticketgenerator.dto.Ticket;
import com.example.ticketgenerator.repository.TicketRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class TicketRepositoryList implements TicketRepository {
    private static List<Ticket> tickets = new LinkedList<>();

    private static int number = 0;

    @Override
    public Ticket generateTicket(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public Ticket getCurrent() {
        return tickets.isEmpty() ? null : tickets.get(0);
    }

    @Override
    public Ticket RemoveLast() {
        if (tickets.isEmpty()) {
            return null;
        }
        Ticket removedTicket = tickets.remove(0);
        tickets.stream()
                .forEach(ticket -> ticket.setPosition(ticket.getPosition() - 1));
        return removedTicket;
    }

    @Override
    public List<Ticket> getAll() {
        return tickets;
    }

    @Override
    public int getLastTicketNumber() {
        return number;
    }

    @Override
    public int nextNumber() {
        return number +=1;
    }

    @Override
    public int getTicketCount() {
        return tickets.size();
    }

    @Override
    public void clear() {
        tickets.clear();
        number = 0;
    }
}
