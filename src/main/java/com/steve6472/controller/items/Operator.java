package com.steve6472.controller.items;

import com.steve6472.controller.*;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.items.enums.EnumOperator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class Operator extends CustomItem
{
	public Operator(int id, EnumOperator operator, String... lore)
	{
		super(id, Material.WHITE_STAINED_GLASS_PANE, operator.getName(), lore);
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), this.create());
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY(), CustomItems.ADD_BOOLEAN.create());
		gui.setItem(gui.getEditingX() + 2, gui.getEditingY(), CustomItems.BOOLEAN_OPERATOR_OR_DO.create());
		gui.show(player);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
