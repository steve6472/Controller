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
public class GetItemInHand extends CustomItem
{
	public GetItemInHand(int id)
	{
		super(id, Material.WHITE_STAINED_GLASS_PANE, ChatColor.AQUA + "Get item in Players Hand");
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.GET_ITEM_IN_MAIN_HAND.create());
		gui.show(player);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
