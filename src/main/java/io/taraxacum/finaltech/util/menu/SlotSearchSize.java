package io.taraxacum.finaltech.util.menu;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.taraxacum.finaltech.menu.AbstractMachineMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Final_ROOT
 */
public class SlotSearchSize {
    public static final String KEY_INPUT = "input-size";
    public static final String KEY_OUTPUT = "output-size";

    public static final String VALUE_INPUTS_ONLY = "inputs-only";
    public static final String VALUE_OUTPUTS_ONLY = "outputs-only";
    public static final String VALUE_INPUTS_AND_OUTPUTS = "inputs-and-outputs";
    public static final String VALUE_OUTPUTS_AND_INPUTS = "outputs-and-inputs";

    public static final ItemStack INPUTS_ONLY_ICON = new CustomItemStack(Material.SOUL_TORCH, "&9仅搜索输入槽", "&7对箱子、桶、漏斗等原版容器，该设置无效");
    public static final ItemStack OUTPUTS_ONLY_ICON = new CustomItemStack(Material.TORCH, "&6仅搜索输出槽", "&7对箱子、桶、漏斗等原版容器，该设置无效");
    public static final ItemStack INPUTS_AND_OUTPUTS_ICON = new CustomItemStack(Material.REDSTONE_TORCH, "&d搜索输入槽输出槽", "&7对箱子、桶、漏斗等原版容器，该设置无效");
    public static final ItemStack OUTPUTS_AND_INPUTS_ICON = new CustomItemStack(Material.REDSTONE_TORCH, "&d搜索输出槽输入槽", "&7对箱子、桶、漏斗等原版容器，该设置无效");

    private static final Map<String, ItemStack> INPUT_ICON_MAP = new LinkedHashMap<>() {
        {
            this.put(VALUE_INPUTS_ONLY, INPUTS_ONLY_ICON);
            this.put(VALUE_OUTPUTS_ONLY, OUTPUTS_ONLY_ICON);
            this.put(VALUE_INPUTS_AND_OUTPUTS, INPUTS_AND_OUTPUTS_ICON);
            this.put(VALUE_OUTPUTS_AND_INPUTS, OUTPUTS_AND_INPUTS_ICON);
        }
    };
    private static final Map<String, ItemStack> OUTPUT_ICON_MAP = new LinkedHashMap<>() {
        {
            this.put(VALUE_OUTPUTS_ONLY, OUTPUTS_ONLY_ICON);
            this.put(VALUE_INPUTS_AND_OUTPUTS, INPUTS_AND_OUTPUTS_ICON);
            this.put(VALUE_OUTPUTS_AND_INPUTS, OUTPUTS_AND_INPUTS_ICON);
            this.put(VALUE_INPUTS_ONLY, INPUTS_ONLY_ICON);
        }
    };

    public static final BlockStorageIconHelper INPUT_HELPER = BlockStorageIconHelper.newInstanceOrGet(BlockStorageHelper.ID_CARGO, KEY_INPUT, INPUT_ICON_MAP);

    public static final BlockStorageIconHelper OUTPUT_HELPER = BlockStorageIconHelper.newInstanceOrGet(BlockStorageHelper.ID_CARGO, KEY_OUTPUT, OUTPUT_ICON_MAP);

    public static ChestMenu.MenuClickHandler getInputHandler(@Nonnull BlockMenu blockMenu, @Nonnull Block block, @Nonnull AbstractMachineMenu abstractMachineMenu, int slot) {
        return (player, i, itemStack, clickAction) -> {
            String size = SlotSearchSize.INPUT_HELPER.getValue(block.getLocation());
            if(clickAction.isRightClicked()) {
                size = SlotSearchSize.INPUT_HELPER.previousValue(size);
            } else {
                size = SlotSearchSize.INPUT_HELPER.nextValue(size);
            }
            blockMenu.replaceExistingItem(slot, SlotSearchSize.INPUT_HELPER.getIcon(size));
            SlotSearchSize.INPUT_HELPER.setValue(block.getLocation(), size);
            return false;
        };
    }

    public static ChestMenu.MenuClickHandler getOutputHandler(@Nonnull BlockMenu blockMenu, @Nonnull Block block, @Nonnull AbstractMachineMenu abstractMachineMenu, int slot) {
        return (player, i, itemStack, clickAction) -> {
            String size = SlotSearchSize.OUTPUT_HELPER.getValue(block.getLocation());
            if(clickAction.isRightClicked()) {
                size = SlotSearchSize.OUTPUT_HELPER.previousValue(size);
            } else {
                size = SlotSearchSize.OUTPUT_HELPER.nextValue(size);
            }
            blockMenu.replaceExistingItem(slot, SlotSearchSize.OUTPUT_HELPER.getIcon(size));
            SlotSearchSize.OUTPUT_HELPER.setValue(block.getLocation(), size);
            return false;
        };
    }

    @Deprecated
    public static final String next(String size) {
        if (size == null) {
            return VALUE_INPUTS_ONLY;
        }
        switch (size) {
            case VALUE_INPUTS_ONLY:
                return VALUE_OUTPUTS_ONLY;
            case VALUE_OUTPUTS_ONLY:
                return VALUE_INPUTS_AND_OUTPUTS;
            case VALUE_INPUTS_AND_OUTPUTS:
                return VALUE_OUTPUTS_AND_INPUTS;
            case VALUE_OUTPUTS_AND_INPUTS:
            default:
                return VALUE_INPUTS_ONLY;
        }
    }

    @Deprecated
    public static final ItemStack getIcon(String size) {
        if (size == null) {
            return Icon.ERROR_ICON;
        }
        switch (size) {
            case VALUE_INPUTS_ONLY:
                return INPUTS_ONLY_ICON;
            case VALUE_OUTPUTS_ONLY:
                return OUTPUTS_ONLY_ICON;
            case VALUE_INPUTS_AND_OUTPUTS:
                return INPUTS_AND_OUTPUTS_ICON;
            case VALUE_OUTPUTS_AND_INPUTS:
                return OUTPUTS_AND_INPUTS_ICON;
            default:
                return Icon.ERROR_ICON;
        }
    }
}
