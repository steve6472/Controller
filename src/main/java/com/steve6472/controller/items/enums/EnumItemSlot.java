package com.steve6472.controller.items.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public enum EnumItemSlot
{
	HELMET("Helmet", Material.IRON_HELMET, EquipmentSlot.HEAD),
	CHESTPLATE("Chestplate", Material.IRON_CHESTPLATE, EquipmentSlot.CHEST),
	LEGGINGS("Leggings", Material.IRON_LEGGINGS, EquipmentSlot.LEGS),
	BOOTS("Boots", Material.IRON_BOOTS, EquipmentSlot.FEET),
	MAIN_HAND("Main Hand", Material.IRON_PICKAXE, EquipmentSlot.HAND),
	OFF_HAND("Off Hand", Material.TORCH, EquipmentSlot.OFF_HAND);

	private final String name;
	private final Material material;
	private final EquipmentSlot slot;

	EnumItemSlot(String name, Material material, EquipmentSlot slot)
	{
		this.name = ChatColor.AQUA + name;
		this.material = material;
		this.slot = slot;
	}

	public String getName()
	{
		return name;
	}

	public Material getMaterial()
	{
		return material;
	}

	public EquipmentSlot getSlot()
	{
		return slot;
	}
}
