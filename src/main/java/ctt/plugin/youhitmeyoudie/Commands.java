package ctt.plugin.youhitmeyoudie;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {
    Plugin plugin;

    List<String> HelpMessages;
    List<String> PlayersUsingYouKillMeYouDie;

    public Commands(Plugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("youhitmeyoudie").setExecutor(this);
        PlayersUsingYouKillMeYouDie = plugin.getConfig().getStringList("PlayersUsingYouKillMeYouDie");
        HelpMessages = plugin.getConfig().getStringList("HelpMessage");

    }

    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label,
            String[] args) {
        Player player = (Player) sender;

        String cmd = "";
        if (args.length > 0) {
            cmd = args[0];
        } else if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            Convenient.PrintListString(HelpMessages, player, ChatColor.YELLOW);
            return false;
        }

        if (args.length == 1) {
            if (cmd.equalsIgnoreCase("list")) {
                if (Convenient.HasPermission("youhitmeyoudie.list", player)) {
                    for (String str : PlayersUsingYouKillMeYouDie) {
                        player.sendMessage(ChatColor.YELLOW + "- " + Bukkit.getPlayer(UUID.fromString(str)).getName());
                    }
                    return true;
                } else {
                    Convenient.PrintString(plugin.getConfig().getString("DontHavePermission"), player,
                            ChatColor.RED);
                    return false;
                }
            } else if (cmd.equalsIgnoreCase("reload")) {
                if (Convenient.HasPermission("youhitmeyoudie.reload", player)) {
                    plugin.reloadConfig();
                    Convenient.PrintString(plugin.getConfig().getString("ReloadMessage"), player,
                            ChatColor.GREEN);
                    return true;
                } else {
                    Convenient.PrintString(plugin.getConfig().getString("DontHavePermission"), player,
                            ChatColor.RED);
                    return false;
                }
            }
        } else if (args.length == 2 && Bukkit.getPlayer(args[1]) != null) {
            if (cmd.equalsIgnoreCase("add")) {
                if (Convenient.HasPermission("youhitmeyoudie.add", player)) {
                    if (!Bukkit.getPlayer(args[1]).isOnline()) {
                        Convenient.PrintString(plugin.getConfig().getString("NotOnlineMessage"), player, args[1],
                                ChatColor.RED);
                        return false;
                    } else {
                        if (PlayersUsingYouKillMeYouDie.contains(Bukkit.getPlayer(args[1]).getUniqueId().toString())) {
                            Convenient.PrintString(plugin.getConfig().getString("InListMessage"), player, args[1],
                                    ChatColor.RED);
                            return false;
                        }
                        PlayersUsingYouKillMeYouDie.add(Bukkit.getPlayer(args[1]).getUniqueId().toString());
                        plugin.getConfig().set("PlayersUsingYouKillMeYouDie", PlayersUsingYouKillMeYouDie);
                        plugin.saveConfig();
                        Convenient.PrintString(plugin.getConfig().getString("AddMessage"), player, args[1],
                                ChatColor.GREEN);
                        return true;
                    }
                } else {
                    Convenient.PrintString(plugin.getConfig().getString("DontHavePermission"), player,
                            ChatColor.RED);
                    return false;
                }

            } else if (cmd.equalsIgnoreCase("remove")) {
                if (Convenient.HasPermission("youhitmeyoudie.add", player)) {
                    if (!Bukkit.getPlayer(args[1]).isOnline()) {
                        Convenient.PrintString(plugin.getConfig().getString("NotOnlineMessage"), player, args[1],
                                ChatColor.RED);
                        return false;
                    } else {
                        if (!PlayersUsingYouKillMeYouDie.contains(Bukkit.getPlayer(args[1]).getUniqueId().toString())) {
                            Convenient.PrintString(plugin.getConfig().getString("NotInListMessage"), player, args[1],
                                    ChatColor.RED);
                            return false;
                        }
                        PlayersUsingYouKillMeYouDie.remove(Bukkit.getPlayer(args[1]).getUniqueId().toString());
                        plugin.getConfig().set("PlayersUsingYouKillMeYouDie", PlayersUsingYouKillMeYouDie);
                        plugin.saveConfig();
                        Convenient.PrintString(plugin.getConfig().getString("RemoveMessage"), player, args[1],
                                ChatColor.GREEN);
                        return true;
                    }
                } else {
                    Convenient.PrintString(plugin.getConfig().getString("DontHavePermission"), player,
                            ChatColor.RED);
                    return false;
                }

            }
        } else if (Bukkit.getPlayer(args[1]) == null) {
            Convenient.PrintString(plugin.getConfig().getString("NullPlayerMessage"),
                    player, args[1],
                    ChatColor.RED);
        } else {

        }
        return false;
    }
}
