package com.steve6472.controller;

import com.steve6472.controller.items.*;
import com.steve6472.controller.items.enums.EnumItemSlot;
import com.steve6472.controller.items.enums.EnumOperator;
import com.steve6472.controller.items.ifitem.If;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public class CustomItems
{
	/* 1111 - 1119 - System Items */
	public static final CustomItem NULL = new NormalCustomItem(1111, Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ");
	public static final CustomItem OUT_OF_BOUNDS = new NormalCustomItem(1112, Material.BLACK_STAINED_GLASS_PANE, ChatColor.DARK_RED + "OUT OF BOUNDS");
	public static final CustomItem CONTROLLER = new NormalCustomItem(1113, Material.NETHER_STAR, ChatColor.GOLD + "Controller");

	public static final CustomItem UP = new MoveGui(1114, "Up", 0, -1);
	public static final CustomItem DOWN = new MoveGui(1115, "Down", 0, 1);
	public static final CustomItem LEFT = new MoveGui(1116, "Left", -1, 0);
	public static final CustomItem RIGHT = new MoveGui(1117, "Right", 1, 0);
	public static final CustomItem SAVE = new Save(1118);
	public static final CustomItem SAVE_NAME = new SaveName(1119);

	/* 2000 - 2499 - Statements */
	public static final CustomItem STATEMENT = new Statement(2001);
	public static final CustomItem MESSAGE = new Message(2002);
	public static final CustomItem MESSAGE_DELAY = new Delay(2003, true);
	public static final CustomItem DELAY = new Delay(2004, false);
	public static final CustomItem IF = new If(2005);
	public static final CustomItem SET_ITEM = new SetItem(2006);

	public static final CustomItem END = new NormalCustomItem(2007, Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "End");
	public static final CustomItem DO = new SelfAddingItem(2008, Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "Do");

	/* 2500 - 2999 - Required Items */
	public static final CustomItem ADD_BOOLEAN = new AddBooleanOperator(2500, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Click to add Boolean (required)");
	public static final CustomItem BOOLEAN_OPERATOR_OR_DO = new AddBooleanOperator(2501, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Click to add Boolean Operator or Do (required)");
	public static final CustomItem ADD_END = new AddEnd(2502);
	public static final CustomItem ADD_ITEM = new SelfAddingItem(2503, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Add Item", ChatColor.WHITE + "Click here with item");

	/* 3000 - 3999 - Comparable */
	//TODO: Add target
	public static final CustomItem SET_ITEM_SLOT = new AddItemSlot(3000, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Item Slot", ChatColor.GRAY + "Item Slot");
	public static final CustomItem SLOT_HELMET = new EquipmentSlot(3001, EnumItemSlot.HELMET, ChatColor.GRAY + "Item Slot");
	public static final CustomItem SLOT_CHESTPLATE = new EquipmentSlot(3002, EnumItemSlot.CHESTPLATE, ChatColor.GRAY + "Item Slot");
	public static final CustomItem SLOT_LEGGINGS = new EquipmentSlot(3003, EnumItemSlot.LEGGINGS, ChatColor.GRAY + "Item Slot");
	public static final CustomItem SLOT_BOOTS = new EquipmentSlot(3004, EnumItemSlot.BOOTS, ChatColor.GRAY + "Item Slot");
	public static final CustomItem SLOT_MAIN_HAND = new EquipmentSlot(3005, EnumItemSlot.MAIN_HAND, ChatColor.GRAY + "Item Slot");
	public static final CustomItem SLOT_OFF_HAND = new EquipmentSlot(3006, EnumItemSlot.OFF_HAND, ChatColor.GRAY + "Item Slot");
	public static final CustomItem FIRST_EMPTY = new SelfAddingItem(3007, Material.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE + "First Empty", ChatColor.GRAY + "Item Slot");

	public static final CustomItem GET_ITEM_IN_MAIN_HAND = new SelfAddingItem(3008, Material.WHITE_STAINED_GLASS_PANE, ChatColor.AQUA + "Get item in Players Main Hand", ChatColor.GRAY + "Item");
	public static final CustomItem GET_ITEM_IN_OFF_HAND = new SelfAddingItem(3009, Material.WHITE_STAINED_GLASS_PANE, ChatColor.AQUA + "Get item in Players Off Hand", ChatColor.GRAY + "Item");
	public static final CustomItem EMPTY_ITEM = new SelfAddingItem(3010, Material.WHITE_STAINED_GLASS_PANE, ChatColor.AQUA + "Empty", ChatColor.GRAY + "Item");

	/* 4000 Boolean Operators */
	public static final CustomItem EQUALS = new Operator(4000, EnumOperator.EQUALS, ChatColor.GRAY + "Operator");
	public static final CustomItem NOT_EQUALS = new Operator(4001, EnumOperator.NOT_EQUALS, ChatColor.GRAY + "Operator");
	public static final CustomItem AND = new Operator(4002, EnumOperator.AND, ChatColor.GRAY + "Operator");
	public static final CustomItem OR = new Operator(4003, EnumOperator.OR, ChatColor.GRAY + "Operator");
	public static final CustomItem TRUE = new Operator(4004, EnumOperator.TRUE, ChatColor.GRAY + "Boolean");
	public static final CustomItem FALSE = new Operator(4005, EnumOperator.FALSE, ChatColor.GRAY + "Boolean");
}
