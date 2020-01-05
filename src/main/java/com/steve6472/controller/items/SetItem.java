package com.steve6472.controller.items;

import com.steve6472.controller.AddStatementEvent;
import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.Bukkit;
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
public class SetItem extends CustomItem
{
	public SetItem(int id)
	{
		super(id, Material.APPLE, ChatColor.GOLD + "Set Item");
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		Bukkit.getPluginManager().callEvent(new AddStatementEvent(gui, this, player));

		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.SET_ITEM.create());
		gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY(), CustomItems.SET_ITEM_SLOT.create());
		gui.setItem(gui.getEditingX() + 2, gui.getEditingY(), CustomItems.ADD_ITEM.create());

		gui.show(player);
	}

	@Override
	public void run(Player player, int x, int y, long delay, AdvancedGui gui)
	{
		if (!getType(gui.getItem(x + 1, y)).equals("Item Slot"))
		{
			player.sendMessage(ChatColor.RED + "Item Slot expected at " + (x + 1) + "/" + y);
		}

		CustomItem slot = CustomItem.getCustomItem(gui.getItem(x + 1, y));
		if (slot instanceof EquipmentSlot)
		{
			ItemStack stack = gui.getItem(x + 2, y);
			if (CustomItem.getCustomItem(stack) == CustomItems.ADD_ITEM)
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(Controller.getInstance(), () -> setItem((EquipmentSlot) slot, player, new ItemStack(Material.AIR)), delay);
			} else
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(Controller.getInstance(), () -> setItem((EquipmentSlot) slot, player, new ItemStack(stack)), delay);
			}
		} else if (slot == CustomItems.FIRST_EMPTY)
		{
			ItemStack stack = gui.getItem(x + 2, y);
			if (CustomItem.getCustomItem(stack) != CustomItems.ADD_ITEM)
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(Controller.getInstance(), () -> player.getInventory().addItem(new ItemStack(stack)), delay);
			}
		}

		gui.next(player, x, y + 1, delay);
	}

	private void setItem(EquipmentSlot slot, Player player, ItemStack item)
	{
		if (player.getEquipment() == null)
			return;

		switch (slot.getItemSlot().getSlot())
		{
			case HAND: player.getEquipment().setItemInMainHand(item);
				break;
			case OFF_HAND: player.getEquipment().setItemInOffHand(item);
				break;
			case FEET: player.getEquipment().setBoots(item);
				break;
			case LEGS: player.getEquipment().setLeggings(item);
				break;
			case CHEST: player.getEquipment().setChestplate(item);
				break;
			case HEAD: player.getEquipment().setHelmet(item);
				break;
		}
	}

	@Override
	public int getHeight()
	{
		return 1;
	}
}
