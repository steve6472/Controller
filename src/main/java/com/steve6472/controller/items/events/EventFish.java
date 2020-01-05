package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventFish extends EventItem
{
	public Item lastFished;
	public PlayerFishEvent.State lastState;

	public EventFish(int id)
	{
		super(id, "Fish");
	}

	@EventHandler
	public void playerBreakBlockEvent(PlayerFishEvent e)
	{
		lastState = e.getState();
		if (e.getCaught() instanceof Item)
		{
			lastFished = (Item) e.getCaught();
		}

		AdvancedGui pausedGui = Controller.pausedGuis.get(e.getPlayer());
		if (pausedGui != null)
			pausedGui.unPause(e.getPlayer(), this);
	}
}
