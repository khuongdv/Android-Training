package android.z10.com.androidrealm.utils;

import android.content.Context;
import android.z10.com.androidrealm.entity.Item;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Admin on 6/13/2016.
 */
public class RealmUtils {
    private static RealmUtils instance = null;
    Context context;
    RealmConfiguration realmConfig;
    Realm realm;

    private RealmUtils() {

    }

    public static RealmUtils getInstance(Context context) {
        if (instance == null) {
            instance = new RealmUtils();
            instance.context = context;
            instance.realmConfig = new RealmConfiguration.Builder(context).build();
            Realm.setDefaultConfiguration(instance.realmConfig);
            instance.realm = Realm.getDefaultInstance();
        }
        return instance;
    }

    public List<Item> getAllItem() {
        RealmResults<Item> listItem = realm.where(Item.class).findAll();
        List<Item> ret = new ArrayList<>();
        if (listItem.size() > 0) {
            ret.addAll(listItem.subList(0, listItem.size()));
        }
        return ret;
    }

    public void saveItem(Item item) {
        realm.beginTransaction();
        Item persistingItem = realm.createObject(Item.class);
        persistingItem.copyData(item);
        realm.commitTransaction();
    }

    public void delete(Item myitem) {
        realm.beginTransaction();
        RealmResults<Item> listItem = realm.where(Item.class).equalTo("name", myitem.getName()).findAll();
        if (listItem.size() == 1) {
            listItem.deleteFirstFromRealm();
        }
        realm.commitTransaction();
    }
}
