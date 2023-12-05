package com.lypaka.titanpokemon.Commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.lypaka.titanpokemon.ConfigGetters;
import com.lypaka.titanpokemon.Titans.Titan;
import com.lypaka.titanpokemon.Titans.TitanHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.Arrays;
import java.util.List;

public class SpawnCommand {

    public SpawnCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : TitanPokemonCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("spawn")
                                            .then(
                                                    Commands.argument("player", EntityArgument.player())
                                                            .then(
                                                                    Commands.argument("titan", StringArgumentType.word())
                                                                            .suggests(
                                                                                    (context, builder) -> ISuggestionProvider.suggest(Arrays.asList("StonyCliffTitan", "OpenSkyTitan", "LurkingSteelTitan", "QuakingEarthTitan", "FalseDragonTitan"), builder)
                                                                            )
                                                                            .executes(c -> {

                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                    if (!PermissionHandler.hasPermission(player, "titanpokemon.command.admin")) {

                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                        return 0;

                                                                                    }

                                                                                }

                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                String titanName = StringArgumentType.getString(c, "titan");

                                                                                Titan titan = TitanHandler.getFromName(titanName);
                                                                                if (titan == null) {

                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cInvalid Titan ID!"));
                                                                                    return 0;

                                                                                }

                                                                                List<String> species = titan.getSpecies();
                                                                                Pokemon pokemon = null;
                                                                                for (String s : species) {

                                                                                    if (PixelmonSpecies.has(s)) {

                                                                                        pokemon = PokemonBuilder.builder().species(s).build();
                                                                                        break;

                                                                                    }

                                                                                }

                                                                                if (pokemon == null) {

                                                                                    c.getSource().sendErrorMessage(FancyText.getFormattedText("&cNo valid Pokemon found for Titan!"));
                                                                                    return 0;

                                                                                }

                                                                                int playersHighestLevel = StorageProxy.getParty(target).getHighestLevel();
                                                                                int modifier = 15;
                                                                                int titanLevel = TitanHandler.getTitanDefeatCount(target, titan.getID());
                                                                                if (titanLevel == 2) modifier = 25;
                                                                                if (titanLevel >= 3) modifier = 50;
                                                                                int mod = modifier + playersHighestLevel;
                                                                                pokemon.setLevel(mod);

                                                                                PixelmonEntity pixelmon = pokemon.getOrSpawnPixelmon(target.world, target.getPosX(), target.getPosY(), target.getPosZ());
                                                                                if (ConfigGetters.lockToUUID) {

                                                                                    TitanHandler.lockTitan(pokemon, target);

                                                                                }
                                                                                TitanHandler.setTitanPokemon(pixelmon, titanLevel);
                                                                                target.world.addEntity(pixelmon);
                                                                                target.sendMessage(FancyText.getFormattedText(ConfigGetters.message
                                                                                        .replace("%titanID%", TitanHandler.getPrettyName(titan))
                                                                                        .replace("%pokemonName%", pokemon.getSpecies().getName())
                                                                                ), target.getUniqueID());

                                                                                return 1;

                                                                            })
                                                            )
                                            )
                            )
            );

        }

    }

}
