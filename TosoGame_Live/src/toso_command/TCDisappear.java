package toso_command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TCDisappear implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player))return false;//コマンドを実行した人がプレイヤー以外なら無視

	switch(args.length){
		case 0:
			for(Player allplayer : Bukkit.getOnlinePlayers()) {
				Player player = (Player)sender;
				allplayer.hidePlayer(player);
			}
			sender.sendMessage(pex + ChatColor.GREEN + "姿を非表示にしました。");
			break;
		case 1:
			for(Player allplayer : Bukkit.getOnlinePlayers()) {
				Player player = Bukkit.getPlayerExact(args[0]);
				allplayer.hidePlayer(player);
			}
			Player player = Bukkit.getPlayerExact(args[0]);
			sender.sendMessage(pex + ChatColor.GREEN + player.getName() + "の姿を非表示にしました。");
			break;
		default:
			sender.sendMessage(pex + ChatColor.RED + "引数が不正です。");

			break;
	}
		return true;
	}
}