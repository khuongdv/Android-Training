package android.z10.com.androidrealm.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Admin on 6/13/2016.
 */
public class Item extends RealmObject {
    @PrimaryKey
    private String name;
    private String description;
    private int quantity;


    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public void copyData(Item item) {
        this.setName(item.getName());
        this.setQuantity(item.getQuantity());
        this.setDescription(item.getDescription());
    }
}
