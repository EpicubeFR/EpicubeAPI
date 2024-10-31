package fr.epicube.api.utils;

import org.bukkit.entity.Entity;
import java.lang.reflect.Field;

public class Flags {

    /**
     * Sets a string flag on an entity.
     *
     * @param entity the entity to set the flag on
     * @param index  the index of the flag
     * @param value  the value to set
     */
    public static void setStringFlag(Entity entity, int index, String value) {
        try {
            Object nmsEntity = getNMSEntity(entity);
            Field dataWatcherField = nmsEntity.getClass().getDeclaredField("datawatcher");
            dataWatcherField.setAccessible(true);
            Object dataWatcher = dataWatcherField.get(nmsEntity);
            Field d = dataWatcher.getClass().getDeclaredField("d");
            d.setAccessible(true);
            d.set(dataWatcher, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the NMS (Net Minecraft Server) entity from a Bukkit entity.
     *
     * @param entity the Bukkit entity
     * @return the NMS entity
     * @throws Exception if an error occurs during reflection
     */
    private static Object getNMSEntity(Entity entity) throws Exception {
        Class<?> craftEntityClass = Class.forName("org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity");
        return craftEntityClass.getMethod("getHandle").invoke(entity);
    }
}
