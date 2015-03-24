package com.zandor300.simplehome.commands;

import com.zandor300.simplehome.SimpleHome;
import com.zandor300.zsutilities.commandsystem.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Zandor on 3/24/15.
 */
public class SimpleHomeCommand extends Command {

	public SimpleHomeCommand() {
		super("simplehome", "Show info.");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		SimpleHome.getChat().sendMessage(sender, "SimpleHome 1.0.0 by Zandor300");
	}
}
