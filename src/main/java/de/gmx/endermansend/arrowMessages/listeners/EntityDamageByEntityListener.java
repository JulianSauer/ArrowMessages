package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener extends ArrowShotListener {

    public EntityDamageByEntityListener(ArrowMessages main) {
        super(main);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {

        Entity entity = e.getDamager();

        if (!(entity instanceof Arrow) || !(e.getEntity() instanceof Player))
            return;

        Arrow arrow = (Arrow) entity;
        Player target = (Player) e.getEntity();

        giveItemsToPlayer(target, arrow);

    }

}
