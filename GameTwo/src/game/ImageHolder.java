package game;

import javafx.scene.image.Image;

import java.util.ArrayList;


public class ImageHolder {

    private Image img1 = new Image(GameApp.class.getResourceAsStream("image.jpg"));
    private Image img2 = new Image(GameApp.class.getResourceAsStream("image2.jpg"));
    private ArrayList<Image> images = new ArrayList<>();

    public ImageHolder(){
        images.add(img1);
        images.add(img2);
    }

    public Image getImg1() {
        return img1;
    }

    public Image getImg2() {
        return img2;
    }

    public ArrayList<Image> getImages(){
        return images;
    }
}
