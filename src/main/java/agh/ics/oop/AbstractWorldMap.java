package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    private MapVisualizer visualizer;
    private int max_y=-1,max_x=-1;
    private int min_y,min_x;
    private int width;
    private int height;

    //protected Object[][] map;

    protected Map<Vector2d, Object> map;
    protected MapBoundary mapBoundary=new MapBoundary();

    public AbstractWorldMap(int width,int height){
        this.width=width;
        this.height=height;
        this.min_y=height;
        this.min_x=width;
        this.visualizer=new MapVisualizer(this);
        this.map=new HashMap<>();
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.y<height &&  position.x<width ){
            return map.get(position)==null;
        }

        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(!isOccupied(animal.getPosition())){
            map.put(animal.getPosition(),animal);
            mapBoundary.add_position(animal.getPosition());
           //map[animal.getPosition().y][animal.getPosition().x]=animal;
            return true;
        }
        else {
            throw new IllegalArgumentException(animal.getPosition() + "field is occupied");
        }

    }

    @Override
    public String toString(){
        /*
        map.forEach((key,value)->{
            if(value!=null){
                updateCorners(key.x, key.y);
            }
        });
        return visualizer.draw(new Vector2d(min_x,min_y),new Vector2d(max_x,max_y));

         */
        return visualizer.draw(mapBoundary.getlowerleft_corner(),mapBoundary.getuppperright_corner());
    }

    public MapBoundary getBorders(){
        return this.mapBoundary;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return map.get(position)!=null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map.get(position);
    }

    @Override
    public void clearPlace(Vector2d position) {
        map.remove(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        map.put(newPosition,map.remove(oldPosition));
        mapBoundary.clear_position(oldPosition);
        mapBoundary.add_position(newPosition);
    }


    private void updateCorners(int pos_x,int pos_y){
        if(pos_x<min_x){
            min_x=pos_x;
        }
        if(pos_x>max_x){
            max_x=pos_x;
        }

        if(pos_y<min_y){
            min_y=pos_y;
        }
        if(pos_y>max_y){
            max_y=pos_y;
        }

    }
}


