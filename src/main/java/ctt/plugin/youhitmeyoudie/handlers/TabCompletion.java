package ctt.plugin.youhitmeyoudie.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            List<String> tabCompleteCommands = new ArrayList<>();
            tabCompleteCommands.add("add");
            tabCompleteCommands.add("remove");
            tabCompleteCommands.add("list");
            tabCompleteCommands.add("reload");
            return tabCompleteCommands;
        } else if (args.length == 2) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player p : players) {
                playerNames.add(p.getName());
            }
            return playerNames;
        }
        return null;
    }

}
