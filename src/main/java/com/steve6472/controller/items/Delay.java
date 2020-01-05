package com.steve6472.controller.items;

import com.steve6472.controller.*;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class Delay extends CustomItem
{
	private boolean editing;
	private boolean isMessageDelay;

	public Delay(int id, boolean isMessageDelay)
	{
		super(id, Material.CLOCK, isMessageDelay ? "Message Delay" : "Delay");
		this.isMessageDelay = isMessageDelay;
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.awaitInput(CustomItems.DELAY);
		player.sendMessage(ChatColor.GREEN + "Type delay: " + ChatColor.GRAY + " (type cancel to cancel)");
		player.closeInventory();
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		if (isMessageDelay)
			gui.awaitInput(CustomItems.MESSAGE_DELAY);
		else
			gui.awaitInput(CustomItems.DELAY);
		player.sendMessage("Type new delay: ");
		player.closeInventory();
		editing = true;
		return true;
	}

	@Override
	public void run(Player player, int x, int y, long delay, AdvancedGui gui)
	{
		ItemStack i = gui.getItem(x, y);
		if (i.getItemMeta() != null && i.getItemMeta().getLore() != null && !i.getItemMeta().getLore().isEmpty())
		{
			String t = i.getItemMeta().getLore().get(0);
			int d = Integer.parseInt(t.substring(11));
			if (isMessageDelay)
				gui.next(player, x - 1, y + 1, delay + d);
			else
				gui.next(player, x, y + 1, delay + d);
		}
	}

	@Override
	public boolean input(AdvancedGui gui, String input, Player player)
	{
		if (input.equals("cancel"))
		{
			gui.show(player);
			return true;
		}

		if (!isNumeric(input))
		{
			player.sendMessage(ChatColor.RED + "Input must be numeric!");
			gui.show(player);
			return true;
		}

		if (!editing)
			Bukkit.getPluginManager().callEvent(new AddStatementEvent(gui, this, player));

		if (isMessageDelay)
			gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.MESSAGE_DELAY.create(ChatColor.WHITE + "Delay: " + ChatColor.GRAY + input));
		else
			gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.DELAY.create(ChatColor.WHITE + "Delay: " + ChatColor.GRAY + input));

		if (!isMessageDelay)
			if (!editing)
				gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
			else
				editing = false;

		return true;
	}

	private static boolean isNumeric(String str)
	{
		for (char c : str.toCharArray())
		{
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}

	@Override
	public int getHeight()
	{
		return isMessageDelay ? 0 : 1;
	}
}
