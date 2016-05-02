package io.github.epelde.crispychainsaw.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by epelde on 20/04/2016.
 */
public class Band implements Serializable {

    private int id;
    private int id_user;
    private String name;
    private String bio;
    private int id_local;
    private int id_country;
    private Date date_creation;
    private int id_genre;
    private int id_label;
    private String imageUrl;
    private Date date_start;
    private Date date_end;
    private List<Concert> list_concert;
    private List<Disc> list_disc;

    public Band() {

    }

    public Band(int id, String name, String imageUrl) {
        this.setId(id);
        this.setName(name);
        this.setImageUrl(imageUrl);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_country() {
        return id_country;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public int getId_label() {
        return id_label;
    }

    public void setId_label(int id_label) {
        this.id_label = id_label;
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

    public List<Concert> getList_concert() {
        return list_concert;
    }

    public void setList_concert(List<Concert> list_concert) {
        this.list_concert = list_concert;
    }

    public List<Disc> getList_disc() {
        return list_disc;
    }

    public void setList_disc(List<Disc> list_disc) {
        this.list_disc = list_disc;
    }
}
