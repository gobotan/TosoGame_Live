package toso_command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TCShow implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player))return false;//コマンドを実行した人がプレイヤー以外なら無視

	switch(args.length){
		case 0:
			for(Player allplayer : Bukkit.getOnlinePlayers()) {
				if (allplayer.isOp() == false) {
					Player player = (Player)sender;
					player.showPlayer(allplayer);
				}
			}
			sender.sendMessage(pex + ChatColor.GREEN + "周りを表示しました。");
			break;
		case 1:
			for(Player allplayer : Bukkit.getOnlinePlayers()) {
				if (!(allplayer.isOp())) {
					Player player = Bukkit.getPlayerExact(args[0]);
					player.showPlayer(allplayer);
				}
			}
			Player player = Bukkit.getPlayerExact(args[0]);
			sender.sendMessage(pex + ChatColor.GREEN + player.getName() + "の周りを表示しました。");
			break;
		default:
			sender.sendMessage(pex + ChatColor.RED + "引数が不正です。");

			break;
	}
		return true;

    }

}
