package com.steve6472.controller.items.ifitem;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public interface IIfCondition
{
	boolean evaluate(Player player, AdvancedGui gui, int x, int y, CustomItem start, If root);

	String getType();

	default String getType(ItemStack item)
	{
		String result = "";
		if (item.getItemMeta() != null)
		{
			ItemMeta meta = item.getItemMeta();
			if (meta.getLore() != null)
			{
				if (!meta.getLore().isEmpty())
				{
					result = meta.getLore().get(0).substring(2);
				}

			}
		}
		return result;
	}
}
