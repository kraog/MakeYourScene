package io.github.epelde.crispychainsaw.model.domain;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by epelde on 20/04/2016.
 */
public class Band implements Serializable {

    private String id;

    private String name;

    private String imageUrl;

    public Band() {

    }

    public Band(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
