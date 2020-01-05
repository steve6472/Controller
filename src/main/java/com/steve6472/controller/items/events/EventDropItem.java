package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventDropItem extends EventItem
{
	public Item lastDropped;

	public EventDropItem(int id)
	{
		super(id, "Drop Item");
	}

	@EventHandler
	public void playerBreakBlockEvent(PlayerDropItemEvent e)
	{
		lastDropped = e.getItemDrop();

		AdvancedGui pausedGui = Controller.pausedGuis.get(e.getPlayer());
		if (pausedGui != null)
			pausedGui.unPause(e.getPlayer(), this);
	}
}
