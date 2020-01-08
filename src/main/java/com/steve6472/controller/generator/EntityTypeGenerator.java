package com.steve6472.controller.generator;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**********************
 * Created by steve6472 (Mirek Jozefek)
 * On date: 06.01.2020
 * Project: Controller
 *
 ***********************/
public class EntityTypeGenerator
{
	public static void main(String[] args)
	{
		int startingId = 3100;

		for (EntityType e : EntityType.values())
		{
			String spawnName = e.name() + "_SPAWN_EGG";
			if (Material.getMaterial(spawnName) != null)
			{
				String name = e.name().substring(0, 1) + e.name().toLowerCase().replace("_", " ").substring(1);
				System.out.println(String.format("public static final CustomItem ENTITY_%s = new GetEntityType(%d, %s, \"%s\");", e.name(), startingId++, "Material." + spawnName, name));
			}
		}
	}
}
