package com.steve6472.controller.guis;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 06.01.2020
 * Project: Controller
 *
 ***********************/
public class EntityTypeGui implements IControllerGui
{
	@Override
	public void open(Player player)
	{
		open(player, 0);
	}

	public void open(Player player, int page)
	{
		Inventory inv = Bukkit.createInventory(player, 6 * 9, "Advanced GUI" + getName());

		int startingId = 3100 + page * 9 * 5;

		for (int i = 0; i < 9 * 5; i++)
		{
			CustomItem item = CustomItem.getCustomItem(startingId++);
			if (item != null)
				inv.setItem(i, item.create());
		}

		inv.setItem(45, CustomItems.PREVIOUS.create());
		inv.setItem(53, CustomItems.NEXT.create());

		fillWithNull(inv);
		player.openInventory(inv);
	}

	@Override
	public void click(InventoryClickEvent e, AdvancedGui gui, CustomItem customItem)
	{
		if (CustomItem.getCustomItem(e.getCurrentItem()) == CustomItems.NEXT)
		{
			open((Player) e.getWhoClicked(), 1);
		}
		else if (CustomItem.getCustomItem(e.getCurrentItem()) == CustomItems.PREVIOUS)
		{
			open((Player) e.getWhoClicked(), 0);
		} else
		{
			customItem.create(gui, (Player) e.getWhoClicked());
		}
		e.setCancelled(true);
	}

	@Override
	public String getName()
	{
		return " (Entity Types)";
	}
}
