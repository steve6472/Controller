package com.steve6472.controller.items.events;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class GetItem extends CustomItem
{
	public GetItem(int id, String name, String returnType)
	{
		super(id, Material.WHITE_STAINED_GLASS_PANE, ChatColor.AQUA + name, ChatColor.GRAY + returnType);
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
