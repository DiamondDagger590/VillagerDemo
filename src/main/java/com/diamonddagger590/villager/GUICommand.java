package com.diamonddagger590.villager;

import com.sun.webkit.dom.WheelEventImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GUICommand implements CommandExecutor{
  
  public static ItemStack healItem;
  public static ItemStack healIngredient1;
  public static ItemStack diamondItem;
  public static ItemStack diamondIngredient1;
  
  /**
   * Executes the given command, returning its success.
   * <br>
   * If false is returned, then the "usage" plugin.yml entry for this command
   * (if defined) will be sent to the player.
   *
   * @param sender  Source of the command
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    Passed command arguments
   * @return true if a valid command, otherwise false
   */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
  
    if(!(sender instanceof Player)){
      return true;
    }
    Player player = (Player) sender;
    
    Merchant merchant = Bukkit.createMerchant(Methods.color("&5Demo"));
    List<MerchantRecipe> merchantRecipes = new ArrayList<>();
    
    if(healItem == null){
      //Heal Command
      healItem = new ItemStack(Material.GLISTERING_MELON_SLICE);
      ItemMeta healMeta = healItem.getItemMeta();
      healMeta.setDisplayName(Methods.color("&4Heals you"));
      List<String> healLore = new ArrayList<>();
      healLore.add(Methods.color("&cThis option will heal you completely!"));
      healMeta.setLore(healLore);
      healItem.setItemMeta(healMeta);
    }
    MerchantRecipe healRecipe = new MerchantRecipe(healItem, 999);
    
    if(healIngredient1 == null){
      healIngredient1 = new ItemStack(Material.GOLD_INGOT);
      ItemMeta healIngredientMeta = healIngredient1.getItemMeta();
      healIngredientMeta.setDisplayName(Methods.color("&eCost: &a$100"));
      List<String> healIngredientLore = new ArrayList<>();
      healIngredientLore.add(Methods.color("&aThis option costs $100"));
      healIngredientMeta.setLore(healIngredientLore);
      healIngredient1.setItemMeta(healIngredientMeta);
    }
    
    healRecipe.addIngredient(healIngredient1);
    merchantRecipes.add(healRecipe);
  
    //Diamond Command
    if(diamondItem == null){
      diamondItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
      ItemMeta diamondMeta = diamondItem.getItemMeta();
      diamondMeta.setDisplayName(Methods.color("&4Gives you a diamond chestplate"));
      List<String> diamondItemLore = new ArrayList<>();
      diamondItemLore.add(Methods.color("&cPurchases a diamond chestplate!"));
      diamondMeta.setLore(diamondItemLore);
      diamondItem.setItemMeta(diamondMeta);
    }
    
    MerchantRecipe diamondChestPlateRecipe = new MerchantRecipe(diamondItem, 999);
  
    if(diamondIngredient1 == null){
      diamondIngredient1 = new ItemStack(Material.GOLD_INGOT);
      ItemMeta diamondIngredientMeta = diamondIngredient1.getItemMeta();
      diamondIngredientMeta.setDisplayName(Methods.color("&eCost: &a$100"));
      List<String> diamondIngredientLore = new ArrayList<>();
      diamondIngredientLore.add(Methods.color("&aThis option costs $100"));
      diamondIngredientMeta.setLore(diamondIngredientLore);
      diamondIngredient1.setItemMeta(diamondIngredientMeta);
    }
    
    diamondChestPlateRecipe.addIngredient(diamondIngredient1);
    merchantRecipes.add(diamondChestPlateRecipe);
    
    merchant.setRecipes(merchantRecipes);
    
    player.openMerchant(merchant, true);
    //It opens by default viewing trade index of 0 but we populate items so we need to do that
    new BukkitRunnable(){
      @Override
      public void run(){
        MerchantInventory merchantInventory = (MerchantInventory) player.getOpenInventory().getTopInventory();
        merchantInventory.setItem(0, GUICommand.healIngredient1);
        merchantInventory.setItem(2, GUICommand.healItem);
        player.updateInventory();
      }
    }.runTaskLater(Villager.instance, 1);
    return false;
  }
}
