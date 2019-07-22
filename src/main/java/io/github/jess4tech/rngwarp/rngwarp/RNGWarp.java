package io.github.jess4tech.rngwarp.rngwarp;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.Game;
import java.nio.file.Path;

import io.github.jess4tech.rngwarp.rngwarp.commands.warpCommandClass;

@Plugin(
        id = "rndwarp",
        name = "RNDWarp",
        description = "Warps the player it was used on a random amount of blocks away",
        url = "jess4tech.github.io",
        authors = {
                "JessRed"
        }
)

public class RNGWarp
{
    public static RNGWarp instance;
    @Inject
    @ConfigDir(sharedRoot = false)
    private Path dir;

    @Inject
    private PluginContainer container;

    @Inject
    private Logger log;

    @Inject
    private Game game;

    public static PluginContainer getContainer() {
        return instance.container;
    }

    @Listener
    public void preInit(GamePreInitializationEvent e) {
        instance = this;
        ConfigManager.setup(dir);
        ConfigManager.load();
        CommandSpec rndWarp = CommandSpec.builder()
                .description(Text.of(TextColors.GRAY, "Warps the player it was used on a random amount of blocks away"))
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.player(Text.of("player")))
                )
                .executor(new warpCommandClass())
                .permission("rndwarp.commands.warp")
                .build();
        Sponge.getCommandManager().register(this, rndWarp, "wilds");
        log.info("RNDWarp intitiated");
    }
}
