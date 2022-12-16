package com.patryk.Task1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private int id;
    private int min;
    private int max;
    private String characters;
    private int quantity;
}
