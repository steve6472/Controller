package com.steve6472.controller.items;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.items.enums.EnumItemSlot;
import org.bukkit.entity.Player;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class EquipmentSlot extends CustomItem
{
	private EnumItemSlot itemSlot;

	public EquipmentSlot(int id, EnumItemSlot itemSlot, String... lore)
	{
		super(id, itemSlot.getMaterial(), itemSlot.getName(), lore);
		this.itemSlot = itemSlot;
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), this.create());
		gui.show(player);
	}

	public EnumItemSlot getItemSlot()
	{
		return itemSlot;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
