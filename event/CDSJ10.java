package event;

import cards.CWYW;
import cards.CYMZW;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.beyond.MindBloom;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CDSJ10 extends AbstractImageEvent {
    public static final String ID = "Remilia_CDSJ10";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String DIALOG_2;
    private static final String DIALOG_3;
    private static final String DIALOG_4;
    private static CurScreen screen;

    public static int ydcs=0;

    public CDSJ10() {
        super(CDSJ10.NAME, CDSJ10.DIALOG_1 + "      \b\b\b当前等级:" + ydcs, "images/events/CDSJ10.jpg");
        CDSJ10.screen = CurScreen.INTRO;
        this.imageEventText.setDialogOption(CDSJ10.OPTIONS[0]);
        this.imageEventText.setDialogOption(CDSJ10.OPTIONS[1]);
        ydcs++;

    }

    //private static Boolean tz = false;
    private String getMonster() {
        return "Lagavulin Event";
    }

    @Override
    protected void buttonEffect(final int buttonPressed) {
        switch (CDSJ10.screen) {
            case INTRO: {
                switch (buttonPressed) {
                    case 0: {
                        //tz = true;
                        /*
                        this.imageEventText.updateBodyText(CDSJ10.DIALOG_2);
                        this.screen = CurScreen.FIGHT;
                        AbstractEvent.logMetric("MindBloom", "Fight");
                        CardCrawlGame.sound.playAndLoop("RemiliaStringsMod:we", 0F);
                        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("The Guardian");
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().addGoldToRewards(100);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        this.enterCombatFromImage();
                        this.enterCombat();
                        AbstractDungeon.lastCombatMetricKey = "Mind Bloom Boss Battle";*/
                        this.imageEventText.updateBodyText(CDSJ10.DIALOG_2);
                        CDSJ10.screen = CurScreen.FIGHT;
                        System.out.println("目前选择1" + CDSJ10.screen.name());
                        //AbstractEvent.logMetric("Remilia_CDSJ10", "Fight");
                        //CardCrawlGame.music.playTempBgmInstantly("llw.mp3", true);
                        //CardCrawlGame.sound.playAndLoop("RemiliaStringsMod:we", 0F);
                        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Remilia_Smww");
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        for (int i = 0; i < ydcs; i++) {
                            AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        }
                        AbstractDungeon.getCurrRoom().addGoldToRewards(4444);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);

                        this.enterCombatFromImage();
                        AbstractDungeon.lastCombatMetricKey = "Remilia_Smww";
                        this.imageEventText.clearRemainingOptions();
                        return;
                    }
                    case 1:
                        CDSJ10.screen = CurScreen.LEAVE;
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
                //this.imageEventText.clearAllDialogs();
                //this.imageEventText.setDialogOption(CDSJ10.OPTIONS[1]);
                //this.screen = CurScreen.RESULT;
                this.imageEventText.clearRemainingOptions();
            }
            case LEAVE: {
                System.out.println("目前选择2" + this.screen.name());
                System.out.println("你选择离开");
                this.openMap();
                break;
            }
            default: {
                System.out.println("目前选择3" + this.screen.name());
                //if (tz) {
                //    this.imageEventText.updateBodyText(CDSJ10.DIALOG_3);
                //} else {
                //    this.imageEventText.updateBodyText(CDSJ10.DIALOG_4);
                //}
                System.out.println("你没有做选择");
                //this.openMap();
                break;
            }


        }
    }


    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Remilia_CDSJ10");
        NAME = CDSJ10.eventStrings.NAME;
        DESCRIPTIONS = CDSJ10.eventStrings.DESCRIPTIONS;
        OPTIONS = CDSJ10.eventStrings.OPTIONS;
        DIALOG_1 = CDSJ10.DESCRIPTIONS[0];
        DIALOG_2 = CDSJ10.DESCRIPTIONS[1];
        DIALOG_3 = CDSJ10.DESCRIPTIONS[2];
        DIALOG_4 = CDSJ10.DESCRIPTIONS[3];
    }

    private enum CurScreen {
        INTRO,
        FIGHT,
        LEAVE;
    }


}
