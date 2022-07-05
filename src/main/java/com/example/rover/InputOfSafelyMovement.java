package com.example.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputOfSafelyMovement {
    int currentX;
    int currentY;
    String currentDirection;
    int headingX;
    int headingY;
    ArrayList<Obstacle> obstacles ;

    public Direction toDirection(String currentDirection) {
        return Direction.valueOf(currentDirection.toUpperCase());
    }


}
