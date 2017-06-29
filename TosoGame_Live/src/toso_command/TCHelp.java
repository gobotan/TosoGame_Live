package toso_command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TCHelp implements CommandExecutor {

	public static String pex = "§7[§4逃走中§7]";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage(pex + "運営用コマンド");
		sender.sendMessage(pex + ChatColor.GOLD + "/help" + ChatColor.WHITE + " : " + ChatColor.AQUA + "TosoGame_Liveのヘルプを表示します。(これ)");
		sender.sendMessage(pex + ChatColor.GOLD + "/pl_reload" + ChatColor.WHITE + " : " + ChatColor.AQUA + "プラグインを再読み込みします。");
		sender.sendMessage(pex + ChatColor.GOLD + "/open" + ChatColor.WHITE + " : " + ChatColor.AQUA + "サーバーを開放します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/close" + ChatColor.WHITE + " : " + ChatColor.AQUA + "サーバーを閉鎖します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/leave [Player]" + ChatColor.WHITE + " : " + ChatColor.AQUA + "運営になります。");
		sender.sendMessage(pex + ChatColor.GOLD + "/join [Player]" + ChatColor.WHITE + " : " + ChatColor.AQUA + "逃走者になります。");
		sender.sendMessage(pex + ChatColor.GOLD + "/broadcaster <add/remove> [Player]" + ChatColor.WHITE + " : " + ChatColor.AQUA + "配信者を追加/削除します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/disappear" + ChatColor.WHITE + " : " + ChatColor.AQUA + "姿を非表示にします。");
		sender.sendMessage(pex + ChatColor.GOLD + "/appear" + ChatColor.WHITE + " : " + ChatColor.AQUA + "姿を表示します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/mission <1/2/3/4/5/6/7/8/9/10>" + ChatColor.WHITE + " : " + ChatColor.AQUA + "ミッションを開始(通知)します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/hint <Title> <Text>" + ChatColor.WHITE + " : " + ChatColor.AQUA + "ヒントを通知します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/opalltp" + ChatColor.WHITE + " : " + ChatColor.AQUA + "オープニングゲームの場所を設定します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/optp" + ChatColor.WHITE + " : " + ChatColor.AQUA + "オープニングゲームで選ばれた人の場所を設定します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/huntertp" + ChatColor.WHITE + " : " + ChatColor.AQUA + "ハンターの場所を設定します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/jalltp" + ChatColor.WHITE + " : " + ChatColor.AQUA + "牢獄の場所を設定します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/restp" + ChatColor.WHITE + " : " + ChatColor.AQUA + "復活の場所を設定します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/opgame" + ChatColor.WHITE + " : " + ChatColor.AQUA + "オープニングゲームを開始します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/start" + ChatColor.WHITE + " : " + ChatColor.AQUA + "ゲームを開始します。");
		sender.sendMessage(pex + "プレイヤー用コマンド");
		sender.sendMessage(pex + ChatColor.GOLD + "/hide" + ChatColor.WHITE + " : " + ChatColor.AQUA + "周りの姿を非表示にします。");
		sender.sendMessage(pex + ChatColor.GOLD + "/show" + ChatColor.WHITE + " : " + ChatColor.AQUA + "周りの姿を表示します。");
		sender.sendMessage(pex + ChatColor.GOLD + "/spec" + ChatColor.WHITE + " : " + ChatColor.AQUA + "観覧状態になります。・観覧状態を解除します。");
		return true;
	}

}
