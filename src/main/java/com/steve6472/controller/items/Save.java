package com.steve6472.controller.items;

import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class Save extends CustomItem
{
	public Save(int id)
	{
		super(id, Material.PAPER, ChatColor.GOLD + "Save");
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.save(player);
		player.closeInventory();
		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
