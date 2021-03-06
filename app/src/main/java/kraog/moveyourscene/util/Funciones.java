package kraog.moveyourscene.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.Preference;
import kraog.moveyourscene.model.domain.User;

/**
 * Created by Gorka on 04/05/2016.
 */
public class Funciones {

    public static void setStupidData(ObservableField<User> user,List<MenuDrawerItem> menuItemList){
        user.set(new User());
        user.get().setMail("email_cojonudo@gmail.com");
        user.get().setName("ou yeahh");
        Preference pref = new Preference();
        pref.setUrl_photo("https://upload.wikimedia.org/wikipedia/commons/0/03/Jacinto_Benavente.jpg");
        user.get().setPreferences(pref);
        MenuDrawerItem mdi = new MenuDrawerItem();
        mdi.setTitle("Bandas");
        mdi.setImageResource(R.drawable.christian_cross);
        mdi.setActivity_related(MenuDrawerItem.Activity_Related.BAND_LIST);
        menuItemList.add(mdi);
        MenuDrawerItem mdi2 = new MenuDrawerItem();
        mdi2.setTitle("Álbumes");
        mdi2.setImageResource(R.drawable.music);
        mdi2.setActivity_related(MenuDrawerItem.Activity_Related.DISC_LIST);
        menuItemList.add(mdi2);
        MenuDrawerItem mdi3 = new MenuDrawerItem();
        mdi3.setTitle("Conciertos");
        mdi3.setImageResource(R.drawable.business);
        mdi3.setActivity_related(MenuDrawerItem.Activity_Related.CONCERT_LIST);
        menuItemList.add(mdi3);
    }

    public static void watchYoutubeVideo(Activity act, String id){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            act.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            act.startActivity(intent);
        }
    }

    public static String convertToFirebaseValidKey(String preKey){
return preKey.
        replace('/', '_').
        replace('.','_').
        replace( '#','_').
        replace('$','_').
        replace('[','_').
        replace(']','_').
        replace('{','_').
        replace('}','_');
    }
}
