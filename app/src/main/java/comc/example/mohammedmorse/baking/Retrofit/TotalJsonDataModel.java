package comc.example.mohammedmorse.baking.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohammed Morse on 12/08/2018.
 */
/*"id": 1,
    "name": "Nutella Pie",
    "ingredients": [

    ],
    "steps": [

    "servings": 8,
    "image": ""*/
public class TotalJsonDataModel {
   @SerializedName("id")
    int Id;
    @SerializedName("name")
    String Name;
    @SerializedName("ingredients")
    ArrayList<IngrediantsModel> IngrediantList;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<IngrediantsModel> getIngrediantList() {
        return IngrediantList;
    }

    public void setIngrediantList(ArrayList<IngrediantsModel> ingrediantList) {
        IngrediantList = ingrediantList;
    }

    public ArrayList<StepsModel> getStepsList() {
        return StepsList;
    }

    public void setStepsList(ArrayList<StepsModel> stepsList) {
        StepsList = stepsList;
    }

    public int getServings() {
        return Servings;
    }

    public void setServings(int servings) {
        Servings = servings;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @SerializedName("steps")
    ArrayList<StepsModel>StepsList;
    @SerializedName("servings")
    int Servings;
    @SerializedName("image")
    String Image;
}
