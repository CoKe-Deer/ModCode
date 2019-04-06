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

public class CMPA extends CustomCard
{
    public static final String ID = "RemiliaMod:CMPA";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CMPA.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 1;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public CMPA() {
        super("RemiliaMod:CMPA", CMPA.NAME, IMG_PATH, COST, CMPA.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        final int n = 1;
        this.baseDamage = n;
        this.damage = n;
        //this.tags.add(AbstractCard.CardTags.STRIKE);
        //this.tags.add(BaseModCardTags.BASIC_STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.currentHealth > 0) {
            p.heal(this.damage);
        }
    }

    public AbstractCard makeCopy() {
        return new CMPA();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMPA");
        NAME = CMPA.cardStrings.NAME;
        DESCRIPTION = CMPA.cardStrings.DESCRIPTION;
    }
}
