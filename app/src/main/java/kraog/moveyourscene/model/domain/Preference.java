package kraog.moveyourscene.model.domain;

/**
 * Created by Gorka on 03/05/2016.
 */
public class Preference {
    private int id_preference;
    private User user;
    private String url_photo;

    public int getId_preference() {
        return id_preference;
    }

    public void setId_preference(int id_preference) {
        this.id_preference = id_preference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }
}
