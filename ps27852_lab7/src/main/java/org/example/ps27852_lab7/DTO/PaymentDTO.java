package org.example.ps27852_lab7.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
    private String status;
    private String message;
    private String url;
}