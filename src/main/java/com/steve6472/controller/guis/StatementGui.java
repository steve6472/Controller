package com.steve6472.controller.guis;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class StatementGui implements IControllerGui
{
	public void open(Player player)
	{
		Inventory inv = Bukkit.createInventory(player, 6 * 9, "Advanced GUI" + getName());
		inv.setItem(0, CustomItems.MESSAGE.create(ChatColor.WHITE + "Type message in chat"));
		inv.setItem(1, CustomItems.DELAY.create(ChatColor.WHITE + "Type delay in chat (in Ticks)"));
		inv.setItem(2, CustomItems.SET_ITEM.create());
		inv.setItem(3, CustomItems.IF.create());

		fillWithNull(inv);
		player.openInventory(inv);
	}

	public void click(InventoryClickEvent e, AdvancedGui gui, CustomItem customItem)
	{
		customItem.create(gui, (Player) e.getWhoClicked());
		e.setCancelled(true);
	}

	@Override
	public String getName()
	{
		return " (Statements)";
	}
}
