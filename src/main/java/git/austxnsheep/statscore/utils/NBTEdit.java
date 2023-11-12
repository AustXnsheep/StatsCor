package git.austxnsheep.statscore.utils;

import git.austxnsheep.statscore.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public interface NBTEdit {
    default void setString(ItemStack item, String key, String value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(
                getKey(key),
                PersistentDataType.STRING,
                value
        );
        item.setItemMeta(meta);
    }

    default String getString(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        if (meta.getPersistentDataContainer().has(getKey(key), PersistentDataType.STRING)) {
            return meta.getPersistentDataContainer().get(getKey(key), PersistentDataType.STRING);
        }
        return null;
    }

    default void setInt(ItemStack item, String key, int value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(
                getKey(key),
                PersistentDataType.INTEGER,
                value
        );
        item.setItemMeta(meta);
    }

    default int getInt(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        if (meta.getPersistentDataContainer().has(getKey(key), PersistentDataType.INTEGER)) {
            return meta.getPersistentDataContainer().get(getKey(key), PersistentDataType.INTEGER);
        }
        return 0;
    }

    default void removeNBT(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().remove(getKey(key));
        item.setItemMeta(meta);
    }

    default NamespacedKey getKey(String key) {
        return new NamespacedKey(Main.getInstance(), key);
    }
}
