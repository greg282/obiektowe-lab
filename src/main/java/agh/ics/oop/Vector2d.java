package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    final public int x;
    final public int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    public boolean precedes(Vector2d other){
        return (this.x <= other.x) && (this.y <= other.y);
    }
    public boolean follows(Vector2d other){
        return (this.x >= other.x) && (this.y >= other.y);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x,this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x,this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other){ /*other to v2 wg rysunku na githubie*/
        return new Vector2d(this.x,other.y);
    }
    public Vector2d lowerLeft (Vector2d other){
        return new Vector2d(other.x, this.y);
    }
    public Vector2d opposite(){
        return new Vector2d(this.x * (-1),this.y * (-1) );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
