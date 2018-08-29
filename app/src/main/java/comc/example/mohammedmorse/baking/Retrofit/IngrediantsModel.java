package comc.example.mohammedmorse.baking.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohammed Morse on 12/08/2018.
 */

public class IngrediantsModel implements Serializable {
    @SerializedName("quantity")
    double Quantity;

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getIngrediant() {
        return Ingrediant;
    }

    public void setIngrediant(String ingrediant) {
        Ingrediant = ingrediant;
    }

    @SerializedName("measure")
    String  Measure;
    @SerializedName("ingredient")
    String Ingrediant;
}
