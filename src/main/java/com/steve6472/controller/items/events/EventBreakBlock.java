package com.steve6472.controller.items.events;

import com.steve6472.controller.Controller;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventBreakBlock extends EventItem
{
	public Block lastBroken;

	public EventBreakBlock(int id)
	{
		super(id, "Block Break");
	}

	@EventHandler
	public void playerBreakBlockEvent(BlockBreakEvent e)
	{
		lastBroken = e.getBlock();
		AdvancedGui pausedGui = Controller.pausedGuis.get(e.getPlayer());
		if (pausedGui != null)
			pausedGui.unPause(e.getPlayer(), this);
	}
}
