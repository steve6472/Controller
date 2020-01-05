package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.guis.EventGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class AddEvent extends CustomItem
{
	public AddEvent(int id)
	{
		super(id, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Add Event");
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		Controller.getGui(EventGui.class).open(player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
