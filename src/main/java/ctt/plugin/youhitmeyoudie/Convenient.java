package ctt.plugin.youhitmeyoudie;

import java.util.List;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Convenient {
    public static void PrintListString(List<String> path, Player p, String targetName, ChatColor color) {
        for (String str : path) {
            if (str.contains("%player%")) {
                str.replace("%player%", targetName);
            }
            p.sendMessage(color + str);
        }
    }

    public static void PrintListString(List<String> path, Player p, ChatColor color) {
        for (String str : path) {
            p.sendMessage(color + str);
        }
    }

    public static void PrintString(String path, Player p, String targetName, ChatColor color) {
        if (path.contains("%player%"))
            p.sendMessage(color + path.replace("%player%", targetName));
        else {
            p.sendMessage(color + path);
        }
    }

    public static void PrintString(String path, Player p, ChatColor color) {
        p.sendMessage(color + path);
    }

    public static Boolean HasPermission(String permission, Player p) {
        if (p.hasPermission(permission) || p.hasPermission("youhitmeyoudie.*")) {
            return true;
        } else {
            return false;
        }
    }
}