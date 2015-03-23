/**
 * Copyright 2015 Zandor Smith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zandor300.simplehome.commands;

import com.zandor300.simplehome.SimpleHome;
import com.zandor300.zsutilities.commandsystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zandor Smith
 * @since 1.0.0
 */
public class HomeCommand extends Command {

	public HomeCommand() {
		super("home", "Teleport to home.");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player))
			return;

		Player player = (Player) sender;
		if(!SimpleHome.getPlugin().getConfig().getConfigurationSection("homes").contains(player.getUniqueId().toString())) {
			SimpleHome.getChat().sendMessage(player, "You haven't set a home!");
			return;
		}

		World world = Bukkit.getWorld(SimpleHome.getPlugin().getConfig().getString("homes." + player.getUniqueId().toString() + ".w"));
		double x = SimpleHome.getPlugin().getConfig().getDouble("homes." + player.getUniqueId().toString() + ".x");
		double y = SimpleHome.getPlugin().getConfig().getDouble("homes." + player.getUniqueId().toString() + ".y");
		double z = SimpleHome.getPlugin().getConfig().getDouble("homes." + player.getUniqueId().toString() + ".z");
		Location location = new Location(world, x, y, z);

		SimpleHome.getChat().sendMessage(sender, "Sending you to your home...");
		player.teleport(location);
	}
}
