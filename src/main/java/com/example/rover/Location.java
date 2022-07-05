package com.example.rover;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Location(Location location)
    {
        this(location.x, location.y);
    }
    public boolean isEqualTo(Location location)
    {
        return (this.x==location.x  && this.y == location.y);
    }


}
