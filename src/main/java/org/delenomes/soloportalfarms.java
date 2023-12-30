package org.delenomes;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.LivingEntity;

import java.util.List;


public final class soloportalfarms extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Plugin is running!");
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();

    }

    @EventHandler
    public void onEntityPortalEntry(EntityPortalEnterEvent event){
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) event.getEntity();
            if (getConfig().getBoolean("enable-blacklist")){
                List<String> mobs = getConfig().getStringList("blacklisted-mobs");
                for (String mob : mobs){

                    if (event.getEntityType() == EntityType.valueOf(mob)){
                        return;
                    }
                    else{
                        living.setRemoveWhenFarAway(false);
                    }
                }
            }
            else {
                living.setRemoveWhenFarAway(false);
            }

        }

    }
}
