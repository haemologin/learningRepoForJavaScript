package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameApp extends Application{

    private Pane root;
    private GameObject player;
    private Image img = new Image(GameApp.class.getResourceAsStream("image.jpg"));
    private ImageView iw = new ImageView(img);
    private boolean isJumping;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private boolean isOnTop = false;

    public Parent createContent(){
        root = new Pane();
        root.setPrefSize(800,600);


        player = new GameObject(iw);
        addGameObject(player,300,300);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void createObject(int i, double x, double y){

        ImageHolder ih = new ImageHolder();
        ImageView iwTest = new ImageView();
        iwTest.setImage(ih.getImages().get(i));
        GameObject test = new GameObject(iwTest);
        test.getView().setTranslateX(x);
        test.getView().setTranslateY(y);
        root.getChildren().add(test.getView());
    }

    private void setIsJumping(boolean jumping){
        isJumping = jumping;
    }

    private void setIsMovingRight(boolean Right){
        isMovingRight = Right;
    }

    private void setIsMovingLeft(boolean Left){
        isMovingLeft = Left;
    }

    private void addGameObject(GameObject object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void RightDirectionChecker(double y){

        if (isMovingRight){
            player.setVelocity(new Point2D(1, y));
        } else{
            player.setVelocity(new Point2D(0, y));
        }
    }

    private void LeftDirectionChecker(double y) {
        if (isMovingLeft) {
            player.setVelocity(new Point2D(-1, y));
        } else {
            player.setVelocity(new Point2D(0, y));
        }
    }

    private void jumpingUpdate(){
        if (player.getIsOnGround() && isJumping) {
            player.setY(player.getView().getTranslateY());
            player.setVelocity(new Point2D(0, -5));
            if(isMovingRight){
                RightDirectionChecker(-5);
            }

            if (isMovingLeft) {
                LeftDirectionChecker(-5);
            }
            player.setIsOnGround(false);

        } else if (!isOnTop && !player.getIsOnGround() && player.getView().getTranslateY() >= player.getY() - 100) {
            player.setVelocity(new Point2D(0, -3));
            if(isMovingRight){
                RightDirectionChecker(-3);
            }
            if (isMovingLeft) {
                LeftDirectionChecker(-3);
            }

        } else if (!isOnTop && !player.getIsOnGround() && player.getView().getTranslateY() >= player.getY() - 150) {
            player.setVelocity(new Point2D(0, -1));
            if(isMovingRight){
                RightDirectionChecker(-1);
            }
            if (isMovingLeft) {
                LeftDirectionChecker(-1);
            }

        } else if (!player.getIsOnGround() && player.getView().getTranslateY() >= player.getY() - 200 && player.getView().getTranslateY() <= player.getY() - 1) {
            System.out.println(player.getView().getTranslateY());
            isOnTop = true;
            player.setVelocity(new Point2D(0, 1));
            if(isMovingRight){
                RightDirectionChecker(1);
            }
            if (isMovingLeft) {
                LeftDirectionChecker(1);
            }

        } else if (!player.getIsOnGround() && player.getView().getTranslateY() == 300){
            System.out.println("földön");
            isOnTop = false;
            System.out.println(isOnTop);
            isJumping = false;
            System.out.println(isJumping);
            player.setIsOnGround(true);
            System.out.println(player.getIsOnGround());
            player.setVelocity(new Point2D(0, 0));
            System.out.println(player.getY());
        }
    }

    private void onUpdate(){
        jumpingUpdate();
        player.update();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                    player.moveRight();
                    setIsMovingLeft(false);
                    setIsMovingRight(true);
                } else if (event.getCode() == KeyCode.LEFT){
                    player.moveLeft();
                    setIsMovingLeft(true);
                    setIsMovingRight(false);
                } else if (event.getCode() == KeyCode.H){
                    createObject(1, 350,300);
                } else if (event.getCode() == KeyCode.SPACE){
                    setIsJumping(true);
                    player.setIsOnGround(true);

                }
            }
        });
        primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT){
                    player.moveStop();
                    setIsMovingRight(false);
                } else if (event.getCode() == KeyCode.LEFT){
                    player.moveStop();
                    setIsMovingLeft(false);
                }
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {

        ImageHolder object = new ImageHolder();
        for (int i = 0;i<object.getImages().size();i++){
            System.out.println(i);
            if (object.getImages().size() == 2){
                System.out.println("a lista üres");
            }
        }

        launch(args);

    }
}
