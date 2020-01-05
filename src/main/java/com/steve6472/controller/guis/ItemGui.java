package com.steve6472.controller.guis;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class ItemGui implements IControllerGui
{
	public void open(Player player)
	{
		Inventory inv = Bukkit.createInventory(player, 6 * 9, "Advanced GUI" + getName());
		inv.setItem(0, CustomItems.FIRST_EMPTY.create());

		inv.setItem(9, CustomItems.SLOT_HELMET.create());
		inv.setItem(10, CustomItems.SLOT_CHESTPLATE.create());
		inv.setItem(11, CustomItems.SLOT_LEGGINGS.create());
		inv.setItem(12, CustomItems.SLOT_BOOTS.create());
		inv.setItem(13, CustomItems.SLOT_MAIN_HAND.create());
		inv.setItem(14, CustomItems.SLOT_OFF_HAND.create());

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
		return " (Items)";
	}
}
