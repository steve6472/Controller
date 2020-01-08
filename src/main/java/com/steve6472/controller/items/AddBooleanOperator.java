package com.steve6472.controller.items;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.BooleanGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class AddBooleanOperator extends CustomItem
{
	public AddBooleanOperator(int id, Material material, String name, String... lore)
	{
		super(id, material, name, lore);
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		Controller.getGui(BooleanGui.class).open(player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
