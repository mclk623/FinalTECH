package io.taraxacum.finaltech.command;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.taraxacum.finaltech.factory.ItemValueMap;
import io.taraxacum.finaltech.items.unusable.CopyCardItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Final_ROOT
 */
public class GetItemFake implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.getLogger().info("command active!");
        Bukkit.getLogger().info(s);
        Bukkit.getLogger().info(Arrays.toString(strings));
        Bukkit.getLogger().info(strings.length + "");
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ItemStack item = player.getItemInHand();
            ItemStack copyCardItem = CopyCardItem.newItem(item, "1");
            player.setItemInHand(copyCardItem);
            Bukkit.getLogger().info("ok");
        }
        for(Map.Entry<String, List<String>> entry : ItemValueMap.valueItemListOutputMap.entrySet()) {
            Bukkit.getLogger().info("&7value= " + entry.getKey());
            for(String value : entry.getValue()) {
                Bukkit.getLogger().info("    " + SlimefunItem.getById(value).getItemName());
            }
        }
        return true;
    }
}
