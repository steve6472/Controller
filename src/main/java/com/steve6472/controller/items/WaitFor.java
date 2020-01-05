package com.steve6472.controller.items;

import com.steve6472.controller.AddStatementEvent;
import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class WaitFor extends CustomItem
{
	public WaitFor(int id)
	{
		super(id, Material.CLOCK, ChatColor.GOLD + "Wait For Event");
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		Bukkit.getPluginManager().callEvent(new AddStatementEvent(gui, this, player));

		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.WAIT_FOR_EVENT.create());
		gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY(), CustomItems.ADD_EVENT.create());

		gui.show(player);
	}

	@Override
	public void run(Player player, int x, int y, long delay, AdvancedGui gui)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(Controller.getInstance(),
			() -> gui.pause(player, CustomItem.getCustomItem(gui.getItem(x + 1, y)), x + 1, y), delay);
	}

	@Override
	public int getHeight()
	{
		return 1;
	}

	@Override
	public ItemStack create(String... moreLore)
	{
		ItemStack i = super.create(moreLore);

		if (i.getItemMeta() == null)
			return new ItemStack(Material.AIR);
		ItemMeta meta = i.getItemMeta();

		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(meta);

		return i;
	}
}
