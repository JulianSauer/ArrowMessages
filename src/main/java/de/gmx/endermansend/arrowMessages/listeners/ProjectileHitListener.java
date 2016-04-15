package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileHitListener implements Listener {

    private ArrowMessages main;

    private boolean spectralGlowing;

    public ProjectileHitListener(ArrowMessages main) {
        this.main = main;
        this.spectralGlowing = main.getConfigHandler().get.spectralGlowing();
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {

        Projectile projectile = e.getEntity();
        if (!(projectile instanceof Arrow))
            return;

        if (projectile.getMetadata("Message").isEmpty())
            return;

        projectile.setGlowing(spectralGlowing);

        World world = projectile.getWorld();
        Location location = projectile.getLocation();
        world.spawnParticle(Particle.SPELL_WITCH, location, 30);
        world.playSound(location, Sound.ENTITY_ARROW_HIT, 1.5f, 1);

        (new ParticleEffects(projectile, world)).runTaskTimer(main, 0L, 2L);

    }

    /**
     * Adds an effect to an entity.
     */
    class ParticleEffects extends BukkitRunnable {

        private Projectile projectile;
        private World world;

        public ParticleEffects(Projectile projectile, World world) {
            this.projectile = projectile;
            this.world = world;
        }

        public void run() {
            if (!projectile.isDead())
                world.spawnParticle(Particle.SPELL_WITCH, projectile.getLocation(), 1);
            else
                this.cancel();
        }

    }

}
