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
import powers.PFWZL;
import relics.RXiangDui;

public class CPZQ extends CustomCard {
    public static final String ID = "RemiliaMod:CPZQ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int flightAmt = 1;

    public CPZQ() {
        this(0);
    }

    public CPZQ(final int upgrades) {
        super("RemiliaMod:CPZQ", CPZQ.NAME, "images/cards/CPZQ.png", COST, CPZQ.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = flightAmt;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        RXiangDui.SetZjy(100);
        System.out.println("已经加了一百经验，结束后显示");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
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
        return new CPZQ(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPZQ");
        NAME = CPZQ.cardStrings.NAME;
        DESCRIPTION = CPZQ.cardStrings.DESCRIPTION;
    }
}
