package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;

public class CSZBS extends CustomCard {
    public static final String ID = "RemiliaMod:CWYW";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 7;
    private int flightAmt = 1;


    public CSZBS() {
        super("RemiliaMod:CSZBS", CSZBS.NAME, "images/cards/CSZBS.png", COST, CSZBS.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = flightAmt;
        this.baseMagicNumber = flightAmt;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 70), 70));


    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.upgradeBaseCost(8);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CSZBS();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CSZBS");
        NAME = CSZBS.cardStrings.NAME;
        DESCRIPTION = CSZBS.cardStrings.DESCRIPTION;
    }
}
