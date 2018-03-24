package game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameObject {

    private Image img;
    private ImageView view;
    private Point2D velocity = new Point2D(0,0);
    private boolean alive = true;
    private boolean isOnGround = true;
    private double y;

    public GameObject(ImageView view){
        this.view = view;
    }

    public GameObject(){

    }

    public void update(){
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public Point2D getVelocity(){
        return velocity;
    }

    public void setVelocity(Point2D velocity){
        this.velocity = velocity;
    }

    public ImageView getView(){
        return view;
    }

    public double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    public void moveRight(){
        view.setTranslateX(view.getTranslateX());
        view.setScaleX(1);
        setVelocity(new Point2D(5,0));
    }

    public void moveLeft(){
        view.setTranslateX(view.getTranslateX());
        view.setScaleX(-1);
        setVelocity(new Point2D(-5,0));
    }

    public void moveStop(){
        view.setTranslateX(view.getTranslateX());
        setVelocity(new Point2D(0,0));
    }

    public boolean isOnGround(){
        if (view.getTranslateY() == 300){
            return true;
        }else{
            return false;
        }
    }

    public boolean getIsOnGround(){
        return isOnGround;
    }

    public void setIsOnGround(boolean isOnGround){
        this.isOnGround = isOnGround;
    }

    public boolean isColliding(GameObject other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
}
