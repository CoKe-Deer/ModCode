package powers;

import cards.CHMF;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import java.util.Iterator;

public class Yakumo_Yukari_XS extends AbstractPower {
    public static final String POWER_ID = "Yakumo Yukari XS";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;


    public Yakumo_Yukari_XS(final AbstractCreature owner, final int strengthAmount) {
        this.name = Yakumo_Yukari_XS.NAME;
        this.ID = POWER_ID;
        this.owner = owner;


        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/Yakumo_Yukari_XS.png");
    }

    @Override
    public void updateDescription() {

        this.description = Yakumo_Yukari_XS.DESCRIPTIONS[0] + this.amount + Yakumo_Yukari_XS.DESCRIPTIONS[1];
    }


    public static boolean cdie = false;

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        this.flash();

        if (this.amount <= 1 && cdie) {


            AbstractDungeon.actionManager.addToBottom(new VFXAction(new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1f));

            AbstractDungeon.player.currentHealth = 12;
            AbstractDungeon.player.gold = 0;
            AbstractDungeon.player.healthBarUpdatedEvent();
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, Integer.MAX_VALUE - 8000), AbstractGameAction.AttackEffect.FIRE));

            //AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, Integer.MAX_VALUE - 8000, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));


            /*
            this.owner.currentHealth = 0;
            this.owner.gold = 0;
            this.owner.healthBarUpdatedEvent();


             */
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Yakumo Yukari XS", 1));
            this.updateDescription();
        }
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PoisonPower(AbstractDungeon.player, AbstractDungeon.player, ds), ds, AbstractGameAction.AttackEffect.POISON));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.amount), this.amount));
    }

    @Override
    public void atStartOfTurnPostDraw() {

        this.flash();
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Yakumo Yukari XS");
        NAME = Yakumo_Yukari_XS.powerStrings.NAME;
        DESCRIPTIONS = Yakumo_Yukari_XS.powerStrings.DESCRIPTIONS;
    }

}
