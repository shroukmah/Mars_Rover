
package com.example.rover;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class RoverService {
    private int[] X=new int[] { 1, 0, -1, 0 };

    private int[] Y = new int[] { 0, 1, 0, -1 };

    public State  excuteCommands(State currState, String commands, ArrayList<Obstacle>obstacles)
    {
        State currentState = new State(currState);
        State heading=new State(currentState);

        for (int i = 0; i < commands.length(); i++) {
            heading = excuteSingleCommand(commands.charAt(i),currentState);
            if(checkIfObstacle(heading.getLocation(),obstacles))
            {
                currentState.setStopped(true);
                return currentState;
            }
            currentState = new State(heading);
        }
        return heading;
    }

    public State excuteSingleCommand(char command,State currentState) {
        Location currentLocation = new Location(currentState.getLocation());
        int noCurrentDirection = currentState.getDirection().getNumVal();

        switch (command) {
            case 'F':
                currentLocation.setX(currentLocation.getX() + X[noCurrentDirection]);
                currentLocation.setY(currentLocation.getY() + Y[noCurrentDirection]);
                break;
            case 'B':
                currentLocation.setX(currentLocation.getX() - X[noCurrentDirection]);
                currentLocation.setY(currentLocation.getY() - Y[noCurrentDirection]);
                break;
            case 'L':
                noCurrentDirection = (noCurrentDirection + 1) % 4;
                break;
            case 'R':
                noCurrentDirection--;
                break;

        }
        if (noCurrentDirection < 0)
            noCurrentDirection = 3;

        return new State(currentLocation, Direction.values()[noCurrentDirection]);
    }
    public boolean checkIfObstacle(Location newLocation, ArrayList<Obstacle>obstacles) {
        for (Obstacle obs : obstacles) {
            Location obsLocation = obs.getLocation();
            if (obsLocation.isEqualTo(newLocation))
                return true;
        }
        return false;
    }

    public String safelyMove(State currState,Location heading,ArrayList<Obstacle> obs)
    {
        return move(currState,heading,obs,false);
    }
    public String move(State currState,Location heading,ArrayList<Obstacle> obs,boolean xChanged)
    {
        State currentState =new State(currState);
        State newState = new State(currentState);
        String commands="";
        String tmpCommand="";
        int diffX=heading.getX()-currentState.getLocation().getX();
        int diffY=heading.getY()-currentState.getLocation().getY();
        boolean xIsChanged=xChanged;

        //Base Case
        if(diffX==0&&diffY==0) return "";

        if(diffX!=0&&(!xIsChanged))
        {
            xIsChanged=false;
            //DIR --->East
            tmpCommand += directionToEast(currentState);
            newState.setDirection(Direction.EAST);

            tmpCommand+= (diffX>0) ? 'F' : 'B';
            newState = excuteSingleCommand(tmpCommand.charAt(tmpCommand.length()-1),newState);

            if (checkIfObstacle(newState.getLocation(),obs))
            {
                tmpCommand=directionToSouth(currentState);
                newState=new State(currentState.getLocation(),Direction.SOUTH) ;
                State newState2=excuteSingleCommand('B',newState);
                if (checkIfObstacle(newState2.getLocation(),obs))
                {
                    newState=excuteSingleCommand('F',newState);
                    if (checkIfObstacle(newState.getLocation(),obs)) {
                        newState = excuteSingleCommand('R', currentState);
                        newState = excuteSingleCommand((diffX > 0) ? 'B' : 'F', newState);
                        tmpCommand =(diffX > 0) ? "B" : "F";
                    }
                    else
                    {
                        tmpCommand+="F";
                    }
                }
                else
                {
                    newState=new State(newState2);
                    tmpCommand+="B";
                }
            }
            commands += tmpCommand;

        }
        else if(diffY!=0)
        {
            tmpCommand+= directionToSouth(currentState);
            newState.setDirection(Direction.SOUTH);


            tmpCommand+= (diffY>0) ? 'B' : 'F';
            newState = excuteSingleCommand(tmpCommand.charAt(tmpCommand.length()-1),newState);

            if (checkIfObstacle(newState.getLocation(),obs))
            {
                tmpCommand=directionToEast(currentState);
                newState=new State(currentState.getLocation(),Direction.EAST) ;
                State newState2=excuteSingleCommand('F',newState);

                if (checkIfObstacle(newState2.getLocation(),obs))
                {
                    newState=excuteSingleCommand('B',newState);
                    if (checkIfObstacle(newState.getLocation(),obs)) {
                        newState = excuteSingleCommand('R', currentState);
                        newState = excuteSingleCommand((diffY > 0) ? 'F' : 'B', newState);
                        tmpCommand =(diffY > 0) ? "F" : "B";
                    }
                    else
                    {
                        xIsChanged=true;
                        tmpCommand+="B";
                    }
                }
                else
                {
                    xIsChanged=true;
                    newState=new State(newState2);
                    tmpCommand+="F";
                }
            }
            commands += tmpCommand;

        }
        else xIsChanged=false;
        currentState = new State(newState);
        commands+=move(currentState, heading, obs,xIsChanged);

        return commands;

    }


    private String directionToSouth(State currentState) {
        String command = "";
        for(int i=currentState.getDirection().getNumVal();i<3;i++)
            command+="L";
        return command;
    }

    private String directionToEast(State currentState) {
        String command = "";
        for(int i=currentState.getDirection().getNumVal();i>0;i--)
            command+="R";
        return command;
    }
    }
