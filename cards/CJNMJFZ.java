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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;

public class CJNMJFZ extends CustomCard {
    public static final String ID = "RemiliaMod:CJNMJFZ";
    public static final String IMG_PATH = "images/cards/CJNMJFZ.png";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNMJFZ() {
        super("RemiliaMod:CJNMJFZ", CJNMJFZ.NAME, IMG_PATH, COST, CJNMJFZ.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ENEMY);
        final int n = 6;
        this.baseBlock = n;
        this.block = n;

    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int a = 0;
        int b = 0;
        int c = 0;
        c = this.baseBlock;
        if (p.hasPower("Dexterity")) {
            a = p.getPower("Dexterity").amount;
        }
        if (m.hasPower("Dexterity")) {
            b = m.getPower("Dexterity").amount;
        }
        if (a != 0 || b != 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, b), 0, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DexterityPower(m, a), 0, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Dexterity", a));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, "Dexterity", b));

            if (a > b) {
                c = c + a - b;
            } else {
                c = c + b - a;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, c));
    }

    public AbstractCard makeCopy() {
        return new CJNMJFZ();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNMJFZ");
        NAME = CJNMJFZ.cardStrings.NAME;
        DESCRIPTION = CJNMJFZ.cardStrings.DESCRIPTION;
    }
}
