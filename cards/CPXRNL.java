package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.PXRNL;
import powers.PYSNL;

public class CPXRNL extends CustomCard {
    public static final String ID = "RemiliaMod:CPXRNL";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private int flightAmt = 1;

    public CPXRNL() {
        this(0);
    }

    public CPXRNL(final int upgrades) {
        super("RemiliaMod:CPXRNL", CPXRNL.NAME, "images/cards/CPXRNL.png", COST, CPXRNL.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = flightAmt;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PXRNL(p, this.magicNumber), this.magicNumber));
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
        return new CPXRNL(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPXRNL");
        NAME = CPXRNL.cardStrings.NAME;
        DESCRIPTION = CPXRNL.cardStrings.DESCRIPTION;
    }
}
