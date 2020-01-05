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
public class MoveGui extends CustomItem
{
	private int moveX, moveY;

	public MoveGui(int id, String name, int moveX, int moveY)
	{
		super(id, Material.ARROW, name);
		this.moveX = moveX;
		this.moveY = moveY;
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.move(moveX, moveY, player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
