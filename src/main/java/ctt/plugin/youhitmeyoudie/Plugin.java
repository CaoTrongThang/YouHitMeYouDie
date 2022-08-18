package ctt.plugin.youhitmeyoudie;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ctt.plugin.youhitmeyoudie.handlers.OnHitHandler;
import ctt.plugin.youhitmeyoudie.handlers.TabCompletion;
import net.md_5.bungee.api.ChatColor;

/*
 * youhitmeyoudie java plugin
 */
public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("youhitmeyoudie");

  public void onEnable() {
    LOGGER.info(ChatColor.YELLOW + "You Hit Me You Die is on");

    new Commands(this);
    getCommand("youhitmeyoudie").setTabCompleter(new TabCompletion());
    new OnHitHandler(this);

    saveDefaultConfig();

  }

  public void onDisable() {
    LOGGER.info(ChatColor.RED + "You Hit Me You Die is off");
  }
}
