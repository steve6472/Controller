package com.steve6472.controller.items.enums;

import org.bukkit.ChatColor;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 03.01.2020
 * Project: AdvancedGuiTest
 *
 ***********************/
public enum EnumOperator
{
	EQUALS("Equals"), NOT_EQUALS("Not Equals"), AND("And"), OR("Or"), TRUE("True"), FALSE("False");

	private final String name;

	EnumOperator(String name)
	{
		this.name = ChatColor.AQUA + name;
	}

	public String getName()
	{
		return name;
	}
}
