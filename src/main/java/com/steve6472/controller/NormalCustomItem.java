package com.steve6472.controller;

import org.bukkit.Material;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class NormalCustomItem extends CustomItem
{
	public NormalCustomItem(int id, Material material, String name, String... lore)
	{
		super(id, material, name, lore);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
