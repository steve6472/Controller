package com.steve6472.controller.guis;

import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public interface IControllerGui
{
	void open(Player player);

	void click(InventoryClickEvent e, AdvancedGui gui, CustomItem customItem);

	String getName();

	default void fillWithNull(Inventory inv)
	{
		Controller.fillWithNull(inv);
	}
}
