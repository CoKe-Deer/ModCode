package powers;

import cards.PLY;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.Iterator;

public class Yakumo_Yukari_NL3 extends AbstractPower {
    public static final String POWER_ID = "Yakumo Yukari NL3";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static int a = 1;
    public static int b = 0;

    public static int c = 8;
    public static int d = 10;

    public Yakumo_Yukari_NL3(final AbstractCreature owner, final int strengthAmount) {
        this.name = Yakumo_Yukari_NL3.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        b = 0;
        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/Yakumo_Yukari_NL3.png");
    }

    @Override
    public void updateDescription() {
        this.description = Yakumo_Yukari_NL3.DESCRIPTIONS[0];
    }

    public static boolean sfsf = false;

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PFWZL(this.owner, a), a));
        b++;
        if (b >= c && !sfsf) {

            final AbstractCard c = new PLY();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(c, 1));

            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(c.cardID), Settings.WIDTH / 2, Settings.HEIGHT / 2));

            sfsf = true;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Yakumo_Yukari_XS(AbstractDungeon.player, d), d));
            Yakumo_Yukari_XS.cdie = true;
        }
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PoisonPower(AbstractDungeon.player, AbstractDungeon.player, ds), ds, AbstractGameAction.AttackEffect.POISON));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.amount), this.amount));
    }

    @Override
    public void atStartOfTurnPostDraw() {

        this.flash();
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Yakumo Yukari NL3");
        NAME = Yakumo_Yukari_NL3.powerStrings.NAME;
        DESCRIPTIONS = Yakumo_Yukari_NL3.powerStrings.DESCRIPTIONS;
    }

}
