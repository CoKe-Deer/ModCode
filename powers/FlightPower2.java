package com.megacrit.cardcrawl.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.cards.*;

public class FlightPower2 extends AbstractPower
{
    public static final String POWER_ID = "Flight2";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private int storedAmount;

    public FlightPower2(final AbstractCreature owner, final int amount) {
        //System.out.println("正在启动1");
        this.name = FlightPower2.NAME;
        this.ID = "Flight2";
        //System.out.println("正在启动2");
        this.owner = owner;
        this.amount = amount;
        this.storedAmount = amount;
        this.updateDescription();
        this.img = new Texture("images/powers/Flight2.png");
        this.priority = 50;
        //System.out.println("正在启动3");
    }

    @Override
    public void playApplyPowerSfx() {
        //System.out.println("正在启动SY");
        CardCrawlGame.sound.play("POWER_FLIGHT", 0.05f);
        //System.out.println("正在启动SY");
    }

    @Override
    public void updateDescription() {
        //System.out.println("正在启动MS");
        this.description = FlightPower2.DESCRIPTIONS[0] ;
        //System.out.println("正在启动MS");
    }

    @Override
    public void atStartOfTurn() {
        this.amount = this.storedAmount;
        this.updateDescription();
    }

    @Override
    public float atDamageFinalReceive(final float damage, final DamageInfo.DamageType type) {
        return this.calculateDamageTakenAmount(damage, type);
    }

    private float calculateDamageTakenAmount(final float damage, final DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
            return damage / 2.0f;
        }
        return damage;
    }

    @Override
    public int onAttacked(final DamageInfo info, final int damageAmount) {
        /*final Boolean willLive = this.calculateDamageTakenAmount(damageAmount, info.type) < this.owner.currentHealth;
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && willLive) {
            this.flash();
            //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Flight2", 1));
        }*/
        return damageAmount;
    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flight2");
        NAME = FlightPower2.powerStrings.NAME;
        DESCRIPTIONS = FlightPower2.powerStrings.DESCRIPTIONS;
    }
}
