package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.localization.*;
import basemod.helpers.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches.AbstractCardEnum;
import relics.RLLDDJ;

import java.util.Iterator;

public class Scratch extends CustomCard
{
    public static final String ID = "RemiliaMod:Scratch";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Strike.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;//6
    private static final int UPGRADE_PLUS_DMG = 3;

    public Scratch() {
        super("RemiliaMod:Scratch", Scratch.NAME, IMG_PATH, COST, Scratch.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod,AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
        final int n = 6;
        this.baseDamage = n;
        this.damage = n;
        //this.tags.add(AbstractCard.CardTags.STRIKE);
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
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

        /*
        final Iterator<AbstractPower> s = AbstractDungeon.player.powers.iterator();
        while (s.hasNext()) {
            final AbstractPower p2 = s.next();
            if (p2.type == AbstractPower.PowerType.BUFF) {
                s.remove();
            }
        }*/
    }

    public AbstractCard makeCopy() {
        return new Scratch();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:Scratch");
        NAME = Scratch.cardStrings.NAME;
        DESCRIPTION = Scratch.cardStrings.DESCRIPTION;
    }
}
