package io.taraxacum.finaltech.core.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.taraxacum.common.util.JavaUtil;
import io.taraxacum.common.util.ReflectionUtil;
import io.taraxacum.finaltech.FinalTech;
import io.taraxacum.finaltech.core.event.ConfigSaveActionEvent;
import io.taraxacum.finaltech.core.item.machine.AbstractMachine;
import io.taraxacum.libs.slimefun.service.SlimefunLocationDataService;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Final_ROOT
 */
public class ConfigSaveListener implements Listener {

    @EventHandler
    public void onConfigSave(ConfigSaveActionEvent configSaveActionEvent) {
        String id = configSaveActionEvent.getId();
        SlimefunItem slimefunItem = SlimefunItem.getById(id);

        // Slimefun cargo node
        if(slimefunItem != null
                && JavaUtil.matchOnce(id, SlimefunItems.CARGO_INPUT_NODE.getItemId(), SlimefunItems.CARGO_OUTPUT_NODE.getItemId(), SlimefunItems.CARGO_OUTPUT_NODE_2.getItemId())
                && FinalTech.getLocationDataService() instanceof SlimefunLocationDataService slimefunLocationDataService) {
            BlockMenu blockMenu = slimefunLocationDataService.getBlockMenu(configSaveActionEvent.getLocation());
            if(blockMenu != null) {
                Method method = ReflectionUtil.getMethod(slimefunItem.getClass(), "updateBlockMenu");
                if(method != null) {
                    try {
                        method.setAccessible(true);
                        method.invoke(slimefunItem, blockMenu, blockMenu.getBlock());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // FinalTECH machines
        if(slimefunItem != null
                && slimefunItem.getAddon().getJavaPlugin().equals(FinalTech.getInstance())
                && slimefunItem instanceof AbstractMachine abstractMachine
                && FinalTech.getLocationDataService() instanceof SlimefunLocationDataService slimefunLocationDataService) {
            BlockMenu blockMenu = slimefunLocationDataService.getBlockMenu(configSaveActionEvent.getLocation());
            if(blockMenu != null) {
                try {
                    Field field = ReflectionUtil.getField(abstractMachine.getClass(), "inventoryTemplate");
                    if (field != null) {
                        field.setAccessible(true);
                        Object menu = field.get(abstractMachine);
                        if (menu != null) {
                            Method method = ReflectionUtil.getMethod(menu.getClass(), "updateInventory");
                            if (method != null) {
                                method.setAccessible(true);
                                method.invoke(menu, blockMenu.toInventory(), blockMenu.getLocation());
                            }
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
