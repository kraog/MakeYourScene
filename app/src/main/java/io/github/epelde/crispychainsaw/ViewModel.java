package io.github.epelde.crispychainsaw;

/**
 * Created by Gorka on 19/04/2016.
 */
public class ViewModel {
    private String text;
    private String image;

    public ViewModel(String text, String image) {
        this. text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }
}