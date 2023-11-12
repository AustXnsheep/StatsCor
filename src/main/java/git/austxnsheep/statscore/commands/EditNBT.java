package git.austxnsheep.statscore.commands;

import git.austxnsheep.statscore.utils.NBTEdit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EditNBT implements CommandExecutor, NBTEdit {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 3) {
            sender.sendMessage("Usage: /modifyitem <key> <value> <type>");
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null) {
            player.sendMessage("You must be holding an item to modify.");
            return true;
        }

        String action = args[0];
        String key = args[1];

        if ("remove".equals(action)) {
            removeNBT(item, key);
            player.sendMessage("Removed NBT data for key '" + key + "'");
        } else {
            if (args.length < 4) {
                player.sendMessage("Usage for remove/edit: /modifyitem <remove|edit> <key> <value> <type>");
                return false;
            }

            String value = args[2];
            String type = args[3].toLowerCase();

            if (action.equals("edit")) {
                if ("string".equals(type)) {
                    setString(item, key, value);
                    player.sendMessage("Set String NBT '" + value + "' for key '" + key + "'");
                } else if ("int".equals(type)) {
                    try {
                        int intValue = Integer.parseInt(value);
                        setInt(item, key, intValue);
                        player.sendMessage("Set Integer NBT '" + intValue + "' for key '" + key + "'");
                    } catch (NumberFormatException e) {
                        player.sendMessage("Value for 'int' type must be an integer.");
                    }
                } else {
                    player.sendMessage("Invalid type specified. Use 'string' or 'int'.");
                }
            } else {
                player.sendMessage("Invalid action specified. Use 'add', 'edit', or 'remove'.");
                return true;
            }
        }
        return false;
    }
}
