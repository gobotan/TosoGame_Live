package tosogame_live;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import toso_command.TCAppear;
import toso_command.TCBroadCaster;
import toso_command.TCClose;
import toso_command.TCDisappear;
import toso_command.TCHelp;
import toso_command.TCHide;
import toso_command.TCJoin;
import toso_command.TCLeave;
import toso_command.TCOpen;
import toso_command.TCShow;

public class TosoGame_Live extends JavaPlugin implements Listener {

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getMainScoreboard();

    /** 管理チームの名前 */
    private static final String TOSO_ADMINS = "Toso_Admins";
    /** 逃走者チームの名前 */
    private static final String TOSO_PLAYER = "Toso_Players";
    /** 牢獄チームの名前 */
    private static final String TOSO_JALL = "Toso_Jalls";
    /** ハンターチームの名前 */
    private static final String TOSO_HUNTER = "Toso_Hunters";

    /** 管理チーム */
    public static Team tosoadmins;
    /** 逃走者チーム */
    public static Team tosoplayer;
    /** 牢獄チーム */
    public static Team tosojall;
    /** ハンターチーム */
    public static Team tosohunter;

    public static String pex = "§7[§4逃走中§7]";

	@Override
	public void onDisable() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDisable();
	}

	@Override
	public void onEnable() {
		// TODO 自動生成されたメソッド・スタブ
		super.onEnable();

        // メインスコアボードを取得します。
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        // チームが既に登録されているかどうか確認し、
        // 登録されていないなら新規作成します。
        tosoadmins = board.getTeam(TOSO_ADMINS);
        if ( tosoadmins == null ) {
            tosoadmins = board.registerNewTeam(TOSO_ADMINS);
            tosoadmins.setPrefix(ChatColor.GOLD.toString());
            tosoadmins.setSuffix(ChatColor.RESET.toString());
            tosoadmins.setDisplayName("Toso_Admins");
            tosoadmins.setAllowFriendlyFire(false);
        }

        tosoplayer = board.getTeam(TOSO_PLAYER);
        if ( tosoplayer == null ) {
            tosoplayer = board.registerNewTeam(TOSO_PLAYER);
            tosoplayer.setPrefix(ChatColor.WHITE.toString());
            tosoplayer.setSuffix(ChatColor.RESET.toString());
            tosoplayer.setDisplayName("Toso_Players");
            tosoplayer.setAllowFriendlyFire(false);
        }

        // チームが既に登録されているかどうか確認し、
        // 登録されていないなら新規作成します。
        tosojall = board.getTeam(TOSO_JALL);
        if ( tosojall == null ) {
            tosojall = board.registerNewTeam(TOSO_JALL);
            tosojall.setPrefix(ChatColor.RED.toString());
            tosojall.setSuffix(ChatColor.RESET.toString());
            tosojall.setDisplayName("Toso_Jalls");
            tosojall.setAllowFriendlyFire(false);
        }

        tosohunter = board.getTeam(TOSO_HUNTER);
        if ( tosohunter == null ) {
            tosohunter = board.registerNewTeam(TOSO_HUNTER);
            tosohunter.setPrefix(ChatColor.BLACK.toString());
            tosohunter.setSuffix(ChatColor.RESET.toString());
            tosohunter.setDisplayName("Toso_Hunters");
            tosohunter.setAllowFriendlyFire(false);
        }


        getServer().getPluginManager().registerEvents(this, this);

        saveDefaultConfig();

	}

	@EventHandler
	public void Join(PlayerJoinEvent event) {

		Player player = (Player)event.getPlayer();

        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
        bookMeta.setTitle("逃走中");
        bookMeta.setAuthor("TosoGame_Live");
        bookMeta.setPages("ここにミッション等が通知されます。");
        writtenBook.setItemMeta(bookMeta);

        player.getInventory().addItem(writtenBook);


		player.sendMessage(pex + ChatColor.GOLD + "サーバーへのご参加ありがとうございます");
		player.sendMessage(pex + ChatColor.WHITE + "以下の" + ChatColor.RED + "禁止行為" + ChatColor.WHITE + "を行った場合Banとなります。ご注意ください！");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "不適切な発言、Spam行為");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "バグ、ラグの利用");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "運営の指示に従わない");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "使用禁止Mod、Hackクライアントの使用");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "連打ツール等の外部ツールの使用");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "管理者用コマンドの実行");
		player.sendMessage(pex + ChatColor.WHITE + "・" + ChatColor.RED + "生存等の位置情報の共有");
		player.sendMessage(pex + ChatColor.GOLD + "以上のルールを守って、" + ChatColor.GREEN + ChatColor.BOLD + ChatColor.UNDERLINE + "楽しく" + ChatColor.RESET + ChatColor.GOLD + "参加してください！");
		player.sendMessage(pex + "あなたを逃走者に追加しました。");

	}

	public static ItemStack giveBook(Player player) {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle("逃走中");
		meta.setAuthor("TosoGame_Live");
		meta.setPages("ここにミッション等が通知されます。");
		book.setItemMeta(meta);
		player.getInventory().addItem(book);
		player.updateInventory();
		return getBook(player, false);
	}

	private static ItemStack getBook(Player player, boolean send){
		for(ItemStack item : player.getInventory().getContents()){
			if(item != null && item.getType().equals(Material.WRITTEN_BOOK)){
				return item;
			}
		}
		if(send) return giveBook(player);
		else return null;
	}

 	@SuppressWarnings({ "unused" })
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

 		if(cmd.getName().equalsIgnoreCase("pl_reload")){
 			this.reloadConfig();
			sender.sendMessage(pex + "プラグインを再読み込みしました。");

			return true;
 		}

 		getCommand("help").setExecutor(new TCHelp());

 		getCommand("open").setExecutor(new TCOpen());

 		getCommand("close").setExecutor(new TCClose());

 		getCommand("leave").setExecutor(new TCLeave());

 		getCommand("join").setExecutor(new TCJoin());

 		getCommand("broadcaster").setExecutor(new TCBroadCaster());

 		getCommand("disappear").setExecutor(new TCDisappear());

 		getCommand("appear").setExecutor(new TCAppear());

 		getCommand("hide").setExecutor(new TCHide());

 		getCommand("show").setExecutor(new TCShow());

		// プレイヤーが「/mission」コマンドを投入した際の処理...

	    if(cmd.getName().equalsIgnoreCase("mission")) {

	    		if (args[0].equalsIgnoreCase("list")){
	    			Player player = (Player)sender;
	    			player.sendMessage(pex + ChatColor.RED + "引数が不正です。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 1: " + this.getConfig().getString("Mission1Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 2: " + this.getConfig().getString("Mission2Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 3: " + this.getConfig().getString("Mission3Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 4: " + this.getConfig().getString("Mission4Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 5: " + this.getConfig().getString("Mission5Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 6: " + this.getConfig().getString("Mission6Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 7: " + this.getConfig().getString("Mission7Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 8: " + this.getConfig().getString("Mission8Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 9: " + this.getConfig().getString("Mission9Title") + "を開始します。");
	    			player.sendMessage(pex + ChatColor.RED + "/mission 10: " + this.getConfig().getString("Mission10Title") + "を開始します。");

	    			return true;
	    		}

	    		if ( args[0].equalsIgnoreCase("1") ){
	    			for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    				ItemStack Book = getBook(bookplayer, true);
    	    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
    	    	        bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission1Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission1"));
    	    	        Book.setItemMeta(bookMeta);
    	                bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
    	                }
	    				sender.sendMessage(pex + this.getConfig().getString("Mission1Title") + "を開始しました。");
	    	        	Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
    	    	        Player player = (Player)sender;


    	    	        return true;
	    			}
	    			else if ( args[0].equalsIgnoreCase("2") ){
	    	            for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    	    	        ItemStack Book = getBook(bookplayer, true);
	    	    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	    	        bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission2Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission2"));
	    	    	        Book.setItemMeta(bookMeta);
	    	    	        bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	    	                }
	    	            	sender.sendMessage(pex + this.getConfig().getString("Mission2Title") + "を開始しました。");
	    	    	        Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
	    	    	        Player player = (Player)sender;


	    	    	        return true;
	    			}
	    		if ( args[0].equalsIgnoreCase("3") ){
	    			for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    				ItemStack Book = getBook(bookplayer, true);
	    	        		BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	        		bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission3Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission3"));
	    	        		Book.setItemMeta(bookMeta);
	                		bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	                	}
	    				sender.sendMessage(pex + this.getConfig().getString("Mission3Title") + "を開始しました。");
	    	        	Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
    	    	        Player player = (Player)sender;


    	    	        return true;
	    			}
	    			else if ( args[0].equalsIgnoreCase("4") ){
	    	            for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    	    	        ItemStack Book = getBook(bookplayer, true);
	    	    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	    	        bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission4Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission4"));
	    	    	        Book.setItemMeta(bookMeta);
	    	                bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	    	                }
	    	            	sender.sendMessage(pex + this.getConfig().getString("Mission4Title") + "を開始しました。");
	    	            	Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
	    	    	        Player player = (Player)sender;


	    	    	        return true;
	    			}
	    		if ( args[0].equalsIgnoreCase("5") ){
	    			for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    				ItemStack Book = getBook(bookplayer, true);
	    	        		BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	        		bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission5Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission5"));
	    	        		Book.setItemMeta(bookMeta);
	                		bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	                	}
	    				sender.sendMessage(pex + this.getConfig().getString("Mission5Title") + "を開始しました。");
	    	        	Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
    	    	        Player player = (Player)sender;


    	    	        return true;
	    			}
	    			else if ( args[0].equalsIgnoreCase("6") ){
	    	            for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    	    	        ItemStack Book = getBook(bookplayer, true);
	    	    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	    	        bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission6Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission6"));
	    	    	        Book.setItemMeta(bookMeta);
	    	                bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	    	                }
	    	            	sender.sendMessage(pex + this.getConfig().getString("Mission6Title") + "を開始しました。");
	    	    	        Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
	    	    	        Player player = (Player)sender;


	    	    	        return true;
	    			}
	    		if ( args[0].equalsIgnoreCase("7") ){
	    			for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    				ItemStack Book = getBook(bookplayer, true);
	    	        		BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	        		bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission7Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission7"));
	    	        		Book.setItemMeta(bookMeta);
	                		bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	                	}
	    				sender.sendMessage(pex + this.getConfig().getString("Mission7Title") + "を開始しました。");
	    	        	Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
    	    	        Player player = (Player)sender;


    	    	        return true;
	    			}
	    			else if ( args[0].equalsIgnoreCase("8") ){
	    	            for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    	    	        ItemStack Book = getBook(bookplayer, true);
	    	    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	    	        bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission8Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission8"));
	    	    	        Book.setItemMeta(bookMeta);
	    	    	        bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	    	                }
	    	            	sender.sendMessage(pex + this.getConfig().getString("Mission8Title") + "を開始しました。");
	    	    	        Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
	    	    	        Player player = (Player)sender;


	    	    	        return true;
	    			}
	    		if ( args[0].equalsIgnoreCase("9") ){
	    			for(Player bookplayer : Bukkit.getOnlinePlayers()) {

	    				ItemStack Book = getBook(bookplayer, true);
	    	        		BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	        		bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission9Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission9"));
	    	        		Book.setItemMeta(bookMeta);
	                		bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	                	}
	    				sender.sendMessage(pex + this.getConfig().getString("Mission9Title") + "を開始しました。");
	    	        	Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
    	    	        Player player = (Player)sender;


    	    	        return true;
	    			}
	    			else if ( args[0].equalsIgnoreCase("10") ){
	    	            for(Player bookplayer : Bukkit.getOnlinePlayers()) {
	    	    	        ItemStack Book = getBook(bookplayer, true);
	    	    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
	    	    	        bookMeta.addPage(ChatColor.GOLD + this.getConfig().getString("Mission10Title") + ChatColor.RESET + "\n" + "\n" + this.getConfig().getString("Mission10"));
	    	    	        Book.setItemMeta(bookMeta);
		    	    	    bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
	    	            }
	    	            	sender.sendMessage(pex + this.getConfig().getString("Mission10Title") + "を開始しました。");
	    	    	        Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
	    	    	        Player player = (Player)sender;

	    	    	        return true;
	    			}

			}

	    if(cmd.getName().equalsIgnoreCase("hint")){
            for(Player bookplayer : Bukkit.getOnlinePlayers()) {
    	        ItemStack Book = getBook(bookplayer, true);
    	        BookMeta bookMeta = (BookMeta) Book.getItemMeta();
    	        bookMeta.addPage(ChatColor.GOLD + args[0] + "のヒント" + ChatColor.RESET + "\n" + "\n" + args[1]);
    	        Book.setItemMeta(bookMeta);
	    	    bookplayer.getWorld().playSound(bookplayer.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 2, 1);
            }
            	sender.sendMessage(pex + args[0] + "のヒント" + "を通知しました。");
    	        Bukkit.broadcastMessage(pex + "ミッションが通知されました。");
    	        return true;

	    }

	    if(cmd.getName().equalsIgnoreCase("start")){
			Bukkit.getScheduler ().runTaskLater (this, () -> timer(), 0);

			return true;
		}

	    if(cmd.getName().equalsIgnoreCase("opalltp")) {
	    	Player player = (Player)sender;

				Location l = player.getLocation();
				this.getConfig().set("opalltp.x", l.getX());
				this.getConfig().set("opalltp.y", l.getY());
				this.getConfig().set("opalltp.z", l.getZ());
				this.getConfig().set("opalltp.yaw", l.getYaw());
				this.getConfig().set("opalltp.pitch", l.getPitch());

				this.saveConfig();
				sender.sendMessage(pex + ChatColor.GOLD + "オープニングゲームの場所を設定しました。");
				return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("optp")) {
	    	Player player = (Player)sender;

				Location l = player.getLocation();
				this.getConfig().set("optp.x", l.getX());
				this.getConfig().set("optp.y", l.getY());
				this.getConfig().set("optp.z", l.getZ());
				this.getConfig().set("optp.yaw", l.getYaw());
				this.getConfig().set("optp.pitch", l.getPitch());

				this.saveConfig();
				sender.sendMessage(pex + ChatColor.GOLD + "オープニングゲームで選ばれた人の場所を設定しました。");
				return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("huntertp")) {
	    	Player player = (Player)sender;

				Location l = player.getLocation();
				this.getConfig().set("huntertp.x", l.getX());
				this.getConfig().set("huntertp.y", l.getY());
				this.getConfig().set("huntertp.z", l.getZ());
				this.getConfig().set("huntertp.yaw", l.getYaw());
				this.getConfig().set("huntertp.pitch", l.getPitch());

				this.saveConfig();
				sender.sendMessage(pex + ChatColor.GOLD + "ハンターの場所を設定しました。");
				return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("jalltp")) {
	    	Player player = (Player)sender;

				Location l = player.getLocation();
				this.getConfig().set("jalltp.x", l.getX());
				this.getConfig().set("jalltp.y", l.getY());
				this.getConfig().set("jalltp.z", l.getZ());
				this.getConfig().set("jalltp.yaw", l.getYaw());
				this.getConfig().set("jalltp.pitch", l.getPitch());

				this.saveConfig();
				sender.sendMessage(pex + ChatColor.GOLD + "牢獄の場所を設定しました。");
				return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("restp")) {
	    	Player player = (Player)sender;

				Location l = player.getLocation();
				this.getConfig().set("restp.x", l.getX());
				this.getConfig().set("restp.y", l.getY());
				this.getConfig().set("restp.z", l.getZ());
				this.getConfig().set("restp.yaw", l.getYaw());
				this.getConfig().set("restp.pitch", l.getPitch());

				this.saveConfig();
				sender.sendMessage(pex + ChatColor.GOLD + "復活の場所を設定しました。");
				return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("spectp")) {
	    	Player player = (Player)sender;

				Location l = player.getLocation();
				this.getConfig().set("spectp.x", l.getX());
				this.getConfig().set("spectp.y", l.getY());
				this.getConfig().set("spectp.z", l.getZ());
				this.getConfig().set("spectp.yaw", l.getYaw());
				this.getConfig().set("spectp.pitch", l.getPitch());

				this.saveConfig();
				sender.sendMessage(pex + ChatColor.GOLD + "観覧状態の場所を設定しました。");
				return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("opgame")) {
	    	Player player = (Player) Bukkit.getOnlinePlayers();
		    		int x = getConfig().getInt("opalltp.x");
		    		int y = getConfig().getInt("opalltp.y");
		    		int z = getConfig().getInt("opalltp.z");
					int yaw = getConfig().getInt("opalltp.yaw");
					int pitch = getConfig().getInt("opalltp.pitch");

		    		player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));

			sender.sendMessage(pex + "オープニングゲームを開始しました。");
			return true;
	    }

	    if(cmd.getName().equalsIgnoreCase("spec")) {
	    	Player p = (Player)sender;
	    	if(p.getGameMode() == GameMode.ADVENTURE){
	    		int x = getConfig().getInt("spectp.x");
	    		int y = getConfig().getInt("spectp.y");
	    		int z = getConfig().getInt("spectp.z");
				int yaw = getConfig().getInt("spectp.yaw");
				int pitch = getConfig().getInt("spectp.pitch");

	    		p.teleport(new Location(p.getWorld(), x, y, z, yaw, pitch));

	    		p.setGameMode(GameMode.SPECTATOR);

	    		sender.sendMessage(pex + "観覧状態になりました。");

	    		return true;
	    	}

	    	if(p.getGameMode() == GameMode.SPECTATOR){
	    		int x = getConfig().getInt("jalltp.x");
	    		int y = getConfig().getInt("jalltp.y");
	    		int z = getConfig().getInt("jalltp.z");
				int yaw = getConfig().getInt("jalltp.yaw");
				int pitch = getConfig().getInt("jalltp.pitch");

	    		p.teleport(new Location(p.getWorld(), x, y, z, yaw, pitch));

	    		p.setGameMode(GameMode.ADVENTURE);

	    		sender.sendMessage(pex + "観覧状態から戻りました。");

	    		return true;
	    	}

	    	if(p.getGameMode() == GameMode.CREATIVE){
	    		sender.sendMessage(pex + ChatColor.RED + "クリエイティブのため観覧状態にできません。");

	    		return true;
	    	}

	    }

	    if (cmd.getName().equalsIgnoreCase("res")) {
	    	Player p = (Player)sender;

    		int x = getConfig().getInt("restp.x");
    		int y = getConfig().getInt("restp.y");
    		int z = getConfig().getInt("restp.z");
			int yaw = getConfig().getInt("restp.yaw");
			int pitch = getConfig().getInt("restp.pitch");

			p.teleport(new Location(p.getWorld(), x, y, z, yaw, pitch));

			sender.sendMessage(pex + "test");
	    }

		return false;
 	}


	public void timer() {
 		Bukkit.broadcastMessage(pex + "ゲーム開始");
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り25分"), 6000);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り20分"), 12000);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り15分"), 18000);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り10分"), 24000);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り5分"), 30000);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り4分"), 31200);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り3分"), 32400);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り2分"), 33600);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り1分"), 34800);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り15秒"), 35700);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り14秒"), 35720);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り13秒"), 35740);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り12秒"), 35760);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り11秒"), 35780);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り10秒"), 35800);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り9秒"), 35820);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り8秒"), 35840);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り7秒"), 35860);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り6秒"), 35880);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り5秒"), 35900);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り4秒"), 35920);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り3秒"), 35940);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り2秒"), 35960);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "残り1秒"), 35980);
		Bukkit.getScheduler ().runTaskLater (this, () -> Bukkit.broadcastMessage(pex + "ゲーム終了"), 36000);
 	}
}