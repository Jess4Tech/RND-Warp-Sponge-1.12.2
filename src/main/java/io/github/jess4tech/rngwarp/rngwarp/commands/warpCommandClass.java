package io.github.jess4tech.rngwarp.rngwarp.commands;


import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.teleport.TeleportHelperFilters;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;

import java.util.Optional;

import io.github.jess4tech.rngwarp.rngwarp.ConfigManager;

public class warpCommandClass implements CommandExecutor {

    private static Optional<Location<World>> getRandomLocation(Player player) {

        Integer minDistance = ConfigManager.getNode("Min Distance").getInt();
        Integer maxDistance = ConfigManager.getNode("Max Distance").getInt();

        Integer x = (int) (Math.random() * (double) (maxDistance - minDistance)) + minDistance;

        Integer z = (int) (Math.random() * (double) (maxDistance - minDistance)) + minDistance;

        player.getLocation().getExtent().loadChunk(player.getLocation().setPosition(new Vector3d(x, 0, z)).getChunkPosition(), true); // Load Target Chunk

        int y = player.getLocation().getExtent().getHighestYAt(x, z); // Get Highest Y

        Vector3d position = new Vector3d(x, y, z); // Create Position Vector
        Optional<Location<World>> location = Sponge.getGame().getTeleportHelper().getSafeLocation(player.getLocation().setPosition(position),

                1, 25, 1, TeleportHelperFilters.DEFAULT, new Filter()); // Get "Safe" Location


        while (!location.isPresent())

            location = getRandomLocation(player);
        return location;

    }

    public static void randomTeleport(Player player) {

        player.setLocationSafely(getRandomLocation(player).get());

    }


    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        if (args.<Player>getOne("player").isPresent()) {
            Player player = args.<Player>getOne("player").get();
            randomTeleport(player);
            src.sendMessage(Text.of(TextColors.GREEN, "Successfully teleported " + player.getName() + " to a random location!"));
            player.sendMessage(Text.of(TextColors.GREEN, "You have been teleported to a random location!"));
            return CommandResult.success();
        } else {
            src.sendMessage(Text.of(TextColors.RED, "Invalid Player (Name may be misspelled)"));
            return CommandResult.empty();
        }

    }
}
