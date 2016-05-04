package kraog.moveyourscene.model.domain;

/**
 * Created by Gorka on 27/04/2016.
 */
public class MenuDrawerItem {



    public enum Activity_Related {BAND_LIST,DISC_LIST,CONCERT_LIST};
    private String title;
    private int imageResource;
    private Activity_Related activity_related;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public Activity_Related getActivity_related() {
        return activity_related;
    }

    public void setActivity_related(Activity_Related activity_related) {
        this.activity_related = activity_related;
    }
}
