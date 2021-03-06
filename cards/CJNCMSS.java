package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches.AbstractCardEnum;

public class CJNCMSS extends CustomCard
{
    public static final String ID = "RemiliaMod:CJNCMSS";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public CJNCMSS() {
        super("RemiliaMod:CJNCMSS", CJNCMSS.NAME, "images/cards/CJNCMSS.png", COST, CJNCMSS.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PhantasmalPower(p, 2), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
            this.baseMagicNumber = 3;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CJNCMSS();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNCMSS");
        NAME = CJNCMSS.cardStrings.NAME;
        DESCRIPTION = CJNCMSS.cardStrings.DESCRIPTION;
    }
}
