# ChristmasWish
Simple command+text based Minecraft paper plugin, should work on basically every modern version.
Has two commands, /wish <text> and /wish clear. Use /wish <text> to create a "wish" that will then be recorded in the config.yml file, recording the nickname of the sender, the message itself and the date and time. Use /wish clear to remove the wish from the config.yml.
Only one wish can be made by one player.
Since this was cobbled together pretty quickly, it uses the now deprecated ChatColor.X thing. But I cba to switch to that new adventure thing, so whatever
Also it's a bit hacky in the way how I didn't have time to have it create a config file itself, so when you slap it into your /plugins directory and after you start the server up, you will need to create "config.yml" yourself in the /ChristmasWish folder (the plugin spews out a relevant error.
