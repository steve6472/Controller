package com.steve6472.controller.items;

import com.steve6472.controller.AddStatementEvent;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 08.01.2020
 * Project: Controller
 *
 ***********************/
public class Goto extends CustomItem
{
	public Goto(int id)
	{
		super(id, Material.COMPASS, ChatColor.GREEN + "Go To");
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		Bukkit.getPluginManager().callEvent(new AddStatementEvent(gui, this, player));

		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.GOTO.create(ChatColor.WHITE + "Go to: " + ChatColor.GRAY + gui.getEditingX() + " " + (gui.getEditingY() + 1)));
		gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
		gui.show(player);
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.awaitInput(CustomItems.GOTO);
		player.sendMessage("Type coordinates: ");
		player.sendMessage(ChatColor.GRAY + "(type cancel to cancel)");
		player.closeInventory();
		return true;
	}

	@Override
	public void run(Player player, int x, int y, long delay, AdvancedGui gui)
	{
		ItemStack item = gui.getItem(x, y);
		if (item.getItemMeta() == null)
			return;

		if (item.getItemMeta().getLore() != null && !item.getItemMeta().getLore().isEmpty())
		{
			String[] coordinates = item.getItemMeta().getLore().get(0).substring(11).split(" ");
			int X = Integer.parseInt(coordinates[0]);
			int Y = Integer.parseInt(coordinates[1]);
			if (X == x && Y == y)
			{
				player.sendMessage(ChatColor.RED + "Goto can not reference itself!");
			} else
			{
				gui.next(player, X, Y, delay);
			}
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

		if (!checkFormat(input))
		{
			player.sendMessage(ChatColor.RED + "Input is incorrect!");
			player.sendMessage(ChatColor.RED + "Format: " + ChatColor.DARK_RED + "x " + ChatColor.DARK_GREEN + "y");
			gui.show(player);
			return true;
		}

		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.GOTO.create(ChatColor.WHITE + "Go to: " + ChatColor.GRAY + input));

		return true;
	}

	private static boolean checkFormat(String str)
	{
		boolean foundSpace = false;

		for (char c : str.toCharArray())
		{
			if (!foundSpace && c == ' ')
			{
				foundSpace = true;
				continue;
			}
			if (!Character.isDigit(c))
				return false;
		}
		return foundSpace;
	}

	@Override
	public int getHeight()
	{
		return 1;
	}
}
