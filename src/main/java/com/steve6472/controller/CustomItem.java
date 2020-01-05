package com.steve6472.controller;

import com.steve6472.controller.guis.AdvancedGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public abstract class CustomItem
{
	private static List<CustomItem> customItems = new ArrayList<>();

	private final int id;
	private final Material material;
	private final String name;
	private final String[] lore;

	protected CustomItem(int id, Material material, String name, String... lore)
	{
		this.id = id;
		this.material = material;
		this.name = name;
		this.lore = lore;
		for (CustomItem i : customItems)
		{
			if (i.getId() == id)
			{
				throw new IllegalArgumentException("Duplicate id: " + id + " " + name + " and " + i.getName());
			}
		}
		customItems.add(this);

		if (this instanceof Listener)
			Bukkit.getPluginManager().registerEvents((Listener) this, Controller.getInstance());
	}

	/**
	 * AdvancedGui automatically opens after this method
	 *
	 * @param input Input typed by player
	 * @param player Who Typed it
	 * @return calls {@link PlayerChatEvent#setCancelled}
	 */
	public boolean input(AdvancedGui gui, String input, Player player) { return false; }

	/**
	 * Called from {@link AdvancedGui#clickAdvancedGui}
	 *
	 * @param gui
	 * @param player Who Clicked
	 * @return calls {@link InventoryClickEvent#setCancelled}
	 */
	public boolean edit(AdvancedGui gui, Player player) { return true; }

	/**
	 * Called from {@link AdvancedGui#run(Player)} and {@link AdvancedGui#next(Player, int, int, long)}
	 *
	 * @param player Player
	 * @param x x coordinate in GUI
	 * @param y y coordinate in GUI
	 * @param delay total delay
	 * @param gui gui (used for calling {@link AdvancedGui#next}
	 */
	public void run(Player player, int x, int y, long delay, AdvancedGui gui) {}

	/**
	 *
	 * @param gui
	 * @param player
	 */
	public void create(AdvancedGui gui, Player player) {}

	public abstract int getHeight();

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String[] getLore()
	{
		return lore;
	}

	public ItemStack create(String... moreLore)
	{
		ItemStack item = new ItemStack(material);
		if (item.getItemMeta() == null)
			return new ItemStack(Material.AIR);
		ItemMeta meta = item.getItemMeta();

		List<String> lore = new ArrayList<>(getLore().length);
		lore.addAll(Arrays.asList(getLore()));
		if (moreLore != null)
			lore.addAll(Arrays.asList(moreLore));
		meta.setLore(lore);

		meta.setDisplayName(ChatColor.GOLD + getName());
		meta.setCustomModelData(id);
		item.setItemMeta(meta);
		return item;
	}

	public static CustomItem getCustomItem(ItemStack item)
	{
		if (item == null)
			return null;
		if (item.getItemMeta() == null)
			return null;
		if (!item.getItemMeta().hasCustomModelData())
			return null;

		int id = item.getItemMeta().getCustomModelData();

		for (CustomItem i : customItems)
		{
			if (i.getId() == id)
				return i;
		}

		return null;
	}

	protected String getType(ItemStack item)
	{
		if (item.getItemMeta() == null)
		{
			return "";
		}
		ItemMeta meta = item.getItemMeta();
		if (meta.getLore() != null)
		{
			if (meta.getLore().isEmpty())
			{
				return "";
			}

			return meta.getLore().get(0).substring(2);
		} else
		{
			return "";
		}
	}
}
