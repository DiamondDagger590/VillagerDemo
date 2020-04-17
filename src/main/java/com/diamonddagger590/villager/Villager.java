package com.diamonddagger590.villager;

import org.bukkit.plugin.java.JavaPlugin;

public final class Villager extends JavaPlugin{
  
  public static Villager instance;
  @Override
  public void onEnable(){
    instance = this;
    // Plugin startup logic
    getCommand("demo").setExecutor(new GUICommand());
    getServer().getPluginManager().registerEvents(new GUIClick(), this);
  }
  
  @Override
  public void onDisable(){
    // Plugin shutdown logic
  }
}
