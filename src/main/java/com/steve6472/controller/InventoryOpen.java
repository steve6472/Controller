package com.steve6472.controller;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
@FunctionalInterface
public interface InventoryOpen
{
	void process(Player player, Inventory inv);
}
