package com.steve6472.controller.items;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 06.01.2020
 * Project: Controller
 *
 ***********************/
public class GetEntityType extends CustomItem
{
	public GetEntityType(int id, Material material, String name)
	{
		super(id, material, ChatColor.AQUA + name, ChatColor.GRAY + "Entity Type");
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), this.create());

		gui.show(player);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
