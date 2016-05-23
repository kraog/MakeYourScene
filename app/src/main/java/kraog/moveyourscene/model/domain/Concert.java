package kraog.moveyourscene.model.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gorka on 02/05/2016.
 */
public class Concert implements Serializable{

    private int id;
    private int id_stage;
    private String name;
    private String bio;
    private Date date_concert;
    private String imageUrl;
    private Float cost;

    public Concert(){}
    public Concert(int id, String name, Date fecha, String imageUrl){
        this.id = id;
        this.name = name;
        this.date_concert= fecha;
        this.imageUrl = imageUrl;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_stage() {
        return id_stage;
    }

    public void setId_stage(int id_stage) {
        this.id_stage = id_stage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDate_concert() {
        return date_concert;
    }

    public void setDate_concert(Date date_concert) {
        this.date_concert = date_concert;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
