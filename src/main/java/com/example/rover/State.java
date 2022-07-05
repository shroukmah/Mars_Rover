package com.example.rover;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@Data
@NoArgsConstructor
public class State {

    private Location location;
    private Direction direction ;

    private boolean isStopped;

    public State(Location location, Direction direction) {
        this.location=location;
        this.direction = direction;
        this.isStopped = false;
    }
    public State(Location location, Direction direction,boolean isStopped) {
        this.location=location;
        this.direction = direction;
        this.isStopped = isStopped;

    }

    public State(State l) {

        this(l.location,l.direction,l.isStopped);
    }
    public boolean isEqualTo(State state)
    {
        return (state.location.isEqualTo(this.location)
                &&state.direction==this.direction
                && state.isStopped==this.isStopped);
    }

    public Location getLocation() {
        return location;
    }


}
