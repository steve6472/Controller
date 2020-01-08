package com.steve6472.controller.items;

import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class AddElse extends CustomItem
{
	public AddElse(int id)
	{
		super(id, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Click to add Else (required)");
	}

	@Override
	public boolean edit(AdvancedGui gui, Player player)
	{
		gui.setItem(gui.getEditingX() - 1, gui.getEditingY(), CustomItems.ELSE.create());
		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.NULL.create());
		gui.setItem(gui.getEditingX(), gui.getEditingY() + 1, CustomItems.STATEMENT.create());
		gui.setItem(gui.getEditingX() - 1, gui.getEditingY() + 2, CustomItems.ADD_END.create());

		for (int i = gui.getEditingY(); i >= 0; i--)
		{
			CustomItem item = CustomItem.getCustomItem(gui.getItem(gui.getEditingX() - 1, i));

			System.out.println(i + " " + item);
			if (item != null)
			{
				if (item == CustomItems.IF)
				{
					ItemStack ifItem = gui.getItem(gui.getEditingX() - 1, i);
					if (ifItem.getItemMeta() == null)
						break;
					ItemMeta meta = ifItem.getItemMeta();

					if (meta.getLore() != null && !meta.getLore().isEmpty())
					{
						String[] coordinates = meta.getLore().get(0).substring(7).split("/");
						int x = Integer.parseInt(coordinates[0]);
						int y = Integer.parseInt(coordinates[1]);

						meta.setLore(Arrays.asList(ChatColor.GRAY + "End: " + x + "/" + (y + 2), ChatColor.GRAY + "Else: " + x + "/" + y));
						ifItem.setItemMeta(meta);
					}

					break;
				}
			}
		}

		gui.show(player);
		return true;
	}

	@Override
	public int getHeight()
	{
		return 2;
	}
}
