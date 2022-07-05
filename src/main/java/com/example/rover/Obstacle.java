package com.example.rover;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Obstacle {
    private Location location;

    public Obstacle(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}
