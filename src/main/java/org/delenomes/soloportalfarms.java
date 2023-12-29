package org.delenomes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.LivingEntity;


public final class soloportalfarms extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Plugin is running!");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onEntityPortalEntry(EntityPortalEnterEvent event){
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) event.getEntity();
            living.setRemoveWhenFarAway(false);

        }

    }
}
