package com.example.ticketgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Ticket {
    private LocalDateTime dateTime;
    private int number;
    private int position;
}
