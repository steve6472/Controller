package com.steve6472.controller.items.ifitem;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.items.GetEntityType;
import com.steve6472.controller.items.events.EventInteractEntity;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class EntityTypeCondition implements IIfCondition
{
	@Override
	public boolean evaluate(Player player, AdvancedGui gui, int x, int y, CustomItem start, If root)
	{
		CustomItem operator = CustomItem.getCustomItem(gui.getItem(x + 1, y));
		if (!getType(gui.getItem(x + 1, y)).equals("Operator"))
		{
			player.sendMessage(ChatColor.RED + "Operator expected at " + (x + 1) + "/" + y);
			return false;
		}
		CustomItem last = CustomItem.getCustomItem(gui.getItem(x + 2, y));
		if (!getType(gui.getItem(x + 2, y)).equals("Entity Type"))
		{
			if (last != null)
			{
				player.sendMessage(ChatColor.RED + "Entity Type expected at " + (x + 2) + "/" + y);
				return false;
			}
		}
		if (last == start)
		{
			player.sendMessage(ChatColor.RED + "Left and Right side is identical!");
			return false;
		}

		EntityType left, right;

		left = getType(player, start, gui.getItem(x, y));
		right = getType(player, last, gui.getItem(x + 2, y));

		boolean equals = left == right;

		if (operator == CustomItems.EQUALS)
			return root.evaluate(player, gui, x + 3, y, equals);
		else if (operator == CustomItems.NOT_EQUALS)
			return root.evaluate(player, gui, x + 3, y, !equals);

		return false;
	}

	private EntityType getType(Player player, CustomItem item, ItemStack itemStack)
	{
		if (item == CustomItems.GET_EVENT_INTERACT_ENTITY)
		{
			return ((EventInteractEntity) CustomItems.EVENT_INTERACT_ENTITY).lastClicked.getType();
		}
		if (item instanceof GetEntityType)
		{
			String entityName = getItemName(itemStack).replace(" ", "_").toUpperCase();
			return EntityType.valueOf(entityName);
		}

		return null;
	}

	private String getItemName(ItemStack item)
	{
		if (item.getItemMeta() != null)
		{
			ItemMeta meta = item.getItemMeta();
			String name = meta.getDisplayName();
			int index = name.lastIndexOf('§');
			if (index != -1)
				name = name.substring(index + 2);
			return name;
		}
		return "";
	}

	@Override
	public String getType()
	{
		return "Entity Type";
	}
}
