package net.silentchaos512.wit.client.key;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.silentchaos512.wit.WIT;

public class KeyTracker {

  public static KeyTracker instance = new KeyTracker();

  public KeyBinding toggleHud = new KeyBinding("Toggle WIT HUD", Keyboard.KEY_NUMPAD1,
      WIT.MOD_NAME);

  public KeyTracker() {

    ClientRegistry.registerKeyBinding(toggleHud);
  }

  public static void init() {

    FMLCommonHandler.instance().bus().register(instance);
  }

  @SubscribeEvent
  public void onKeyInput(KeyInputEvent event) {

    // TODO
  }

  public boolean isShiftPressed() {

    return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
  }

  public boolean isControlPressed() {

    return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
  }
}
