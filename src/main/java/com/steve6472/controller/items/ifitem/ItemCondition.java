package com.steve6472.controller.items.ifitem;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.items.events.EventConsumeItem;
import com.steve6472.controller.items.events.EventDropItem;
import com.steve6472.controller.items.events.EventFish;
import com.steve6472.controller.items.events.EventPickUpItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class ItemCondition implements IIfCondition
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
		if (!getType(gui.getItem(x + 2, y)).equals("Item"))
		{
			if (last != null)
			{
				player.sendMessage(ChatColor.RED + "Item expected at " + (x + 2) + "/" + y);
				return false;
			}
		}
		if (last == start)
		{
			player.sendMessage(ChatColor.RED + "Left and Right side is identical!");
			return false;
		}

		ItemStack left , right;

		left = getItem(player, start);
		if (left == null)
			left = gui.getItem(x, y);

		right = getItem(player, last);
		if (right == null)
			right = gui.getItem(x + 2, y);

		boolean equals = false;

		if ((left == null && right != null && right.getType() == Material.AIR) || (right == null && left != null && left.getType() == Material.AIR))
		{
			equals = true;
		}
		else if (left != null)
		{
			equals = left.equals(right);
		}

		if (operator == CustomItems.EQUALS)
			return root.evaluate(player, gui, x + 3, y, equals);
		else if (operator == CustomItems.NOT_EQUALS)
			return root.evaluate(player, gui, x + 3, y, !equals);

		return false;
	}

	private ItemStack getItem(Player player, CustomItem item)
	{
		if (item == CustomItems.GET_ITEM_IN_MAIN_HAND)
			if (player.getEquipment() != null)
				return player.getEquipment().getItemInMainHand();

		if (item == CustomItems.GET_ITEM_IN_OFF_HAND)
			if (player.getEquipment() != null)
				return player.getEquipment().getItemInOffHand();

		if (item == CustomItems.GET_EVENT_PICKUP_ITEM)
			return ((EventPickUpItem) CustomItems.EVENT_PICKUP_ITEM).lastPickedUp.getItemStack();

		if (item == CustomItems.GET_EVENT_CONSUME_ITEM)
			return ((EventConsumeItem) CustomItems.EVENT_CONSUME_ITEM).lastConsumed;

		if (item == CustomItems.GET_EVENT_DROP_ITEM)
			return ((EventDropItem) CustomItems.EVENT_DROP_ITEM).lastDropped.getItemStack();

		if (item == CustomItems.GET_EVENT_FISH_ITEM)
			return ((EventFish) CustomItems.GET_EVENT_FISH_ITEM).lastFished.getItemStack();

		return null;
	}

	@Override
	public String getType()
	{
		return "Item";
	}
}
