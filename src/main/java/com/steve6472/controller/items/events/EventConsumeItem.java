package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventConsumeItem extends EventItem
{
	public ItemStack lastConsumed;

	public EventConsumeItem(int id)
	{
		super(id, "Consume Item");
	}

	@EventHandler
	public void playerBreakBlockEvent(PlayerItemConsumeEvent e)
	{
		lastConsumed = e.getItem();

		AdvancedGui pausedGui = Controller.pausedGuis.get(e.getPlayer());
		if (pausedGui != null)
			pausedGui.unPause(e.getPlayer(), this);
	}
}
