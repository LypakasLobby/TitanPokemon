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

    /**
     * TODO
     *
     * Store how many times x player has defeated y titan - DONE
     * Increase difficulty of titan for each defeat - DONE
     *      Defeating a titan more than once boosts the level of the ability they grant
     *          Max value so players don't tap W and go 45 blocks/second
     * Tick listener for making sure player always has x effect from y titan in proper situations
     *
     * Commands:
     *      to check how many times player has defeated a specified titan
     *      where specified titan spawns
     *      what species is currently being used to represent that titan
     *
     *  API:
     *      TitanSpawnEvent - DONE
     *      TitanDefeatEvent - DONE
     *      TitanAbilityEvent
     */

    public TitanPokemon() throws IOException, ObjectMappingException {

        Path dir = ConfigUtils.checkDir(Paths.get("./config/titanpokemon"));
        String[] files = new String[]{"titanpokemon.conf", "storage.conf"};
        configManager = new BasicConfigManager(files, dir, TitanPokemon.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();

    }

}
