package com.example.ticketgenerator.controller;

import com.example.ticketgenerator.dto.Ticket;
import com.example.ticketgenerator.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {


    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Ticket> createTicket() {
        return new ResponseEntity<Ticket>(ticketService.generateTicket(), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAll(){
        return new ResponseEntity<List<Ticket>>(ticketService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<Ticket> getCurrent(){
        return new ResponseEntity<Ticket>(ticketService.getCurrent(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity <Ticket> removeLast(){
        return new ResponseEntity<Ticket>(ticketService.RemoveLast(), HttpStatus.NO_CONTENT);
    }
}
