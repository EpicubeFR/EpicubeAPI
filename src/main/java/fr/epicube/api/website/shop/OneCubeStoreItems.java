package fr.epicube.api.website.shop;

/* Private code from OneCube (https://github.com/HexaGamesFR/OC-Website) */

public enum OneCubeStoreItems {
    
    PACK_BASIC("Pack basique", 4.99, false),
    PACK_EPIC("Pack épique", 9.99, false),
    PACK_LEGENDARY("Pack légendaire", 19.99, false),
    PACK_MASTER("Pack master", 29.99, true);

    private final String displayName;
    private final double price;
    private final boolean perMonth;

    OneCubeStoreItems(String displayName, double price, boolean perMonth) {
        this.displayName = displayName;
        this.price = price;
        this.perMonth = perMonth;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPerMonth() {
        return perMonth;
    }
}
