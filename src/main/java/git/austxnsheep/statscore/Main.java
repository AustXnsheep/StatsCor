package git.austxnsheep.statscore;

import git.austxnsheep.statscore.commands.EditNBT;
import git.austxnsheep.statscore.listeners.InventoryOpen;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static Main instance;
    @Override
    public void onEnable() {
        instance = this;
        Objects.requireNonNull(this.getCommand("modifyitem")).setExecutor(new EditNBT());

        getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
    }

    @Override
    public void onDisable() {
    }
    public static Main getInstance() {
        return instance;
    }
}
