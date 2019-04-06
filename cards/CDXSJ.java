package cards;

import basemod.abstracts.CustomCard;
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

public class CDXSJ extends CustomCard {
    public static final String ID = "RemiliaMod:CDXSJ";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CDXSJ.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 9;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public CDXSJ() {
        super("RemiliaMod:CDXSJ", CDXSJ.NAME, IMG_PATH, COST, CDXSJ.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.ENEMY);
        final int n = 9;
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
        return new CDXSJ();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CDXSJ");
        NAME = CDXSJ.cardStrings.NAME;
        DESCRIPTION = CDXSJ.cardStrings.DESCRIPTION;
    }
}
