//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.megacrit.cardcrawl.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;

import java.util.Iterator;

public class Yakumo_Yukari_Time_Warp extends AbstractPower {
    public static final String POWER_ID = "Yakumo Yukari Time Warp";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESC;
    private static final int STR_AMT = 2;
    private static final int COUNTDOWN_AMT = 6;

    public Yakumo_Yukari_Time_Warp(AbstractCreature owner, int ds) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = ds;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.img = new Texture("images/powers/Yakumo_Yukari_Time_Warp.png");
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
    }

    public void updateDescription() {
        this.description = DESC[0] + (COUNTDOWN_AMT - this.amount) + DESC[1] + STR_AMT + DESC[2] + DESC[3] + this.amount + DESC[4];
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {


        this.flashWithoutSound();
        ++this.amount;
        if (this.amount >= COUNTDOWN_AMT) {
            Js();
        }

        this.updateDescription();


    }

    public void Js() {
        this.amount = 0;
        this.playApplyPowerSfx();
        AbstractDungeon.actionManager.cardQueue.clear();
        Iterator var3 = AbstractDungeon.player.limbo.group.iterator();

        while (var3.hasNext()) {
            AbstractCard c = (AbstractCard) var3.next();
            AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
        }

        AbstractDungeon.player.limbo.group.clear();
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.overlayMenu.endTurnButton.disable(true);
        CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
        AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());
        var3 = AbstractDungeon.getMonsters().monsters.iterator();

        while (var3.hasNext()) {
            AbstractMonster m = (AbstractMonster) var3.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new StrengthPower(m, this.amount), this.amount));
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Yakumo Yukari Time Warp");
        NAME = powerStrings.NAME;
        DESC = powerStrings.DESCRIPTIONS;
    }
}
