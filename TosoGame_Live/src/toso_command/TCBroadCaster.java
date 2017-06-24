package toso_command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TCBroadCaster implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    String subCommand = args.length == 0 ? "" : args[0];
	    if (subCommand.equalsIgnoreCase("add")) {

	    	// addコマンド
	        Player target = Bukkit.getPlayerExact(args[1]);
	        if ( target == null ) {
				OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
				sender.sendMessage(pex + ChatColor.GOLD + offlineplayer.getName() + "を配信者に追加しました。");
				offlineplayer.setOp(true);

	            return true;
	        } else {
	        	Player player = Bukkit.getPlayerExact(args[1]);
				sender.sendMessage(pex + ChatColor.GOLD + player.getName() + "を配信者に追加しました。");
				player.setOp(true);
	            return true;
	        }

	    } else if (subCommand.equalsIgnoreCase("remove")) {

	        // removeコマンド
	        Player target = Bukkit.getPlayerExact(args[1]);
	        if ( target == null ) {
				OfflinePlayer offlinep = Bukkit.getOfflinePlayer(args[1]);
				sender.sendMessage(pex + ChatColor.GOLD + offlinep.getName() + "を配信者から削除しました。");
				offlinep.setOp(false);
	            return true;
	        } else {
	        	Player p = Bukkit.getPlayerExact(args[1]);
				sender.sendMessage(pex + ChatColor.GOLD + p.getName() + "を配信者から削除しました。");
				p.setOp(false);
	            return true;
	        }

	    } else {
	    	// それ以外(使い方)
	    	sender.sendMessage(pex + ChatColor.RED + "引数が不正です。");
	    }

		return true;
	}

}
