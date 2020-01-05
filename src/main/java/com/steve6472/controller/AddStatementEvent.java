package com.steve6472.controller;

import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 * Is run before the item is added
 *
 ***********************/
public class AddStatementEvent extends Event
{
	private static final HandlerList handlers = new HandlerList();

	private final int x, y;
	private final CustomItem customItem;
	private final Player player;
	private final AdvancedGui gui;

	public AddStatementEvent(AdvancedGui gui, CustomItem customItem, Player player)
	{
		this.gui = gui;
		this.x = gui.getEditingX();
		this.y = gui.getEditingY();
		this.customItem = customItem;
		this.player = player;
	}

	public AdvancedGui getGui()
	{
		return gui;
	}

	public CustomItem getCustomItem()
	{
		return customItem;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Player getPlayer()
	{
		return player;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}
}
