package net.silentchaos512.wit.config;

import java.io.File;
import java.util.List;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.silentchaos512.wit.lib.EnumHudPosition;
import net.silentchaos512.wit.lib.EnumJustification;

public class Config {

  /*
   * HUD display position
   */

  public static EnumJustification hudJustification = EnumJustification.CENTER;
  public static String hudJustificationComment = "The justification of text in the HUD. Can be LEFT, CENTER, or RIGHT";

  public static EnumHudPosition hudPosition = EnumHudPosition.TOP_CENTER;
  public static String hudPositionComment = "The positioning of the HUD. Can be TOP_LEFT, TOP_CENTER, TOP_RIGHT, CENTER_LEFT, CENTER_RIGHT, BOTTOM_LEFT, BOTTOM_MIDDLE, or BOTTOM_RIGHT";

  /*
   * HUD display options
   */

  //@formatter:off
  public static ConfigOptionHudElement hudObjectName = new ConfigOptionHudElement(
      "Object Name", true, false, "&f")
      .setComment("Display the block/entity name in the HUD. Example: Iron Ore, Chicken");
  public static ConfigOptionHudElement hudResourceName = new ConfigOptionHudElement(
      "Resource Name", true, true, "&7")
      .setComment("Display the resource name in the HUD. Example: minecraft:stone.");
  public static ConfigOptionHudElement hudModName = new ConfigOptionHudElement(
      "Mod Name", true, false, "&5")
      .setComment("Display the mod name in the HUD. Example: Minecraft");
  public static ConfigOptionHudElement hudIdMeta = new ConfigOptionHudElement(
      "ID and Meta", true, true, "&o")
      .setComment("Display the ID and metadata in the HUD. Example: [1:0]");
  public static ConfigOptionHudElement hudTileEntity = new ConfigOptionHudElement(
      "Tile Entity", true, false, "&7")
      .setComment("Tells if a tile entity exists for the block being looked at. Example: Furnace (TE)");
  public static ConfigOptionHudElement hudHarvestable = new ConfigOptionHudElement(
      "Harvestability", true, true, "&a", "&c")
      .setComment("Shows whether or not a block is harvestable and with what kind of tool.");
  public static ConfigOptionHudElement hudBlockInventory = new ConfigOptionHudElement(
      "Inventory Contents", true, false, "&f")
      .setComment("Displays the contents of certain inventories, including item count.");
  public static ConfigOptionHudElement hudEntityHealth = new ConfigOptionHudElement(
      "Entity Health", true, false, "&f")
      .setComment("Display the health and max health of entities.");
  //@formatter:on

  public static float hudBackgroundOpacity = 0.8f;
  public static float hudOffsetX = 0.0f;
  public static float hudOffsetY = 0.0f;
  public static boolean hudAdvancedMode = false;
  public static boolean hudHideWhenGuiOpen = true;
  public static int hudInventoryMaxListCount = 8;
  public static boolean disguiseMonsterEggBlocks = true;
  public static boolean enableSunflowerBugfix = true;

  /*
   * Tooltip display options
   */

  public static boolean tooltipDisplayIdMeta = false;
  public static String tooltipDisplayIdMetaComment = "Display the item ID and damage value by the item name.";
  public static boolean tooltipDisplayModName = true;
  public static String tooltipDisplayModNameComment = "Display the name of the mod in tooltips.";
  public static boolean tooltipDisplayModNameShift = false;
  public static String tooltipDisplayModNameShiftComment = "Display the name of the mod only when holding shift.";
  public static boolean tooltipDisplayOreDict = true;
  public static String tooltipDisplayOreDictComment = "Display the ore dictionary entries for the block/item.";
  public static boolean tooltipDisplayOreDictShift = true;
  public static String tooltipDisplayOreDictShiftComment = "Display the ore dictionary entries only when holding shift.";
  public static boolean tooltipDisplayFoodStats = true;
  public static String tooltipDisplayFoodStatsComment = "Display the food value and saturation of foods in the tooltip.";
  public static boolean tooltipDisplayFoodStatsShift = true;
  public static String tooltipDisplayFoodStatsShiftComment = "Display food stats only when holding shift.";
  public static boolean tooltipDisplayToolStats = true;
  public static String tooltipDisplayToolStatsComment = "Display mining speed and durability of tools when possible.";
  public static boolean tooltipDisplayToolStatsShift = true;
  public static String tooltipDisplayToolStatsShiftComment = "Display tool stats only when shift is held.";

  /*
   * Formatting
   */

  public static String formatModName = "&5";
  // Originally: The formatting codes to use in the tooltip. Use & to substitute for the control character.
  public static String formatModNameComment = "Formatting for mod names.";
  public static String formatResourceName = "&7";
  public static String formatResourceNameComment = "Formatting for resource names.";

  static final String splitter = Configuration.CATEGORY_SPLITTER;
  public static final String CAT_MAIN = "main";
  public static final String CAT_HUD = CAT_MAIN + splitter + "HUD";
  public static final String CAT_HUD_DISPLAY = CAT_HUD + splitter + "Display";
  public static final String CAT_HUD_POSITION = CAT_HUD + splitter + "Positioning";
  public static final String CAT_TOOLTIP = CAT_MAIN + splitter + "Tooltip";
  public static final String CAT_TOOLTIP_FORMAT = CAT_TOOLTIP + splitter + "Formatting";

  private static File configFile;
  private static Configuration c;

  public static void init(File file) {

    configFile = file;
    c = new Configuration(file);
    load();
  }

