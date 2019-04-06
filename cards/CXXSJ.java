package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;

public class CXXSJ extends CustomCard {
    public static final String ID = "RemiliaMod:CXXSJ";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CXXSJ.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 3;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public CXXSJ() {
        super("RemiliaMod:CXXSJ", CXXSJ.NAME, IMG_PATH, COST, CXXSJ.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ENEMY);
        final int n = 3;
        this.baseDamage = n;
        this.damage = n;
        this.baseMagicNumber = n;
        this.magicNumber = this.baseMagicNumber;
        //this.tags.add(AbstractCard.CardTags.STRIKE);
        //this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            this.upgradeMagicNumber(3);
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (p.currentHealth > 0) {
            p.heal(this.magicNumber);
        }
    }

    public AbstractCard makeCopy() {
        return new CXXSJ();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CXXSJ");
        NAME = CXXSJ.cardStrings.NAME;
        DESCRIPTION = CXXSJ.cardStrings.DESCRIPTION;
    }
}
