package com.steve6472.controller.items.ifitem;

import com.steve6472.controller.AddStatementEvent;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class If extends CustomItem implements Listener
{
	public If(int id)
	{
		super(id, Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "If");
	}

	private static List<IIfCondition> conditions = new ArrayList<>();

	public static void addCondition(IIfCondition condition)
	{
		conditions.add(condition);
	}

	public static List<IIfCondition> getConditions()
	{
		return conditions;
	}

	@EventHandler
	public void onStatementAdd(AddStatementEvent e)
	{
		if (e.getX() <= 0)
			return;

		AdvancedGui gui = e.getGui();
		boolean foundElse = false;

		for (int i = e.getY(); i >= 0; i--)
		{
			CustomItem item = CustomItem.getCustomItem(gui.getItem(e.getX() - 1, i));

			if (item != null)
			{
				if (item == CustomItems.ELSE)
					foundElse = true;

				if (item == CustomItems.IF)
				{
					ItemStack ifItem = gui.getItem(e.getX() - 1, i);
					if (ifItem.getItemMeta() == null)
						continue;
					ItemMeta meta = ifItem.getItemMeta();

					if (meta.getLore() != null && !meta.getLore().isEmpty())
					{
						System.out.println(meta.getLore().size());
						if (meta.getLore().size() == 2)
						{
							if (!foundElse)
							{
								line(meta, 1, gui, e);
								updateEnd(ifItem, e);
								updateElse(ifItem, e);
							} else
							{
								line(meta, 0, gui, e);
								updateEnd(ifItem, e);
							}
						} else
						{
							line(meta, 0, gui, e);
							updateEnd(ifItem, e);
						}
					}

					break;
				}
			}
		}
	}

	private void updateEnd(ItemStack item, AddStatementEvent e)
	{
		ItemMeta meta = item.getItemMeta();
		/* Adds line before END */
		String[] coordinates = meta.getLore().get(0).substring(7).split("/");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);

		List<String> lore = meta.getLore();
		lore.set(0, ChatColor.GRAY + "End: " + x + "/" + (y + e.getCustomItem().getHeight()));

		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	private void updateElse(ItemStack item, AddStatementEvent e)
	{
		ItemMeta meta = item.getItemMeta();
		/* Adds line before ELSE */
		String[] coordinates = meta.getLore().get(1).substring(8).split("/");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);

		List<String> lore = meta.getLore();
		lore.set(1, ChatColor.GRAY + "Else: " + x + "/" + (y + e.getCustomItem().getHeight()));

		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	private void line(ItemMeta meta, int index, AdvancedGui gui, AddStatementEvent e)
	{
		String[] coordinates = meta.getLore().get(index).substring(8).split("/");
		int y = Integer.parseInt(coordinates[1]);
		for (int j = 0; j < e.getCustomItem().getHeight(); j++)
		{
			gui.insertColumn(y);
		}
	}

	@Override
	public void create(AdvancedGui gui, Player player)
	{
		Bukkit.getPluginManager().callEvent(new AddStatementEvent(gui, this, player));

		gui.setItem(gui.getEditingX(), gui.getEditingY(), CustomItems.IF.create(ChatColor.GRAY + "End: " + gui.getEditingX() + "/" + (gui.getEditingY() + 2)));
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY() + 1, CustomItems.STATEMENT.create(ChatColor.DARK_GRAY + "IF " + gui.getEditingX() + "/" + gui.getEditingY()));
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY(), CustomItems.ADD_BOOLEAN.create());
		gui.setItem(gui.getEditingX() + 2, gui.getEditingY(), CustomItems.BOOLEAN_OPERATOR_OR_DO.create());
		gui.setItem(gui.getEditingX(), gui.getEditingY() + 2, CustomItems.ADD_END.create());
		gui.setItem(gui.getEditingX() + 1, gui.getEditingY() + 2, CustomItems.ADD_ELSE.create());

		gui.show(player);
	}

	@Override
	public void run(Player player, int x, int y, long delay, AdvancedGui gui)
	{
		if (evaluate(player, gui, x + 1, y, true))
			gui.next(player, x + 1, y + 1, delay);
		else
		{
			ItemStack item = gui.getItem(x, y);
			if (item.getItemMeta() == null)
				return;

			if (item.getItemMeta().getLore() != null && item.getItemMeta().getLore().size() == 2)
			{
				String[] coordinates = item.getItemMeta().getLore().get(1).substring(8).split("/");
				gui.next(player, Integer.parseInt(coordinates[0]) + 1, Integer.parseInt(coordinates[1]) + 1, delay);
			}
		}

		ItemStack item = gui.getItem(x, y);
		if (item.getItemMeta() == null)
			return;

		if (item.getItemMeta().getLore() != null && !item.getItemMeta().getLore().isEmpty())
		{
			String[] coordinates = item.getItemMeta().getLore().get(0).substring(7).split("/");
			gui.next(player, Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]) + 1, delay);
		}
	}

	public boolean evaluate(Player player, AdvancedGui gui, int x, int y, boolean lastResult)
	{
		CustomItem start = CustomItem.getCustomItem(gui.getItem(x, y));

		if (start == CustomItems.DO)
			return lastResult;

		if (start == CustomItems.AND)
		{
			return lastResult && evaluate(player, gui, x + 1, y, true);
		}

		if (start == CustomItems.OR)
		{
			return lastResult || evaluate(player, gui, x + 1, y, true);
		}

		if (getType(gui.getItem(x, y)).equals("Boolean"))
			return start == CustomItems.TRUE;

		for (IIfCondition condition : conditions)
		{
			if (condition.getType().equals(getType(gui.getItem(x, y))))
			{
				return condition.evaluate(player, gui, x, y, start, this);
			}
		}

		return false;
	}

	@Override
	public int getHeight()
	{
		return 2;
	}
}
