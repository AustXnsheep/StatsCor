package git.austxnsheep.statscore.utils;

import git.austxnsheep.statscore.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public interface ItemLore {
    static void updateItemLoreWithStats(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        List<String> lore = new ArrayList<>();

        addStatIfPresent(lore, meta, "health", ChatColor.GRAY + "Health: ", ChatColor.GREEN);
        addStatIfPresent(lore, meta, "mana", ChatColor.GRAY + "Mana: ", ChatColor.BLUE);
        addStatIfPresent(lore, meta, "speed", ChatColor.GRAY + "Speed: ", ChatColor.YELLOW);
        addStatIfPresent(lore, meta, "power", ChatColor.GRAY + "Power: ", ChatColor.RED);

        lore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "Right-click to use");

        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    static void addStatIfPresent(List<String> lore, ItemMeta meta, String key, String prefix, ChatColor valueColor) {
        NamespacedKey namespacedKey = new NamespacedKey(Main.getInstance(), key);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(namespacedKey, PersistentDataType.INTEGER)) {
            int value = container.get(namespacedKey, PersistentDataType.INTEGER);
            lore.add(prefix + valueColor + value);
        }
    }
}
