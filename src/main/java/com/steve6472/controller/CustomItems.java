package com.steve6472.controller;

import com.steve6472.controller.guis.EntityTypeGui;
import com.steve6472.controller.items.*;
import com.steve6472.controller.items.enums.EnumItemSlot;
import com.steve6472.controller.items.enums.EnumOperator;
import com.steve6472.controller.items.events.*;
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
	/* 1111 - 1199 - System Items */
	public static final CustomItem NULL = new NormalCustomItem(1111, Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ");
	public static final CustomItem OUT_OF_BOUNDS = new NormalCustomItem(1112, Material.BLACK_STAINED_GLASS_PANE, ChatColor.DARK_RED + "OUT OF BOUNDS");
	public static final CustomItem CONTROLLER = new NormalCustomItem(1113, Material.NETHER_STAR, ChatColor.GOLD + "Controller");

	public static final CustomItem UP = new MoveGui(1114, "Up", 0, -1);
	public static final CustomItem DOWN = new MoveGui(1115, "Down", 0, 1);
	public static final CustomItem LEFT = new MoveGui(1116, "Left", -1, 0);
	public static final CustomItem RIGHT = new MoveGui(1117, "Right", 1, 0);
	public static final CustomItem SAVE = new Save(1118);
	public static final CustomItem SAVE_NAME = new SaveName(1119);
	public static final CustomItem NEXT = new NormalCustomItem(1120, Material.ARROW, "Next Page");
	public static final CustomItem PREVIOUS = new NormalCustomItem(1121, Material.ARROW, "Previous Page");

	/* 2000 - 2499 - Statements */
	public static final CustomItem STATEMENT = new Statement(2001);
	public static final CustomItem MESSAGE = new Message(2002);
	public static final CustomItem MESSAGE_DELAY = new Delay(2003, true);
	public static final CustomItem DELAY = new Delay(2004, false);
	public static final CustomItem IF = new If(2005);
	public static final CustomItem SET_ITEM = new SetItem(2006);
	public static final CustomItem WAIT_FOR_EVENT = new WaitFor(2007);

	public static final CustomItem ELSE = new SelfAddingItem(2497, Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "Else");
	public static final CustomItem END = new NormalCustomItem(2498, Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "End");
	public static final CustomItem DO = new SelfAddingItem(2499, Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "Do");

	/* 2500 - 2999 - Required Items */
	//TODO: Replace AddBooleanOperator with OpenGui
	public static final CustomItem ADD_BOOLEAN = new AddBooleanOperator(2500, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Click to add Boolean (required)");
	public static final CustomItem BOOLEAN_OPERATOR_OR_DO = new AddBooleanOperator(2501, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Click to add Boolean Operator or Do (required)");
	public static final CustomItem ADD_END = new AddEnd(2502);
	public static final CustomItem ADD_ITEM = new SelfAddingItem(2503, Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Add Item", ChatColor.WHITE + "Click here with item");
	public static final CustomItem ADD_EVENT = new AddEvent(2504);
	public static final CustomItem ENTITY_TYPE = new OpenGui(2505, Material.WHITE_STAINED_GLASS_PANE, ChatColor.AQUA + "Get Last Interacted Entity Type", EntityTypeGui.class, ChatColor.GRAY + "Entity Type");
	public static final CustomItem ADD_ELSE = new AddElse(2506);

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

	public static final CustomItem EVENT_BREAK_BLOCK = new EventBreakBlock(3011);
	public static final CustomItem EVENT_PLACE_BLOCK = new EventPlaceBlock(3012);
	public static final CustomItem EVENT_DROP_ITEM = new EventDropItem(3013);
	public static final CustomItem EVENT_PICKUP_ITEM = new EventPickUpItem(3014);
	public static final CustomItem EVENT_CONSUME_ITEM = new EventConsumeItem(3015);
	public static final CustomItem EVENT_FISH = new EventFish(3016);

	public static final CustomItem GET_EVENT_BREAK_BLOCK = new GetItem(3017, "Get Last Broken Block", "Block");
	public static final CustomItem GET_EVENT_PLACE_BLOCK = new GetItem(3018, "Get Last Placed Block", "Block");
	public static final CustomItem GET_EVENT_DROP_ITEM = new GetItem(3019, "Get Last Dropped Item", "Item");
	public static final CustomItem GET_EVENT_PICKUP_ITEM = new GetItem(3020, "Get Last Picked Up Item", "Item");
	public static final CustomItem GET_EVENT_CONSUME_ITEM = new GetItem(3021, "Get Last Consumed Item", "Item");
	public static final CustomItem GET_EVENT_FISH_ITEM = new GetItem(3022, "Get Last Fished Item", "Item");
	public static final CustomItem GET_EVENT_FISH_STATE = new GetItem(3023, "Get Last Fish State", "Fish State");

	public static final CustomItem EVENT_INTERACT_ENTITY = new EventInteractEntity(3024);
	public static final CustomItem GET_EVENT_INTERACT_ENTITY = new GetItem(3025, "Get Last Interacted Entity Type", "Entity Type");

	/* Entity Types */
	public static final CustomItem ENTITY_ELDER_GUARDIAN = new GetEntityType(3100, Material.ELDER_GUARDIAN_SPAWN_EGG, "Elder guardian");
	public static final CustomItem ENTITY_WITHER_SKELETON = new GetEntityType(3101, Material.WITHER_SKELETON_SPAWN_EGG, "Wither skeleton");
	public static final CustomItem ENTITY_STRAY = new GetEntityType(3102, Material.STRAY_SPAWN_EGG, "Stray");
	public static final CustomItem ENTITY_HUSK = new GetEntityType(3103, Material.HUSK_SPAWN_EGG, "Husk");
	public static final CustomItem ENTITY_ZOMBIE_VILLAGER = new GetEntityType(3104, Material.ZOMBIE_VILLAGER_SPAWN_EGG, "Zombie villager");
	public static final CustomItem ENTITY_SKELETON_HORSE = new GetEntityType(3105, Material.SKELETON_HORSE_SPAWN_EGG, "Skeleton horse");
	public static final CustomItem ENTITY_ZOMBIE_HORSE = new GetEntityType(3106, Material.ZOMBIE_HORSE_SPAWN_EGG, "Zombie horse");
	public static final CustomItem ENTITY_DONKEY = new GetEntityType(3107, Material.DONKEY_SPAWN_EGG, "Donkey");
	public static final CustomItem ENTITY_MULE = new GetEntityType(3108, Material.MULE_SPAWN_EGG, "Mule");
	public static final CustomItem ENTITY_EVOKER = new GetEntityType(3109, Material.EVOKER_SPAWN_EGG, "Evoker");
	public static final CustomItem ENTITY_VEX = new GetEntityType(3110, Material.VEX_SPAWN_EGG, "Vex");
	public static final CustomItem ENTITY_VINDICATOR = new GetEntityType(3111, Material.VINDICATOR_SPAWN_EGG, "Vindicator");
	public static final CustomItem ENTITY_CREEPER = new GetEntityType(3112, Material.CREEPER_SPAWN_EGG, "Creeper");
	public static final CustomItem ENTITY_SKELETON = new GetEntityType(3113, Material.SKELETON_SPAWN_EGG, "Skeleton");
	public static final CustomItem ENTITY_SPIDER = new GetEntityType(3114, Material.SPIDER_SPAWN_EGG, "Spider");
	public static final CustomItem ENTITY_ZOMBIE = new GetEntityType(3115, Material.ZOMBIE_SPAWN_EGG, "Zombie");
	public static final CustomItem ENTITY_SLIME = new GetEntityType(3116, Material.SLIME_SPAWN_EGG, "Slime");
	public static final CustomItem ENTITY_GHAST = new GetEntityType(3117, Material.GHAST_SPAWN_EGG, "Ghast");
	public static final CustomItem ENTITY_ENDERMAN = new GetEntityType(3118, Material.ENDERMAN_SPAWN_EGG, "Enderman");
	public static final CustomItem ENTITY_CAVE_SPIDER = new GetEntityType(3119, Material.CAVE_SPIDER_SPAWN_EGG, "Cave spider");
	public static final CustomItem ENTITY_SILVERFISH = new GetEntityType(3120, Material.SILVERFISH_SPAWN_EGG, "Silverfish");
	public static final CustomItem ENTITY_BLAZE = new GetEntityType(3121, Material.BLAZE_SPAWN_EGG, "Blaze");
	public static final CustomItem ENTITY_MAGMA_CUBE = new GetEntityType(3122, Material.MAGMA_CUBE_SPAWN_EGG, "Magma cube");
	public static final CustomItem ENTITY_BAT = new GetEntityType(3123, Material.BAT_SPAWN_EGG, "Bat");
	public static final CustomItem ENTITY_WITCH = new GetEntityType(3124, Material.WITCH_SPAWN_EGG, "Witch");
	public static final CustomItem ENTITY_ENDERMITE = new GetEntityType(3125, Material.ENDERMITE_SPAWN_EGG, "Endermite");
	public static final CustomItem ENTITY_GUARDIAN = new GetEntityType(3126, Material.GUARDIAN_SPAWN_EGG, "Guardian");
	public static final CustomItem ENTITY_SHULKER = new GetEntityType(3127, Material.SHULKER_SPAWN_EGG, "Shulker");
	public static final CustomItem ENTITY_PIG = new GetEntityType(3128, Material.PIG_SPAWN_EGG, "Pig");
	public static final CustomItem ENTITY_SHEEP = new GetEntityType(3129, Material.SHEEP_SPAWN_EGG, "Sheep");
	public static final CustomItem ENTITY_COW = new GetEntityType(3130, Material.COW_SPAWN_EGG, "Cow");
	public static final CustomItem ENTITY_CHICKEN = new GetEntityType(3131, Material.CHICKEN_SPAWN_EGG, "Chicken");
	public static final CustomItem ENTITY_SQUID = new GetEntityType(3132, Material.SQUID_SPAWN_EGG, "Squid");
	public static final CustomItem ENTITY_WOLF = new GetEntityType(3133, Material.WOLF_SPAWN_EGG, "Wolf");
	public static final CustomItem ENTITY_OCELOT = new GetEntityType(3134, Material.OCELOT_SPAWN_EGG, "Ocelot");
	public static final CustomItem ENTITY_HORSE = new GetEntityType(3135, Material.HORSE_SPAWN_EGG, "Horse");
	public static final CustomItem ENTITY_RABBIT = new GetEntityType(3136, Material.RABBIT_SPAWN_EGG, "Rabbit");
	public static final CustomItem ENTITY_POLAR_BEAR = new GetEntityType(3137, Material.POLAR_BEAR_SPAWN_EGG, "Polar bear");
	public static final CustomItem ENTITY_LLAMA = new GetEntityType(3138, Material.LLAMA_SPAWN_EGG, "Llama");
	public static final CustomItem ENTITY_PARROT = new GetEntityType(3139, Material.PARROT_SPAWN_EGG, "Parrot");
	public static final CustomItem ENTITY_VILLAGER = new GetEntityType(3140, Material.VILLAGER_SPAWN_EGG, "Villager");
	public static final CustomItem ENTITY_TURTLE = new GetEntityType(3141, Material.TURTLE_SPAWN_EGG, "Turtle");
	public static final CustomItem ENTITY_PHANTOM = new GetEntityType(3142, Material.PHANTOM_SPAWN_EGG, "Phantom");
	public static final CustomItem ENTITY_COD = new GetEntityType(3143, Material.COD_SPAWN_EGG, "Cod");
	public static final CustomItem ENTITY_SALMON = new GetEntityType(3144, Material.SALMON_SPAWN_EGG, "Salmon");
	public static final CustomItem ENTITY_PUFFERFISH = new GetEntityType(3145, Material.PUFFERFISH_SPAWN_EGG, "Pufferfish");
	public static final CustomItem ENTITY_TROPICAL_FISH = new GetEntityType(3146, Material.TROPICAL_FISH_SPAWN_EGG, "Tropical fish");
	public static final CustomItem ENTITY_DROWNED = new GetEntityType(3147, Material.DROWNED_SPAWN_EGG, "Drowned");
	public static final CustomItem ENTITY_DOLPHIN = new GetEntityType(3148, Material.DOLPHIN_SPAWN_EGG, "Dolphin");
	public static final CustomItem ENTITY_CAT = new GetEntityType(3149, Material.CAT_SPAWN_EGG, "Cat");
	public static final CustomItem ENTITY_PANDA = new GetEntityType(3150, Material.PANDA_SPAWN_EGG, "Panda");
	public static final CustomItem ENTITY_PILLAGER = new GetEntityType(3151, Material.PILLAGER_SPAWN_EGG, "Pillager");
	public static final CustomItem ENTITY_RAVAGER = new GetEntityType(3152, Material.RAVAGER_SPAWN_EGG, "Ravager");
	public static final CustomItem ENTITY_TRADER_LLAMA = new GetEntityType(3153, Material.TRADER_LLAMA_SPAWN_EGG, "Trader llama");
	public static final CustomItem ENTITY_WANDERING_TRADER = new GetEntityType(3154, Material.WANDERING_TRADER_SPAWN_EGG, "Wandering trader");
	public static final CustomItem ENTITY_FOX = new GetEntityType(3155, Material.FOX_SPAWN_EGG, "Fox");
	public static final CustomItem ENTITY_BEE = new GetEntityType(3156, Material.BEE_SPAWN_EGG, "Bee");

	/* 4000 Boolean Operators */
	public static final CustomItem EQUALS = new Operator(4000, EnumOperator.EQUALS, ChatColor.GRAY + "Operator");
	public static final CustomItem NOT_EQUALS = new Operator(4001, EnumOperator.NOT_EQUALS, ChatColor.GRAY + "Operator");
	public static final CustomItem AND = new Operator(4002, EnumOperator.AND, ChatColor.GRAY + "Operator");
	public static final CustomItem OR = new Operator(4003, EnumOperator.OR, ChatColor.GRAY + "Operator");
	public static final CustomItem TRUE = new Operator(4004, EnumOperator.TRUE, ChatColor.GRAY + "Boolean");
	public static final CustomItem FALSE = new Operator(4005, EnumOperator.FALSE, ChatColor.GRAY + "Boolean");
}
