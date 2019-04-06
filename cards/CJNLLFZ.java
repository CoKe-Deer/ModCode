package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import patches.AbstractCardEnum;

public class CJNLLFZ extends CustomCard {
    public static final String ID = "RemiliaMod:CJNLLFZ";
    public static final String IMG_PATH = "images/cards/CJNLLFZ.png";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNLLFZ() {
        super("RemiliaMod:CJNLLFZ", CJNLLFZ.NAME, IMG_PATH, COST, CJNLLFZ.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ENEMY);
        final int n = 6;
        this.baseBlock = n;
        this.block = n;

    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int a = 0;
        int b = 0;
        int c = 0;
        c = this.baseBlock;
        if (p.hasPower("Strength")) {
            a = p.getPower("Strength").amount;
        }
        if (m.hasPower("Strength")) {
            b = m.getPower("Strength").amount;
        }
        if (a != 0 || b != 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, b), b, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, a), a, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Strength", a));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, "Strength", b));

            if (a > b) {
                c = c + a - b;
            } else {
                c = c + b - a;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, c));
    }

    public AbstractCard makeCopy() {
        return new CJNLLFZ();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNLLFZ");
        NAME = CJNLLFZ.cardStrings.NAME;
        DESCRIPTION = CJNLLFZ.cardStrings.DESCRIPTION;
    }
}
