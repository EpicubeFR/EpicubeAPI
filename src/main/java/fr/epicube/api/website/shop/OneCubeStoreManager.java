package fr.epicube.api.website.shop;

import java.util.ArrayList;
import java.util.List;

/* Private code from OneCube (https://github.com/HexaGamesFR/OC-Website) */

public class OneCubeStoreManager {

    private static OneCubeStoreManager instance;
    private List<OneCubeStoreItems> items = new ArrayList<>();

    private OneCubeStoreManager() {}

    public static OneCubeStoreManager getInstance() {
        if (instance == null) {
            instance = new OneCubeStoreManager();
        }
        return instance;
    }

    /**
     * Adds an item to the store.
     *
     * @param item the item to add
     */
    public void addItem(OneCubeStoreItems item) {
        this.items.add(item);
    }

    /**
     * Removes an item from the store.
     *
     * @param item the item to remove
     */
    public void removeItem(OneCubeStoreItems item) {
        this.items.remove(item);
    }

    /**
     * Adds all items from the enum to the store.
     */
    public void addAll() {
        for (OneCubeStoreItems item : OneCubeStoreItems.values()) {
            addItem(item);
        }
    }

    /**
     * Retrieves an item by its display name.
     *
     * @param displayName the display name of the item
     * @return the item with the given display name, or null if not found
     */
    public OneCubeStoreItems getItemByDisplayName(String displayName) {
        for (OneCubeStoreItems item : items) {
            if (item.getDisplayName().equals(displayName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns the list of all items in the store.
     *
     * @return a list of all items
     */
    public List<OneCubeStoreItems> getItems() {
        return this.items;
    }
}
