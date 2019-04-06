package event;

import cards.CSZBS;
import cards.CWYW;
import cards.CYMZW;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.events.*;
import com.megacrit.cardcrawl.cards.curses.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.core.*;

public class CDSJ09 extends AbstractImageEvent {
    public static final String ID = "Remilia_CDSJ09";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String BANANA_RESULT;
    private static final String DONUT_RESULT;
    private int healAmt;
    private static final int MAX_HP_AMT = 5;
    private CurScreen screen;

    public CDSJ09() {
        super(NAME, CDSJ09.DIALOG_1, "images/events/CDSJ09.jpg");
        this.healAmt = 0;
        this.screen = CurScreen.INTRO;
        this.healAmt = AbstractDungeon.player.maxHealth / 3;
        this.imageEventText.setDialogOption(CDSJ09.OPTIONS[0] + this.healAmt + CDSJ09.OPTIONS[1]);
        this.imageEventText.setDialogOption(CDSJ09.OPTIONS[2] + 5 + CDSJ09.OPTIONS[3]);
        this.imageEventText.setDialogOption(CDSJ09.OPTIONS[4], CardLibrary.getCopy("Regret"));
    }

    @Override
    protected void buttonEffect(final int buttonPressed) {
        switch (this.screen) {
            case INTRO: {
                switch (buttonPressed) {
                    case 0: {
                        AbstractDungeon.player.heal(this.healAmt, true);
                        this.imageEventText.updateBodyText(CDSJ09.BANANA_RESULT);
                        final AbstractCard c = new CWYW();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(c.cardID), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        AbstractEvent.logMetricHeal("Remilia_CDSJ09", "CWYW", this.healAmt);
                        break;
                    }
                    case 1: {
                        AbstractDungeon.player.increaseMaxHp(5, true);
                        final AbstractCard c = new CYMZW();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(c.cardID), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        this.imageEventText.updateBodyText(CDSJ09.DONUT_RESULT);
                        AbstractEvent.logMetricMaxHPGain("Remilia_CDSJ09", "CYMZW", 5);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(CDSJ09.OPTIONS[4]);
                this.screen = CurScreen.RESULT;
            }
            default: {
                this.openMap();
                break;
            }

         }
    }


    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Remilia_CDSJ09");
        NAME = CDSJ09.eventStrings.NAME;
        DESCRIPTIONS = CDSJ09.eventStrings.DESCRIPTIONS;
        OPTIONS = CDSJ09.eventStrings.OPTIONS;
        DIALOG_1 = CDSJ09.DESCRIPTIONS[0];
        BANANA_RESULT = CDSJ09.DESCRIPTIONS[1];
        DONUT_RESULT = CDSJ09.DESCRIPTIONS[2];
    }
    private enum CurScreen
    {
        INTRO,
        RESULT;
    }



}
