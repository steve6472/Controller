package com.steve6472.controller.items;

import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.guis.StatementGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class Statement extends CustomItem
{
	public Statement(int id)
	{
		super(id, Material.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW + "Click to add Statement");
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		Controller.getGui(StatementGui.class).open(player);
//		gui.getStatementGui().open(player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
