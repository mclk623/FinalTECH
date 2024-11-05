package io.taraxacum.finaltech.core.enchantment;

import io.taraxacum.finaltech.FinalTech;
import io.taraxacum.libs.plugin.util.ItemStackUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * @author Final_ROOT
 * @since 1.0
 */
public class NullEnchantment extends Enchantment {
	public static final NamespacedKey KEY = new NamespacedKey(JavaPlugin.getPlugin(FinalTech.class), "FINALTECH_NULL_ENCHANTMENT");
    public static final Enchantment ENCHANTMENT = new NullEnchantment(KEY);

    private NullEnchantment(NamespacedKey key) {
        super();
    }

    @Nonnull
    @Override
    public String getName() {
        return "FINALTECH_NULL_ENCHANTMENT";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Nonnull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@Nonnull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@Nonnull ItemStack itemStack) {
        return false;
    }

    public static void addAndHidden(@Nonnull ItemStack item) {
        item.addUnsafeEnchantment(ENCHANTMENT, 0);
        ItemStackUtil.addItemFlag(item, ItemFlag.HIDE_ENCHANTS);
    }

	@Override
	public NamespacedKey getKey() {
		return KEY;
	}

	@Override
	public String getTranslationKey() {
		return "";
	}
}
