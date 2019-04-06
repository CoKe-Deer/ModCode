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

public class CWYXD extends CustomCard {
    public static final String ID = "RemiliaMod:CWYXD";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CWYXD.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 2;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public CWYXD() {
        super("RemiliaMod:CWYXD", CWYXD.NAME, IMG_PATH, COST, CWYXD.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.ENEMY);
        final int n = 4;
        this.baseDamage = n;
        this.damage = n;
        this.exhaust = true;
        //this.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

    }

    public AbstractCard makeCopy() {
        return new CWYXD();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CWYXD");
        NAME = CWYXD.cardStrings.NAME;
        DESCRIPTION = CWYXD.cardStrings.DESCRIPTION;
    }
}
