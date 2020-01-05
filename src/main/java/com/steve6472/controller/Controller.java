package com.steve6472.controller;

import com.steve6472.controller.guis.*;
import com.steve6472.controller.items.ifitem.If;
import com.steve6472.controller.items.ifitem.ItemCondition;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Controller extends JavaPlugin implements Listener
{
	private static Controller INSTANCE;

	private static Map<Player, AdvancedGui> advancedGui;
	public static Map<Player, AdvancedGui> pausedGuis;

	private static HashMap<Class<? extends IControllerGui>, IControllerGui> guis;

	@EventHandler
	public void click(InventoryClickEvent e)
	{
		if (e.getClickedInventory() == null)
			return;
		if (e.getRawSlot() > e.getView().getTopInventory().getSize())
			return;
		if (!e.getView().getTitle().startsWith("Advanced GUI"))
			return;
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;

		ItemStack currentItem = e.getCurrentItem();

		if (CustomItem.getCustomItem(currentItem) == null)
			return;

		AdvancedGui gui = advancedGui.get((Player) e.getWhoClicked());

		for (IControllerGui g : guis.values())
		{
			if (e.getView().getTitle().endsWith(g.getName()))
			{
				g.click(e, gui, CustomItem.getCustomItem(currentItem));
				return;
			}
		}

		gui.clickAdvancedGui(e, CustomItem.getCustomItem(currentItem));
	}

	@EventHandler
	public void playerQuit(PlayerQuitEvent e)
	{
		advancedGui.remove(e.getPlayer());
		pausedGuis.remove(e.getPlayer());
	}

	@EventHandler
	public void playerKick(PlayerKickEvent e)
	{
		advancedGui.remove(e.getPlayer());
		pausedGuis.remove(e.getPlayer());
	}

	/*

	Can not be used as opening new GUI calls this and destroys the pair

	@EventHandler
	public void closeInventory(InventoryCloseEvent e)
	{
		if (advancedGui.get((Player) e.getPlayer()) == null)
			return;

		clear((Player) e.getPlayer());
	}*/

	@EventHandler(ignoreCancelled = true)
	public void chatInput(PlayerChatEvent e)
	{
		if (advancedGui.get(e.getPlayer()) == null)
			return;

		AdvancedGui gui = advancedGui.get(e.getPlayer());

		if (gui.getInput() == null)
			return;

		boolean shouldCancel = gui.getInput().input(advancedGui.get(e.getPlayer()), e.getMessage(), e.getPlayer());

		e.setCancelled(shouldCancel);

		gui.show(e.getPlayer());
		gui.setInput(null);
	}

	@EventHandler
	public void openGui(PlayerInteractEvent e)
	{
		if (e.getHand() != EquipmentSlot.HAND)
			return;
		if (e.getItem() == null)
			return;
		if (e.getItem().getType() != Material.NETHER_STAR)
			return;
		if (CustomItem.getCustomItem(e.getItem()) != CustomItems.CONTROLLER)
			return;

		e.setCancelled(true);

		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if (pausedGuis.get(e.getPlayer()) == null)
			{
				AdvancedGui gui = new AdvancedGui(e.getPlayer());
				advancedGui.put(e.getPlayer(), gui);
				advancedGui.get(e.getPlayer()).run(e.getPlayer());
				advancedGui.remove(e.getPlayer());
			}
		} else
		{
			AdvancedGui gui = new AdvancedGui(e.getPlayer());

			advancedGui.put(e.getPlayer(), gui);
			gui.show(e.getPlayer());
		}
	}

	@Override
	public void onEnable()
	{
		INSTANCE = this;

		advancedGui = new HashMap<>();
		pausedGuis = new HashMap<>();
		guis = new HashMap<>();

		Bukkit.getPluginManager().registerEvents(this, INSTANCE);

		If.addCondition(new ItemCondition());

		addGui(StatementGui.class, new StatementGui());
		addGui(BooleanGui.class, new BooleanGui());
		addGui(ItemGui.class, new ItemGui());
		addGui(EventGui.class, new EventGui());
	}

	@Override
	public void onDisable()
	{
	}

	public static void addGui(Class<? extends IControllerGui> clazz, IControllerGui gui)
	{
		guis.putIfAbsent(clazz, gui);
	}

	public static HashMap<Class<? extends IControllerGui>, IControllerGui> getGuis()
	{
		return guis;
	}

	public static <T extends IControllerGui> T getGui(Class<T> clazz)
	{
		return (T) guis.get(clazz);
	}

	public static void clear(Player player)
	{
		advancedGui.remove(player);
	}

	public static Controller getInstance()
	{
		return INSTANCE;
	}

	public static void fillWithNull(Inventory inv)
	{
		for (int i = 0; i < inv.getSize(); i++)
		{
			if (inv.getItem(i) == null)
				inv.setItem(i, CustomItems.NULL.create());
		}
	}
}
