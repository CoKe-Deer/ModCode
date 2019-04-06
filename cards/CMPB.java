package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import patches.AbstractCardEnum;

public class CMPB extends CustomCard
{
    public static final String ID = "RemiliaMod:CMPB";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CMPB.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 1;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public CMPB() {
        super("RemiliaMod:CMPB", CMPB.NAME, IMG_PATH, COST, CMPB.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
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
            this.upgradeDamage(1);
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new ThornsPower(p, this.damage), this.damage));

    }

    public AbstractCard makeCopy() {
        return new CMPB();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMPB");
        NAME = CMPB.cardStrings.NAME;
        DESCRIPTION = CMPB.cardStrings.DESCRIPTION;
    }
}
