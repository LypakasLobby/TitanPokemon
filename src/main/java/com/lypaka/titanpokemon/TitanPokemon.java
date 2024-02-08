package com.lypaka.titanpokemon;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraftforge.fml.common.Mod;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("titanpokemon")
public class TitanPokemon {

    public static final String MOD_ID = "titanpokemon";
    public static final String MOD_NAME = "TitanPokemon";
    public static final Logger logger = LogManager.getLogger(MOD_NAME);
    public static BasicConfigManager configManager;

    public TitanPokemon() throws IOException, ObjectMappingException {

        logger.info("This mod was written by me (Lypaka) and is available for free - and is open sourced on my GitHub! If you PURCHASED this mod from someone, please report them to me in my dev Discord: https://discord.gg/6kKcz2kWgF");
        Path dir = ConfigUtils.checkDir(Paths.get("./config/titanpokemon"));
        String[] files = new String[]{"titanpokemon.conf", "storage.conf", "blacklist.conf"};
        configManager = new BasicConfigManager(files, dir, TitanPokemon.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();

    }

}
