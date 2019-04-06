package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower2;
import patches.AbstractCardEnum;
import powers.PHWYB;

public class CPHwYb extends CustomCard {
    public static final String ID = "RemiliaMod:CPHwYb";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 4;
    private int flightAmt = 1;

    public CPHwYb() {
        this(0);
    }

    public CPHwYb(final int upgrades) {
        super("RemiliaMod:CPHwYb", CPHwYb.NAME, "images/cards/CPHwYb.png", COST, CPHwYb.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_HW", MathUtils.random(0.0f, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PHWYB(p, this.flightAmt), this.flightAmt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(3);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPHwYb(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPHwYb");
        NAME = CPHwYb.cardStrings.NAME;
        DESCRIPTION = CPHwYb.cardStrings.DESCRIPTION;
    }
}
