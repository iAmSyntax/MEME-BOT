package com.example.jda.Bot;


import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;


public class MemeCommands extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        JSONParser parser = new JSONParser();

        String postLink = "";
        String title = "";
        String url = "";


        if (args[0].equalsIgnoreCase("!memes")) {
            try {
                URL memeURL = new URL("https://meme-api.herokuapp.com/gimme"); // the meme api
                BufferedReader reader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));

                String lines;
                // passes meme in json format
                while ((lines = reader.readLine()) != null) {
                    JSONArray array = new JSONArray();
                    array.add(parser.parse(lines));
                    for (Object o : array) {
                        JSONObject j = (JSONObject) o;
                        postLink = (String) j.get("postLink");
                        title = (String) j.get("title");
                        url = (String) j.get("url");

                    }
                }
                reader.close();

// sends the meme as an embed
                EmbedBuilder memer = new EmbedBuilder();
                memer.setTitle(title, postLink);
                memer.setImage(url);
                memer.setColor(Color.RED);
                event.getChannel().sendMessage(memer.build()).queue();


            } catch (Exception e) {
                event.getChannel().sendMessage("Something went wrong , please try again later").queue();
                e.printStackTrace();
            }
        }

    }
}
