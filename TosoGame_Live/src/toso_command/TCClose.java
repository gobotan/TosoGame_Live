package toso_command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TCClose implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

			Bukkit.setWhitelist(true);
			sender.sendMessage(pex + ChatColor.GREEN + "サーバーを閉鎖しました。");

			return true;
	}

}
