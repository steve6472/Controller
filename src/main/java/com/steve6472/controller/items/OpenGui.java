package com.steve6472.controller.items;

import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.guis.IControllerGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 06.01.2020
 * Project: Controller
 *
 ***********************/
public class OpenGui extends CustomItem
{
	private final Class<? extends IControllerGui> gui;

	public OpenGui(int id, Material material, String name, Class<? extends IControllerGui> gui, String... lore)
	{
		super(id, material, name, lore);
		this.gui = gui;
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		Controller.getGui(this.gui).open(player);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
