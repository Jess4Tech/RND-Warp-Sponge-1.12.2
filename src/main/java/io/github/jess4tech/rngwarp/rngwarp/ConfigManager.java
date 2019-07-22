package io.github.jess4tech.rngwarp.rngwarp;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    private static Path dir, config;
    public static CommentedConfigurationNode confNode;
    private static ConfigurationLoader<CommentedConfigurationNode> confLoad;
    public static final String CFG = "defaultConfig.conf";

    public static void setup(Path folder) {
        dir = folder;
        config = dir.resolve(CFG);
    }
    public static void load() {
        try {
            if(!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RNGWarp.getContainer().getAsset(CFG).get().copyToFile(config, false, true);
            confLoad = HoconConfigurationLoader.builder().setPath(config).build();
            confNode = confLoad.load();
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try  {
            confLoad.save(confNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static CommentedConfigurationNode getNode(Object... node) {
        return confNode.getNode(node);
    }
}