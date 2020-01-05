package com.steve6472.controller.guis;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventGui implements IControllerGui
{
	@Override
	public void open(Player player)
	{
		Inventory inv = Bukkit.createInventory(player, 6 * 9, "Advanced GUI" + getName());
		inv.setItem(0, CustomItems.EVENT_BREAK_BLOCK.create());
		inv.setItem(1, CustomItems.EVENT_PLACE_BLOCK.create());
		inv.setItem(2, CustomItems.EVENT_DROP_ITEM.create());
		inv.setItem(3, CustomItems.EVENT_PICKUP_ITEM.create());
		inv.setItem(4, CustomItems.EVENT_CONSUME_ITEM.create());
		inv.setItem(5, CustomItems.EVENT_FISH.create());

		fillWithNull(inv);
		player.openInventory(inv);
	}

	@Override
	public void click(InventoryClickEvent e, AdvancedGui gui, CustomItem customItem)
	{
		customItem.create(gui, (Player) e.getWhoClicked());
		e.setCancelled(true);
	}

	@Override
	public String getName()
	{
		return " (Events)";
	}
}
