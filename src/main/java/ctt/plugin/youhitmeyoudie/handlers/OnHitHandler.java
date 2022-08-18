package ctt.plugin.youhitmeyoudie.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.*;
import ctt.plugin.youhitmeyoudie.Plugin;

public class OnHitHandler implements Listener {
    Plugin plugin;

    public OnHitHandler(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHitHander(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player) {
                boolean isInTheList = plugin.getConfig().getStringList("PlayersUsingYouKillMeYouDie")
                        .contains(((Player) e.getEntity()).getUniqueId().toString());
                if (isInTheList) {
                    ((Player) e.getDamager()).setHealth(0);
                }
            } else if (e.getDamager() instanceof Projectile) {

                try {
                    LivingEntity lEntity = (LivingEntity) ((Projectile) e.getDamager()).getShooter();
                    lEntity.setHealth(0);
                } catch (Exception exception) {
                    System.out.println("[You Hit Me You Die] " + e.getEntityType() + " is not count.");
                }

            } else if (e.getDamager() instanceof LivingEntity) {
                LivingEntity lEntity = (LivingEntity) (e.getDamager());
                lEntity.setHealth(0);
            } else {
                System.out.println(e.getEntityType() + " is not handled.");
            }
        }
    }
}