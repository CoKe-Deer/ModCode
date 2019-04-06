package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import patches.AbstractCardEnum;

public class CMPC extends CustomCard
{
    public static final String ID = "RemiliaMod:CMPC";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CMPC.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 1;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public CMPC() {
        super("RemiliaMod:CMPC", CMPC.NAME, IMG_PATH, COST, CMPC.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
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
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, this.damage), this.damage));
    }

    public AbstractCard makeCopy() {
        return new CMPC();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMPC");
        NAME = CMPC.cardStrings.NAME;
        DESCRIPTION = CMPC.cardStrings.DESCRIPTION;
    }
}
