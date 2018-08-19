package comc.example.mohammedmorse.baking.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by Mohammed Morse on 07/08/2018.
 */

public class SweetImagesandName {
    public SweetImagesandName(String Name, int Image , Drawable Color){
        this.Name=Name;
        this.Image=Image;
        this.Color=Color;
    }
    private Drawable Color;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    private String  Name;
    private int Image;

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    private double Rating;
    public Drawable getColor() {
        return Color;
    }

    public void setColor(Drawable color) {
        Color = color;
    }
}
