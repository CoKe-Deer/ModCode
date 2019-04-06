package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower2;
import patches.AbstractCardEnum;
import powers.PWXXD;

public class CPWYXD extends CustomCard {
    public static final String ID = "RemiliaMod:CPWYXD";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private int flightAmt = 1;

    public CPWYXD() {
        this(0);
    }

    public CPWYXD(final int upgrades) {
        super("RemiliaMod:CPWYXD", CPWYXD.NAME, "images/cards/CPWYXD.png", COST, CPWYXD.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PWXXD(p, this.flightAmt), this.flightAmt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(1);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPWYXD(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPWYXD");
        NAME = CPWYXD.cardStrings.NAME;
        DESCRIPTION = CPWYXD.cardStrings.DESCRIPTION;
    }
}
