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
package com.zandor300.simplehome;

import com.zandor300.simplehome.commands.HomeCommand;
import com.zandor300.simplehome.commands.SetHomeCommand;
import com.zandor300.simplehome.commands.SimpleHomeCommand;
import com.zandor300.zsutilities.ZSUtilities;
import com.zandor300.zsutilities.commandsystem.CommandManager;
import com.zandor300.zsutilities.config.Config;
import com.zandor300.zsutilities.utilities.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;

/**
 * @author Zandor Smith
 * @since 1.0.0
 */
public class SimpleHome extends JavaPlugin {

	private static Chat chat = new Chat("SimpleHome");
	private static SimpleHome plugin;
	private static Config config;

	public static Chat getChat() {
		return chat;
	}

	public static SimpleHome getPlugin() {
		return plugin;
	}

	public static Config getCustomConfig() {
		return config;
	}

	@Override
	public void onEnable() {
		chat.sendConsoleMessage("Setting things up...");

		config = new Config(this, "config.yml", true);
		plugin = this;
		PluginManager pm = Bukkit.getPluginManager();

		ZSUtilities.addDependency(this);

		chat.sendConsoleMessage("Starting metrics...");
		try {
			new Metrics(this).start();
			chat.sendConsoleMessage("Submitted stats to MCStats.org.");
		} catch (IOException e) {
			chat.sendConsoleMessage("Couldn't submit stats to MCStats.org...");
		}

		CommandManager cm = new CommandManager();
		cm.registerCommand(new HomeCommand(), this);
		cm.registerCommand(new SetHomeCommand(), this);
		cm.registerCommand(new SimpleHomeCommand(), this);

		chat.sendConsoleMessage("Everything is setup!");
		chat.sendConsoleMessage("Enabled.");
	}
}
