package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.FlightPower2;
import patches.AbstractCardEnum;

public class CFx extends CustomCard {
    public static final String ID = "RemiliaMod:CFx";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int flightAmt = 1;

    public CFx() {
        this(0);
    }

    public CFx(final int upgrades) {
        super("RemiliaMod:CFx", CFx.NAME, "images/cards/CFx.png", COST, CFx.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlightPower2(p, this.flightAmt), this.flightAmt));
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
        return new CFx(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFx");
        NAME = CFx.cardStrings.NAME;
        DESCRIPTION = CFx.cardStrings.DESCRIPTION;
    }
}
