package kraog.moveyourscene.model.domain;

import java.io.Serializable;

/**
 * Created by Gorka on 10/05/2016.
 */
public class Track implements Serializable{
    private int id;
    private int id_disc;
    private int order;
    private String name;
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_disc() {
        return id_disc;
    }

    public void setId_disc(int id_disc) {
        this.id_disc = id_disc;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
