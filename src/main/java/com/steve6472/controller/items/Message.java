package com.steve6472.controller.items;

import com.steve6472.controller.*;
import com.steve6472.controller.guis.AdvancedGui;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
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
public class Message extends CustomItem
{
	private boolean editing;

	public Message(int id)
	{
		super(id, Material.NAME_TAG, "Message");
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		gui.awaitInput(CustomItems.MESSAGE);
		player.sendMessage(ChatColor.GREEN + "Type message: " + ChatColor.GRAY + " (type cancel to cancel)");
		player.closeInventory();
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.awaitInput(CustomItems.MESSAGE);
		player.sendMessage(ChatColor.GREEN + "Type new message: ");
		player.sendMessage(ChatColor.GRAY + "(type cancel to cancel)");

		ItemStack i = gui.getItem(gui.getEditingX(), gui.getEditingY());
		if (i.getItemMeta() != null && i.getItemMeta().getLore() != null && !i.getItemMeta().getLore().isEmpty())
		{
			BaseComponent[] click = new ComponentBuilder("Click to paste old message in chat")
				.color(net.md_5.bungee.api.ChatColor.GOLD)
				.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, i.getItemMeta().getLore().get(1).substring(2).replace("§", "&")))
				.create();
			player.spigot().sendMessage(click);
		}


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
			Bukkit.getScheduler().scheduleSyncDelayedTask(Controller.getInstance(), () -> player.sendMessage(i.getItemMeta().getLore().get(1)), delay);
			gui.next(player, x + 1, y, delay);
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

		String text = ChatColor.WHITE + input;
		text = text.replace("&", "§");

		if (!editing)
			Bukkit.getPluginManager().callEvent(new AddStatementEvent(gui, this, player));

		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.MESSAGE.create(ChatColor.GRAY + "Text:", text));
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY(), CustomItems.MESSAGE_DELAY.create(ChatColor.WHITE + "Delay: " + ChatColor.GRAY + "0"));
		if (!editing)
			gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
		else
			editing = false;

		return true;
	}

	@Override
	public int getHeight()
	{
		return 1;
	}
}
