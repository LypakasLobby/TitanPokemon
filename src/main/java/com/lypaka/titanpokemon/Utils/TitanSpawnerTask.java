package com.lypaka.titanpokemon.Utils;

import com.lypaka.lypakautils.Listeners.JoinListener;
import com.lypaka.titanpokemon.ConfigGetters;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class TitanSpawnerTask {

    public static Timer timer;

    public static void startTimer() {

        if (timer != null) {

            timer.cancel();

        }

        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                for (Map.Entry<UUID, ServerPlayerEntity> entry : JoinListener.playerMap.entrySet()) {

                    ServerPlayerEntity player = entry.getValue();
                    if (ConfigGetters.ignoreNonSurvival) {

                        if (player.isCreative() || player.isSpectator()) continue;

                    }

                    if (RandomHelper.getRandomChance(ConfigGetters.titanSpawnChance)) {



                    }

                }

            }

        }, 0, ConfigGetters.titanSpawnInterval);

    }

}
