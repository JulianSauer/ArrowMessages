package de.gmx.endermansend.arrowMessages.listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {

        Projectile projectile = e.getEntity();
        if (!(projectile instanceof Arrow))
            return;

        if (projectile.getMetadata("Message") == null)
            return;

        World world = projectile.getWorld();
        Location location = projectile.getLocation();
        world.spawnParticle(Particle.SPELL_WITCH, location, 5);
        world.playSound(location, Sound.BLOCK_NOTE_PLING, 5, 1);

    }

}
