package com.steve6472.controller.items;

import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class AddEnd extends CustomItem
{
	public AddEnd(int id)
	{
		super(id, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Click to add End (required)");
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.END.create());
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY(), CustomItems.NULL.create());
		gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
		gui.show(player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 1;
	}
}
