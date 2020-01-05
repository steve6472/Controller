package com.steve6472.controller.items.events;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 05.01.2020
 * Project: Controller
 *
 ***********************/
public class EventItem extends CustomItem implements Listener
{
	public EventItem(int id, String eventName)
	{
		super(id, Material.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE + "Event", ChatColor.GRAY + eventName);
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX(), gui.getEditingY(), this.create());

		gui.show(player);
	}

	@Override
	public void run(Player player, int x, int y, long delay, AdvancedGui gui)
	{
		gui.next(player, x - 1, y + 1, delay);
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
