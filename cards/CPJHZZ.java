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
import com.megacrit.cardcrawl.powers.RetainCardPower;
import patches.AbstractCardEnum;

public class CPJHZZ extends CustomCard {
    public static final String ID = "RemiliaMod:CPJHZZ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private int flightAmt = 1;

    public CPJHZZ() {
        this(0);
    }

    public CPJHZZ(final int upgrades) {
        super("RemiliaMod:CPJHZZ", CPJHZZ.NAME, "images/cards/CPJHZZ.png", COST, CPJHZZ.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = 1;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetainCardPower(p, this.magicNumber), this.magicNumber));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(1);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPJHZZ(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPJHZZ");
        NAME = CPJHZZ.cardStrings.NAME;
        DESCRIPTION = CPJHZZ.cardStrings.DESCRIPTION;
    }
}
