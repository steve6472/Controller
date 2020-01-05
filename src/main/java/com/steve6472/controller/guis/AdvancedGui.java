package com.steve6472.controller.guis;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.steve6472.controller.Controller;
import com.steve6472.controller.CustomItem;
import com.steve6472.controller.CustomItems;
import net.minecraft.server.v1_15_R1.MojangsonParser;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class AdvancedGui
{
	private ItemStack[][] items;
	private int editingX, editingY;
	private int showX, showY;
	private int width, height;

	private CustomItem input;

	public AdvancedGui(Player player)
	{
		boolean loaded = load(player);
		if (!loaded)
		{
			width = 20;
			height = 40;
			items = new ItemStack[width][height];
			items[0][0] = CustomItems.STATEMENT.create();
		}
	}

	/**
	 * Requires {@link AdvancedGui#load}
	 */
	public AdvancedGui()
	{
	}

	public boolean load(Player player)
	{
		if (player.getEquipment() != null && player.getEquipment().getItemInMainHand().getType() == Material.NETHER_STAR)
		{
			ItemStack item = player.getEquipment().getItemInMainHand();

			if (CustomItem.getCustomItem(item) != CustomItems.CONTROLLER)
				return false;

			return load(item);
		}

		return false;
	}

	public boolean load(ItemStack item)
	{
		ItemMeta meta = item.getItemMeta();

		if (meta == null)
			return false;

		PersistentDataContainer container = meta.getPersistentDataContainer();

		NamespacedKey widthKey = new NamespacedKey(Controller.getInstance(), "width");
		NamespacedKey heightKey = new NamespacedKey(Controller.getInstance(), "height");

		if (container.has(widthKey, PersistentDataType.INTEGER))
		{
			Integer w = container.get(widthKey, PersistentDataType.INTEGER);
			if (w != null)
				width = w;
		} else
		{
			return false;
		}
		if (container.has(heightKey, PersistentDataType.INTEGER))
		{
			Integer h = container.get(heightKey, PersistentDataType.INTEGER);
			if (h != null)
				height = h;
		} else
		{
			return false;
		}

		items = new ItemStack[width][height];

		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				NamespacedKey itemKey = new NamespacedKey(Controller.getInstance(), i + "x" + j);
				if (!container.has(itemKey, PersistentDataType.STRING))
					continue;

				String nbt = container.get(itemKey, PersistentDataType.STRING);

				ItemStack itemStack = null;

				try
				{
					itemStack = CraftItemStack.asBukkitCopy(net.minecraft.server.v1_15_R1.ItemStack.a(MojangsonParser.parse(nbt)));
				} catch (CommandSyntaxException e)
				{
					e.printStackTrace();
				}

				setItem(i, j, itemStack);
			}
		}
		item.setItemMeta(meta);
		return true;
	}

	public void save(Player player)
	{
		if (player.getEquipment() != null && player.getEquipment().getItemInMainHand().getType() == Material.NETHER_STAR)
		{
			ItemStack item = player.getEquipment().getItemInMainHand();

			if (CustomItem.getCustomItem(item) != CustomItems.CONTROLLER)
			{
				player.sendMessage(ChatColor.RED + "You are not holding " + ChatColor.GOLD + "Controller");
				return;
			}

			save(item);
		}

		Controller.clear(player);
	}

	public void save(ItemStack item)
	{
		if (CustomItem.getCustomItem(item) != CustomItems.CONTROLLER)
		{
			throw new IllegalArgumentException("Item is not Controller");
		}

		ItemMeta meta = item.getItemMeta();
		if (meta == null)
			return;

		PersistentDataContainer container = meta.getPersistentDataContainer();

		container.set(new NamespacedKey(Controller.getInstance(), "width"), PersistentDataType.INTEGER, getWidth());
		container.set(new NamespacedKey(Controller.getInstance(), "height"), PersistentDataType.INTEGER, getHeight());
		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				if (getItem(i, j) == null)
				{
					NamespacedKey key = new NamespacedKey(Controller.getInstance(), i + "x" + j);
					if (container.has(key, PersistentDataType.STRING))
					{
						container.remove(key);
					}
					continue;
				}

				Bukkit.getLogger().info("Saving " + getItem(i, j).getType() + " at " + i + "/" + j);

				NBTTagCompound nbtTagCompoundItem = new NBTTagCompound();
				CraftItemStack.asNMSCopy(getItem(i, j)).save(nbtTagCompoundItem);
				container.set(new NamespacedKey(Controller.getInstance(), i + "x" + j), PersistentDataType.STRING, nbtTagCompoundItem.toString());
			}
		}
		item.setItemMeta(meta);
	}

	public void awaitInput(CustomItem type)
	{
		input = type;
	}

	public void clickAdvancedGui(InventoryClickEvent e, CustomItem customItem)
	{
		setEditing(e.getRawSlot() % 9, e.getRawSlot() / 9);

		Player player = (Player) e.getWhoClicked();

		if (customItem == CustomItems.ADD_ITEM)
		{
			/* Replace Item Slot with Item */
			if (e.getWhoClicked().getItemOnCursor().getType() != Material.AIR)
			{
				e.getClickedInventory().setItem(e.getRawSlot(), new ItemStack(e.getWhoClicked().getItemOnCursor()));
				setItem(getEditingX(), getEditingY(), new ItemStack(e.getWhoClicked().getItemOnCursor()));
				player.updateInventory();
			}
			e.setCancelled(true);
			return;
		}

		boolean shouldCancel = customItem.edit(this, player);

		e.setCancelled(shouldCancel);
	}

	public void show(Player player)
	{
		Inventory inv = Bukkit.createInventory(player, 9 * 6, "Advanced GUI (" + showX + "/" + showY + "), (" + width + "x" + height + ")");

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				int X = showX + i;
				int Y = showY + j;

				int slot = (j * 9) + i;

				if (X < 0 || X >= 20 || Y < 0 || Y >= 40)
					inv.setItem(slot, CustomItems.OUT_OF_BOUNDS.create());
				else
					if (items[X][Y] == null)
						inv.setItem(slot, CustomItems.NULL.create());
					else
						inv.setItem(slot, items[X][Y]);
			}
		}

		inv.setItem(45, CustomItems.SAVE.create());
		inv.setItem(46, CustomItems.SAVE_NAME.create());
		inv.setItem(47, CustomItems.NULL.create());
		inv.setItem(48, CustomItems.NULL.create());
		inv.setItem(49, CustomItems.NULL.create());
		inv.setItem(50, CustomItems.UP.create());
		inv.setItem(51, CustomItems.DOWN.create());
		inv.setItem(52, CustomItems.LEFT.create());
		inv.setItem(53, CustomItems.RIGHT.create());

		player.openInventory(inv);
	}

	public void run(Player player)
	{
		next(player, 0, 0, 0);
	}

	public void next(Player player, int x, int y, long delay)
	{
		CustomItem item = CustomItem.getCustomItem(items[x][y]);
		if (item == null)
			return;

		item.run(player, x, y, delay, this);
	}

	public void setItem(int x, int y, ItemStack item)
	{
		items[x][y] = item;
		Bukkit.getLogger().info("Adding " + item.getType() + " at " + x + "/" + y);
	}

	public void setEditing(int x, int y)
	{
		this.editingX = x + showX;
		this.editingY = y + showY;
	}

	public int getEditingX()
	{
		return editingX;
	}

	public int getEditingY()
	{
		return editingY;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void move(int x, int y, Player player)
	{
		showX += x;
		showY += y;

		show(player);
	}

	public ItemStack getItem(int x, int y)
	{
		return items[x][y];
	}

	public CustomItem getInput()
	{
		return input;
	}

	public void setInput(CustomItem input)
	{
		this.input = input;
	}

	public void insertColumn(int r)
	{
		height++;
		ItemStack[][] out = new ItemStack[width][height];
		for (int i = 0; i < width; i++)
		{
			if (r >= 0)
				System.arraycopy(items[i], 0, out[i], 0, r);
		}
		for (int i = 0; i < width; i++)
		{
			if (out.length - r + 1 >= 0)
				System.arraycopy(items[i], r + 1 - 1, out[i], r + 1, out.length - r + 1);
		}
		items = out;
	}
}
