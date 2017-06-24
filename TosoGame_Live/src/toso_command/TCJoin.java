package toso_command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tosogame_live.TosoGame_Live;

public class TCJoin implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) return false;

		Player player = (Player)sender;

		switch(args.length){
			case 0:
				sender.sendMessage(pex + "あなたを逃走者に追加しました。");
				sender.sendMessage(pex + ChatColor.GREEN + "ゲームに参加しました。");
				Bukkit.broadcastMessage(pex +  ChatColor.GREEN + player.getName() + "がゲームに参加しました。");

				TosoGame_Live.tosoplayer.addPlayer(player);

				player.setGameMode(GameMode.ADVENTURE);

				break;

			case 1:
				Player p = Bukkit.getPlayerExact(args[0]);
				sender.sendMessage(pex + p.getName() + "を逃走者に追加しました。");
				sender.sendMessage(pex + ChatColor.GREEN + p.getName() + "ゲームに参加しました。");
				Bukkit.broadcastMessage(pex + ChatColor.GREEN + p.getName() + "がゲームに参加しました。");

				TosoGame_Live.tosoplayer.addPlayer(p);

				p.setGameMode(GameMode.ADVENTURE);

				break;

			default:
				sender.sendMessage(pex + ChatColor.RED + "引数が不正です。");

				break;
		}
		return true;
	}

}
