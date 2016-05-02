package io.github.epelde.crispychainsaw.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Gorka on 02/05/2016.
 */
public class User implements Serializable {

    private int id;
    private String name;
    private String nickname;
    private int id_sex;
    private String mail;
    private Date date_start;
    private Date date_end;
    private List<Band> list_band;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId_sex() {
        return id_sex;
    }

    public void setId_sex(int id_sex) {
        this.id_sex = id_sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public List<Band> getList_band() {
        return list_band;
    }

    public void setList_band(List<Band> list_band) {
        this.list_band = list_band;
    }
}
