package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventPickUpItem extends EventItem
{
	public Item lastPickedUp;

	public EventPickUpItem(int id)
	{
		super(id, "Item Pick Up");
	}

	@EventHandler
	public void playerBreakBlockEvent(EntityPickupItemEvent e)
	{
		lastPickedUp = e.getItem();
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();

			AdvancedGui pausedGui = Controller.pausedGuis.get(p);
			if (pausedGui != null)
				pausedGui.unPause(p, this);
		}
	}
}
