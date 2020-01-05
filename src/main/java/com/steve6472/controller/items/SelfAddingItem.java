package com.steve6472.controller.items;

import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.CustomItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class SelfAddingItem extends CustomItem
{
	public SelfAddingItem(int id, Material material, String name, String... lore)
	{
		super(id, material, name, lore);
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), create());
		gui.show(player);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
