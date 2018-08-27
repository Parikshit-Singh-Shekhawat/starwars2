package app.starwars.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by apple on 25/08/18.
 */

public class CharacterListModel implements Serializable{

    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    @SerializedName("mass")
    private String mass;
    @SerializedName("created")
    private String created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
