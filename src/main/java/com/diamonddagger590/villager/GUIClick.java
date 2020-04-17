package com.diamonddagger590.villager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIClick implements Listener{
  
  @EventHandler
  public void onClick(InventoryClickEvent event){
    if(event.getInventory() instanceof MerchantInventory){
      MerchantInventory merchantInventory = (MerchantInventory) event.getInventory();
      event.setCancelled(true);
      Player player = (Player) event.getWhoClicked();
      //If the clicked slot is the accepted trade
      if(event.getSlot() == 2){
        //If it is the first trade
        if(merchantInventory.getSelectedRecipeIndex() == 0){
          player.sendMessage(Methods.color("&aYou have been healed."));
          player.setHealth(player.getMaxHealth());
          player.setFoodLevel(20);
          player.setSaturation(5);
          player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6f, 1);
        }
        //If it is the second trade
        else if(merchantInventory.getSelectedRecipeIndex() == 1){
          player.sendMessage(Methods.color("&aYou have been given a diamond chestplate"));
          ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
          ItemMeta itemMeta = itemStack.getItemMeta();
          itemMeta.setDisplayName(Methods.color("&bDiamond Chestplate"));
          itemStack.setItemMeta(itemMeta);
          player.getInventory().addItem(itemStack);
        }
        //Empty the itemstacks in the ingredient slots to prevent giving them back when a player closes the inventory
        event.getInventory().setItem(0, new ItemStack(Material.AIR));
        event.getInventory().setItem(1, new ItemStack(Material.AIR));
        player.closeInventory();
      }
    }
  }
  
  @EventHandler
  public void closeEvent(InventoryCloseEvent event){
    //Empty the itemstacks in the ingredient slots to prevent giving them back when a player closes the inventory
    if(event.getInventory() instanceof MerchantInventory){
      event.getInventory().setItem(0, new ItemStack(Material.AIR));
      event.getInventory().setItem(1, new ItemStack(Material.AIR));
    }
  }
  
  @EventHandler
  public void selectEvent(TradeSelectEvent event){
    //Set the items when a player selects an index
    if(event.getIndex() == 0){
      event.getInventory().setItem(0, GUICommand.healIngredient1);
      event.getInventory().setItem(2, GUICommand.healItem);
    }
    else if(event.getIndex() == 1){
      event.getInventory().setItem(0, GUICommand.diamondIngredient1);
      event.getInventory().setItem(2, GUICommand.diamondItem);
    }
    ((Player) event.getWhoClicked()).updateInventory();
  }
}
