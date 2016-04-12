package de.gmx.endermansend.arrowMessages.listeners;

import de.gmx.endermansend.arrowMessages.main.ArrowMessages;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class PlayerPickupArrowListener extends ArrowShotListener {

    public PlayerPickupArrowListener(ArrowMessages main) {
        super(main);
    }

    @EventHandler
    public void onPlayerPickupArrow(PlayerPickupArrowEvent e) {

        Player player = e.getPlayer();
        Arrow arrow = e.getArrow();

        giveItemsToPlayer(player, arrow);

    }

}
