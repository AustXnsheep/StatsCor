package git.austxnsheep.statscore.listeners;

import git.austxnsheep.statscore.Main;
import git.austxnsheep.statscore.utils.ItemLore;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class InventoryOpen implements Listener, ItemLore {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;

        Player player = (Player) event.getPlayer();
        for (ItemStack item : player.getInventory()) {
            if (item != null && hasCustomStats(item)) {
                ItemLore.updateItemLoreWithStats(item);
            }
        }
    }

    private boolean hasCustomStats(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.has(new NamespacedKey(Main.getInstance(), "health"), PersistentDataType.INTEGER) ||
                container.has(new NamespacedKey(Main.getInstance(), "mana"), PersistentDataType.INTEGER) ||
                container.has(new NamespacedKey(Main.getInstance(), "speed"), PersistentDataType.INTEGER) ||
                container.has(new NamespacedKey(Main.getInstance(), "power"), PersistentDataType.INTEGER);
    }
}
