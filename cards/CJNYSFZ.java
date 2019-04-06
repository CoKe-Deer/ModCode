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
import patches.AbstractCardEnum;

public class CJNYSFZ extends CustomCard {
    public static final String ID = "RemiliaMod:CJNYSFZ";
    public static final String IMG_PATH = "images/cards/CJNYSFZ.png";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNYSFZ() {
        super("RemiliaMod:CJNYSFZ", CJNYSFZ.NAME, IMG_PATH, COST, CJNYSFZ.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ENEMY);
        final int n = 6;
        this.baseBlock = n;
        this.block = n;

    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int a = 0;
        int b = 0;
        int c = 0;
        c = this.baseBlock;
        if (p.hasPower("Vulnerable")) {
            a = p.getPower("Vulnerable").amount;
        }
        if (m.hasPower("Vulnerable")) {
            b = m.getPower("Vulnerable").amount;
        }
        if (a != 0 || b != 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, b, false), 0, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, a, false), 0, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Vulnerable", a));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, "Vulnerable", b));

            if (a > b) {
                c = c + a - b;
            } else {
                c = c + b - a;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, c));
    }

    public AbstractCard makeCopy() {
        return new CJNYSFZ();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeBaseCost(0);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNYSFZ");
        NAME = CJNYSFZ.cardStrings.NAME;
        DESCRIPTION = CJNYSFZ.cardStrings.DESCRIPTION;
    }
}
