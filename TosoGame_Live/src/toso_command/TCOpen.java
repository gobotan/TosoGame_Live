package toso_command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TCOpen implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

			Bukkit.setWhitelist(false);
			sender.sendMessage(pex + ChatColor.GREEN + "サーバーを開放しました。");

			return true;
	}
}
