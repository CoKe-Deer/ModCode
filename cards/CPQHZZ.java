package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnvenomPower;
import com.megacrit.cardcrawl.powers.FlightPower2;
import patches.AbstractCardEnum;

public class CPQHZZ extends CustomCard {
    public static final String ID = "RemiliaMod:CPQHZZ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int flightAmt = 1;

    public CPQHZZ() {
        this(0);
    }

    public CPQHZZ(final int upgrades) {
        super("RemiliaMod:CPQHZZ", CPQHZZ.NAME, "images/cards/CPQHZZ.png", COST, CPQHZZ.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        int m=4;
        this.baseMagicNumber=m;
        this.magicNumber=this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnvenomPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(2);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPQHZZ(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPQHZZ");
        NAME = CPQHZZ.cardStrings.NAME;
        DESCRIPTION = CPQHZZ.cardStrings.DESCRIPTION;
    }
}
