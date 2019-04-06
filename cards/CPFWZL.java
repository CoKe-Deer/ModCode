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
import powers.PFWZL;

public class CPFWZL extends CustomCard {
    public static final String ID = "RemiliaMod:CPFWZL";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int flightAmt = 1;

    public CPFWZL() {
        this(0);
    }

    public CPFWZL(final int upgrades) {
        super("RemiliaMod:CPFWZL", CPFWZL.NAME, "images/cards/CPFWZL.png", COST, CPFWZL.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PFWZL(AbstractDungeon.player, 1), 1));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(0);
            this.upgradeName();
        }
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPFWZL(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPFWZL");
        NAME = CPFWZL.cardStrings.NAME;
        DESCRIPTION = CPFWZL.cardStrings.DESCRIPTION;
    }
}
