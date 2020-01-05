package com.steve6472.controller.items;

import com.steve6472.controller.guis.AdvancedGui;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 04.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class SaveName extends CustomItem
{
	public SaveName(int id)
	{
		super(id, Material.NAME_TAG, ChatColor.GOLD + "Set Name");
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.awaitInput(CustomItems.SAVE_NAME);
		player.sendMessage(ChatColor.GREEN + "Type name for this save: " + ChatColor.GRAY + " (type cancel to cancel)");
		player.closeInventory();

		return true;
	}

	@Override
	public boolean input(AdvancedGui gui, String input, Player player)
	{
		if (input.equals("cancel"))
		{
			gui.show(player);
			return true;
		}

		if (player.getEquipment() == null || CustomItem.getCustomItem(player.getEquipment().getItemInMainHand()) != CustomItems.CONTROLLER)
		{
			player.sendMessage(ChatColor.RED + "You must be holding " + ChatColor.GOLD + "Controller");
		}

		ItemStack item = player.getEquipment().getItemInMainHand();

		ItemMeta meta = item.getItemMeta();
		if (meta == null)
		{
			player.sendMessage(ChatColor.RED + "Item has no ItemMeta");
			return true;
		}

		String text = ChatColor.WHITE + input;
		text = text.replace("&", "§");

		meta.setLore(Collections.singletonList(ChatColor.GRAY + "Name: " + text));

		item.setItemMeta(meta);

		return true;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}
}
