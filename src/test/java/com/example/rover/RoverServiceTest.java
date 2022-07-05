package com.example.rover;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RoverServiceTest {

    RoverService roverService;
    @Test
    void followTheCommands_getNewRoverState() {
        roverService=new RoverService();

        State currState = new State(new Location(4,2),Direction.EAST);
        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(new Location(1, 4)));
        obstacles.add(new Obstacle(new Location(3, 5)));
        obstacles.add(new Obstacle(new Location(7,4)));

        ////////////////////CASE1//////////////////////
        String commands1 ="FLFFFRFLB";
        State resultState1 = new State(new Location(6,4),Direction.NORTH,false);

       assertEquals(roverService.excuteCommands(currState,commands1,obstacles).isEqualTo(resultState1)
        ,true);

        /////////////////////CASE2/////////////////////
        State resultState2 = new State(new Location(7,3),Direction.NORTH,true);
        String commands2 ="FFFLFFF";

        assertEquals(roverService.excuteCommands(currState,commands2,obstacles).isEqualTo(resultState2)
                ,true);
    }

    @Test
    void followSingleCommand_getNewRoverState() {
        roverService=new RoverService();
        State currentState = new State(new Location(4,2),Direction.EAST);
        //////////////Case1//////////////////////
        State resultState1 = new State(new Location(5,2),Direction.EAST);
        assertEquals(roverService.excuteSingleCommand('F',currentState).isEqualTo(resultState1)
        ,true);
        //////////////Case2//////////////////////
        State resultState2 = new State(new Location(3,2),Direction.EAST);
        assertEquals(roverService.excuteSingleCommand('B',currentState).isEqualTo(resultState2)
                ,true);
        //////////////Case3//////////////////////
        State resultState3 = new State(new Location(4,2),Direction.NORTH);
        assertEquals(roverService.excuteSingleCommand('L',currentState).isEqualTo(resultState3)
                ,true);
        //////////////Case4//////////////////////
        State resultState4 = new State(new Location(4,2),Direction.SOUTH);
        assertEquals(roverService.excuteSingleCommand('R',currentState).isEqualTo(resultState4)
                ,true);


    }

    @Test
    void takeLocationAndCheckIFObstacle() {
        roverService=new RoverService();
        ArrayList<Obstacle> obstacles1 = new ArrayList<Obstacle>();
		obstacles1.add(new Obstacle(new Location(6, 5)));
        obstacles1.add(new Obstacle(new Location(1, 1)));
        //CASE1//
        boolean b1= roverService.checkIfObstacle(new Location(1,1),obstacles1);
        assertEquals(b1,true);
        //CASE1=2//
        boolean b2= roverService.checkIfObstacle(new Location(3,5),obstacles1);
        assertEquals(b2,false);

    }

    @Test
    void fromCurrentStateAndTheHeading_getTheCommandString() {
        roverService=new RoverService();

        State currState = new State(new Location(4,2),Direction.EAST);
        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(new Location(8, 4)));
        obstacles.add(new Obstacle(new Location(9, 2)));
        obstacles.add(new Obstacle(new Location(2,6)));

        /////////////////////////////CASE1///////////////////////////////
        ////////////////////////WithoutObstcales////////////////////////
        State heading1 = new State(new Location(6,4),Direction.SOUTH,false);
        String commands1 =roverService.safelyMove(currState,heading1.getLocation(),obstacles) ;
        State result1=roverService.excuteCommands(currState,commands1,new ArrayList<Obstacle>());

        assertEquals(result1.getLocation().isEqualTo(heading1.getLocation()),true);

        ///////////////////////////CASE2///////////////////////////////
        ///////////////////////Obstcale On X////////////////////////
        State heading2 = new State(new Location(8,6),Direction.SOUTH,false);
        String commands2 =roverService.safelyMove(currState,heading2.getLocation(),obstacles) ;
        State result2=roverService.excuteCommands(currState,commands2,new ArrayList<Obstacle>());

        assertEquals(result2.getLocation().isEqualTo(heading2.getLocation()),true);

        ///////////////////////////CASE3///////////////////////////////
        ///////////////////////Obstcale On Y////////////////////////
        State heading3 = new State(new Location(2,8),Direction.SOUTH,false);
        String commands3 =roverService.safelyMove(currState,heading3.getLocation(),obstacles) ;
        State result3=roverService.excuteCommands(currState,commands3,new ArrayList<Obstacle>());

        assertEquals(result3.getLocation().isEqualTo(heading3.getLocation()),true);
        ///////////////////////////CASE4///////////////////////////////
        ///////////////////////Obstcale On X&Y////////////////////////
        State heading4 = new State(new Location(9,7),Direction.SOUTH,false);
        String commands4 =roverService.safelyMove(currState,heading4.getLocation(),obstacles) ;
        State result4=roverService.excuteCommands(currState,commands4,new ArrayList<Obstacle>());

        assertEquals(result4.getLocation().isEqualTo(heading4.getLocation()),true);
         }


}