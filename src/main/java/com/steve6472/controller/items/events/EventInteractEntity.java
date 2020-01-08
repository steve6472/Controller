package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventInteractEntity extends EventItem
{
	public Entity lastClicked;

	public EventInteractEntity(int id)
	{
		super(id, "Interact Entity");
	}

	@EventHandler
	public void playerBreakBlockEvent(PlayerInteractEntityEvent e)
	{
		if (e.getHand() == EquipmentSlot.HAND)
		{
			lastClicked = e.getRightClicked();

			AdvancedGui pausedGui = Controller.pausedGuis.get(e.getPlayer());
			if (pausedGui != null)
				pausedGui.unPause(e.getPlayer(), this);
		}
	}
}
