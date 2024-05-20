package ru.matt.api.modmenu;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;
import ru.matt.listener.LCheckWidget;

public class HaramScreen extends Screen {

    public final Screen prevision;

    // Enabled haram eat?
    public CheckboxWidget enabledEat;

    // Enabled Kill Spam?
    public CheckboxWidget enabledKill;

    public HaramScreen(Screen prevision) {
        super(Text.translatable("haramclient.title"));
        this.prevision = prevision;
    }

    @Override
    public void init() {
        if (enabledEat != null) removed();
        enabledEat = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("haramclient.enabledEat")) / 2 - 210,
                20, 24 + textRenderer.getWidth(Text.translatable("haramclient.enabledEat")), 20,
                Text.translatable("haramclient.enabledEat"), HaramConfig.enabledEat, cb -> HaramConfig.enabledEat = cb.isChecked()));
        if (enabledKill != null) removed();
        enabledKill = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("haramclient.enabledKill")) / 2 + 210,
                20, 24 + textRenderer.getWidth(Text.translatable("haramclient.enabledKill")), 20,
                Text.translatable("haramclient.enabledKill"), HaramConfig.enabledKill, cb -> HaramConfig.enabledKill = cb.isChecked()));
    }

    @Override
    public void removed() {
        HaramConfig.save();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("haramclient.title"), width / 2, 8, 0xffffff);
        super.render(context, mouseX, mouseY, delta);
    }
}