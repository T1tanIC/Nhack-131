package me.bebeli555.cookieclient.gui;

import java.awt.*;

public class ColorSetting extends GSColor implements com.lukflug.panelstudio.settings.ColorSetting {

    private boolean rainbow;

    public ColorSetting(GSColor color, int a) {
        super(color, a);
    }

    public ColorSetting(String outline, GSColor gsColor) {
        super(null);
    }


    @Override
    public GSColor getValue() {
        if (rainbow) return GSColor.fromHSB((System.currentTimeMillis() % (360 * 32)) / (360f * 32), 1, 1);
        return null;
    }

    public int toInteger() {
        return getValue().getRGB() & 0xFFFFFF + (this.rainbow ? 1 : 0) * 0x1000000;
    }

    public void fromInteger(int number) {
        this.rainbow = ((number & 0x1000000) != 0);
        return;
    }

    @Override
    public void setValue(Color value) {
      return;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public boolean getRainbow() {
        return this.rainbow;
    }

    @Override
    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
}
