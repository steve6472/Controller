package com.steve6472.controller.items;

import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.guis.ItemGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class AddItemSlot extends CustomItem
{
	public AddItemSlot(int id, Material material, String name, String... lore)
	{
		super(id, material, name, lore);
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		Controller.getGui(ItemGui.class).open(player);
//		gui.getItemGui().open(player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
