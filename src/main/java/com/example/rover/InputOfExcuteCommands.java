package com.example.rover;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputOfExcuteCommands {

    int currentX;
    int currentY;
    String currentDirection;
    String commands;

    ArrayList<Obstacle> obstacles ;

    public Direction toDirection(String currentDirection) {
        return Direction.valueOf(currentDirection.toUpperCase());
    }

}
