package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DarkEmbracePower;
import patches.AbstractCardEnum;
import powers.PFHFY;
import powers.PFHGJ;

public class CFHFY extends CustomCard {
    public static final String ID = "RemiliaMod:CFHFY";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private int flightAmt = 1;

    public CFHFY() {
        this(0);
    }

    public CFHFY(final int upgrades) {
        super("RemiliaMod:CFHFY", CFHFY.NAME, "images/cards/CFHFY.png", COST, CFHFY.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PFHFY(p, 1), 1));
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
        return new CFHFY(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFHFY");
        NAME = CFHFY.cardStrings.NAME;
        DESCRIPTION = CFHFY.cardStrings.DESCRIPTION;
    }
}
