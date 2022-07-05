package com.example.rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
class RoverController {
    @Autowired
    private RoverService roverService;

    @PostMapping("/excuteCommands")
    public State excuteCommands(@RequestBody InputOfExcuteCommands input)
    {
        State currentState = new State(new Location(input.currentX,input.currentY)
                                       ,input.toDirection(input.currentDirection));
        String commands=input.commands ;
        ArrayList<Obstacle> obstacles = input.obstacles;

        return roverService.excuteCommands(currentState,commands,obstacles);
    }
    @PostMapping("/safelyMovement")
    public String safelyMovement(@RequestBody InputOfSafelyMovement input)
    {
        State currentState =new State(new Location(input.currentX,input.currentY)
                                    ,input.toDirection(input.currentDirection));
        Location heading =new Location(input.headingX,input.headingY);
        ArrayList<Obstacle> obstacles = input.obstacles;

        return roverService.safelyMove(currentState,heading,obstacles);
    }
}
