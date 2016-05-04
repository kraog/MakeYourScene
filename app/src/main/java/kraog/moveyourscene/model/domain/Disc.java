package kraog.moveyourscene.model.domain;

import java.util.Date;

/**
 * Created by Gorka on 02/05/2016.
 */
public class Disc {

    private int id;
    private int id_band;
    private int id_studio;
    private String name;
    private Date date_creation;
    private Date date_start;
    private Date date_end;
    private String imageUrl;

    public Disc(int id_band, String name , String imageUrl){
        this.id_band=id_band;
        this.name=name;
        this.imageUrl=imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_band() {
        return id_band;
    }

    public void setId_band(int id_band) {
        this.id_band = id_band;
    }

    public int getId_studio() {
        return id_studio;
    }

    public void setId_studio(int id_studio) {
        this.id_studio = id_studio;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
