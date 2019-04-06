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
import powers.PFHGJ;

public class CFHGJ extends CustomCard {
    public static final String ID = "RemiliaMod:CFHGJ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int flightAmt = 1;

    public CFHGJ() {
        this(0);
    }

    public CFHGJ(final int upgrades) {
        super("RemiliaMod:CFHGJ", CFHGJ.NAME, "images/cards/CFHGJ.png", COST, CFHGJ.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PFHGJ(p, this.flightAmt), this.flightAmt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(0);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CFHGJ(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFHGJ");
        NAME = CFHGJ.cardStrings.NAME;
        DESCRIPTION = CFHGJ.cardStrings.DESCRIPTION;
    }
}
