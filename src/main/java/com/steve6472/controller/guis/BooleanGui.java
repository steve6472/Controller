package com.steve6472.controller.guis;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.InventoryOpen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class BooleanGui implements IControllerGui
{
	public InventoryOpen additionalItems;

	public BooleanGui()
	{
		additionalItems = (p, inv) -> {
		};
	}

	public void open(Player player)
	{
		Inventory inv = Bukkit.createInventory(player, 6 * 9, "Advanced GUI" + getName());
		inv.setItem(0, CustomItems.EQUALS.create());
		inv.setItem(1, CustomItems.NOT_EQUALS.create());
		inv.setItem(2, CustomItems.AND.create());
		inv.setItem(3, CustomItems.OR.create());
		inv.setItem(5, CustomItems.TRUE.create());
		inv.setItem(6, CustomItems.FALSE.create());

		inv.setItem(9 , CustomItems.GET_ITEM_IN_MAIN_HAND.create());
		inv.setItem(10, CustomItems.GET_ITEM_IN_OFF_HAND.create());
		inv.setItem(11, CustomItems.EMPTY_ITEM.create());
		inv.setItem(12, CustomItems.ADD_ITEM.create(ChatColor.RED + "" + ChatColor.UNDERLINE + "Works only in Editor!"));
		inv.setItem(13, CustomItems.ENTITY_TYPE.create(ChatColor.RED + "" + ChatColor.UNDERLINE + "Click to Select!"));

		inv.setItem(18, CustomItems.GET_EVENT_BREAK_BLOCK.create());
		inv.setItem(19, CustomItems.GET_EVENT_PLACE_BLOCK.create());
		inv.setItem(20, CustomItems.GET_EVENT_DROP_ITEM.create());
		inv.setItem(21, CustomItems.GET_EVENT_PICKUP_ITEM.create());
		inv.setItem(22, CustomItems.GET_EVENT_CONSUME_ITEM.create());
		inv.setItem(23, CustomItems.GET_EVENT_FISH_ITEM.create());
		inv.setItem(24, CustomItems.GET_EVENT_FISH_STATE.create());
		inv.setItem(25, CustomItems.GET_EVENT_INTERACT_ENTITY.create());

		inv.setItem(27, CustomItems.DO.create());

		additionalItems.process(player, inv);

		fillWithNull(inv);
		player.openInventory(inv);
	}

	public void click(InventoryClickEvent e, AdvancedGui gui, CustomItem customItem)
	{
		customItem.create(gui, (Player) e.getWhoClicked());
		e.setCancelled(true);
	}

	@Override
	public String getName()
	{
		return " (Boolean Operators)";
	}
}
