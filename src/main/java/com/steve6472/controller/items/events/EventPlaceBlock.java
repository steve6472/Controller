package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventPlaceBlock extends EventItem
{
	public Block lastPlaced;

	public EventPlaceBlock(int id)
	{
		super(id, "Block Place");
	}

	@EventHandler
	public void playerBreakBlockEvent(BlockPlaceEvent e)
	{
		lastPlaced = e.getBlock();
		AdvancedGui pausedGui = Controller.pausedGuis.get(e.getPlayer());
		if (pausedGui != null)
			pausedGui.unPause(e.getPlayer(), this);
	}
}