  public static void load() {

    try {
      // c.load();
      String str;

      /*
       * HUD
       */

      hudAdvancedMode = c.getBoolean("Advanced Mode", CAT_HUD, hudAdvancedMode,
          "Display additional information in HUD that may not be especially useful to most players.");
      hudHideWhenGuiOpen = c.getBoolean("Hide When GUI Is Open", CAT_HUD, hudHideWhenGuiOpen,
          "Hide the HUD when a GUI is open. The HUD will still remain visible if the GUI pauses the game.");

      /*
       * HUD display positioning
       */

      str = c.getString("Text Justification", CAT_HUD_POSITION, "CENTER", hudJustificationComment,
          EnumJustification.getValidValues());
      for (EnumJustification j : EnumJustification.values()) {
        if (str.equals(j.name())) {
          hudJustification = j;
        }
      }

      str = c.getString("Position", CAT_HUD_POSITION, "TOP_CENTER", hudPositionComment,
          EnumHudPosition.getValidValues());
      for (EnumHudPosition p : EnumHudPosition.values()) {
        if (str.equals(p.name())) {
          hudPosition = p;
        }
      }

      hudOffsetX = c.getFloat("X Offset", CAT_HUD_POSITION, hudOffsetX, -1.0f, 1.0f,
          "Fine-tune the position of the HUD.");
      hudOffsetY = c.getFloat("Y Offset", CAT_HUD_POSITION, hudOffsetY, -1.0f, 1.0f,
          "Fine-tune the position of the HUD.");

      /*
       * HUD display options
       */

      hudObjectName.loadValue(c);
      hudResourceName.loadValue(c);
      hudModName.loadValue(c);
      hudIdMeta.loadValue(c);
      hudTileEntity.loadValue(c);
      hudHarvestable.loadValue(c);
      hudBlockInventory.loadValue(c);
      hudEntityHealth.loadValue(c);

      hudBackgroundOpacity = c.getFloat("Background Opacity", CAT_HUD_DISPLAY, hudBackgroundOpacity,
          0f, 1f, "Opacity (alpha) of the HUD background image.");
      hudInventoryMaxListCount = c.getInt("Max Inventory List Count", CAT_HUD_DISPLAY,
          hudInventoryMaxListCount, 0, 64,
          "The maximum number of items to display for inventory contents.");
      disguiseMonsterEggBlocks = c.getBoolean("Disguise Monster Egg Blocks", CAT_HUD_DISPLAY,
          disguiseMonsterEggBlocks,
          "Show monster egg (silverfish) blocks as the blocks they look like in the HUD (requires restart)");
      enableSunflowerBugfix = c.getBoolean("Enable Sunflower Bugfix", CAT_HUD_DISPLAY,
          enableSunflowerBugfix,
          "Fixes the 'sunflower bug', where the upper half of double plants are reported as sunflowers.");

      /*
       * Tooltip display options
       */

      tooltipDisplayIdMeta = c.getBoolean("ID and Metadata", CAT_TOOLTIP, tooltipDisplayIdMeta,
          tooltipDisplayIdMetaComment);
      tooltipDisplayModName = c.getBoolean("Mod Name - Show", CAT_TOOLTIP, tooltipDisplayModName,
          tooltipDisplayModNameComment);
      tooltipDisplayModNameShift = c.getBoolean("Mod Name - Sneak Only", CAT_TOOLTIP,
          tooltipDisplayModNameShift, tooltipDisplayModNameShiftComment);
      tooltipDisplayOreDict = c.getBoolean("Ore Dictionary - Show", CAT_TOOLTIP,
          tooltipDisplayOreDict, tooltipDisplayOreDictComment);
      tooltipDisplayOreDictShift = c.getBoolean("Ore Dictionary - Sneak Only", CAT_TOOLTIP,
          tooltipDisplayOreDictShift, tooltipDisplayOreDictShiftComment);
      tooltipDisplayFoodStats = c.getBoolean("Food Stats", CAT_TOOLTIP, tooltipDisplayFoodStats,
          tooltipDisplayFoodStatsComment);
      tooltipDisplayFoodStatsShift = c.getBoolean("Food Stats - Sneak Only", CAT_TOOLTIP,
          tooltipDisplayFoodStatsShift, tooltipDisplayFoodStatsShiftComment);
      tooltipDisplayToolStats = c.getBoolean("Tool Stats", CAT_TOOLTIP, tooltipDisplayToolStats,
          tooltipDisplayToolStatsComment);
      tooltipDisplayToolStatsShift = c.getBoolean("Tool Stats - Sneak Only", CAT_TOOLTIP,
          tooltipDisplayToolStatsShift, tooltipDisplayToolStatsShiftComment);

      /*
       * Formatting
       */

      formatModName = c.getString("Mod Name", CAT_TOOLTIP_FORMAT, formatModName,
          formatModNameComment);
      formatResourceName = c.getString("Resource Name", CAT_TOOLTIP_FORMAT, formatResourceName,
          formatResourceNameComment);
    } catch (Exception e) {
      System.out.println("Oh noes!!! Couldn't load configuration file properly!");
    }

  }

  public static void save() {

    if (c.hasChanged()) {
      c.save();
    }
  }

  public static ConfigCategory getCategory(String str) {

    return c.getCategory(str);
  }

  public static Configuration getConfiguration() {

    return c;
  }

  public static List<IConfigElement> getConfigElements() {

    return new ConfigElement(getCategory(CAT_MAIN)).getChildElements();
  }
}
