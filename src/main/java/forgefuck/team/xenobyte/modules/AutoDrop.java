package forgefuck.team.xenobyte.modules;

import forgefuck.team.xenobyte.api.config.Cfg;
import forgefuck.team.xenobyte.api.module.Category;
import forgefuck.team.xenobyte.api.module.CheatModule;
import forgefuck.team.xenobyte.api.module.PerformMode;
import forgefuck.team.xenobyte.gui.click.elements.Button;
import forgefuck.team.xenobyte.gui.click.elements.Panel;
import forgefuck.team.xenobyte.gui.click.elements.ScrollSlider;
import forgefuck.team.xenobyte.utils.TickHelper;

public class AutoDrop extends CheatModule {
    
    @Cfg("allStacks") private boolean allStacks;
    @Cfg("delay") private int delay;
    
    public AutoDrop() {
        super("AutoDrop", Category.PLAYER, PerformMode.TOGGLE);
    }
    
    @Override public int tickDelay() {
        return delay;
    }
    
    @Override public void onTick(boolean inGame) {
        if (inGame) {
            utils.dropSlot(utils.activeSlot(), allStacks);
        }
    }
    
    @Override public String moduleDesc() {
        return "Дроп предмета из активного слота";
    }
    
    @Override public Panel settingPanel() {
        return new Panel(
            new ScrollSlider("Delay", delay, 0, TickHelper.oneSecond()) {
                @Override public void onScroll(int dir, boolean withShift) {
                    delay = processSlider(dir, withShift);
                }
                @Override public String elementDesc() {
                    return "Задержка дропа предмета";
                }
            },
            new Button("AllStacks", allStacks) {
                @Override public void onLeftClick() {
                    buttonValue(allStacks = !allStacks);
                }
                @Override public String elementDesc() {
                    return "Дропать весь стак или по одному предмету";
                }
            }
        );
    }

}