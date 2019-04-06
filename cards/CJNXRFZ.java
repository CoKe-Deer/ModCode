package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches.AbstractCardEnum;

public class CJNXRFZ extends CustomCard {
    public static final String ID = "RemiliaMod:CJNXRFZ";
    public static final String IMG_PATH = "images/cards/CJNXRFZ.png";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNXRFZ() {
        super("RemiliaMod:CJNXRFZ", CJNXRFZ.NAME, IMG_PATH, COST, CJNXRFZ.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ENEMY);
        final int n = 6;
        this.baseBlock = n;
        this.block = n;

    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int a = 0;
        int b = 0;
        int c = 0;
        c = this.baseBlock;
        if (p.hasPower("Weakened")) {
            a = p.getPower("Weakened").amount;
        }
        if (m.hasPower("Weakened")) {
            b = m.getPower("Weakened").amount;
        }
        if (a != 0 || b != 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, b, false), 0, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, a, false), 0, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Weakened", a));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, "Weakened", b));
            if (a > b) {
                c = c + a - b;
            } else {
                c = c + b - a;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, c));
    }

    public AbstractCard makeCopy() {
        return new CJNXRFZ();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeBaseCost(0);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNXRFZ");
        NAME = CJNXRFZ.cardStrings.NAME;
        DESCRIPTION = CJNXRFZ.cardStrings.DESCRIPTION;
    }
}
