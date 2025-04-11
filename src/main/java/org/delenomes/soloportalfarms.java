package org.delenomes;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public final class soloportalfarms extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Plugin is running!");
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String commandLabel, String @NotNull [] args){
        if (cmd.getName().equalsIgnoreCase("spf")){
            if(sender.hasPermission("spf.admin")) {
                if(args[0].equalsIgnoreCase("addmob")) {
                    List<String> mobs = getConfig().getStringList("blacklisted-mobs");
                    mobs.addLast(args[1]);
                    sender.sendMessage(ChatColor.GOLD + "You added " + ChatColor.GREEN + args[1] + ChatColor.GOLD + " to the blacklist.");
                    getConfig().set("blacklisted-mobs", mobs);
                    saveConfig();
                    return true;
                }
                if(args[0].equalsIgnoreCase("removemob")) {
                    List<String> mobs = getConfig().getStringList("blacklisted-mobs");
                    mobs.remove(args[1]);
                    if (mobs.size() < getConfig().getStringList("blacklisted-mobs").size()) {
                        sender.sendMessage(ChatColor.GOLD + "You removed " + ChatColor.GREEN + args[1] + ChatColor.GOLD + " from the blacklist.");
                        getConfig().set("blacklisted-mobs", mobs);
                        saveConfig();
                        return true;
                    }
                    else {
                        sender.sendMessage(ChatColor.GOLD + "There is no " + ChatColor.GREEN + args[1] + ChatColor.GOLD + " mob in the blacklist.");
                        return true;
                    }
                }
                else{
                    sender.sendMessage(ChatColor.GOLD + "Bad syntax! Use " + ChatColor.GREEN + "/spf addmob [MOB]" + ChatColor.GOLD + " or " + ChatColor.GREEN + "/spf removemob [MOB]");
                    return true;
                }
            }
        }

        return true;


    }

    @EventHandler
    public void onEntityPortalEntry(EntityPortalEnterEvent event){
        if (event.getEntity() instanceof LivingEntity living) {
            if (getConfig().getBoolean("enable-blacklist")){
                List<String> mobs = getConfig().getStringList("blacklisted-mobs");
                for (String mob : mobs){
                    if (event.getEntityType() == EntityType.valueOf(mob)){
                        return;
                    }
                }
                living.setRemoveWhenFarAway(false);
            }
            else living.setRemoveWhenFarAway(false);

        }

    }
}
