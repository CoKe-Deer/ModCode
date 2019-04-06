package cards;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;

import basemod.helpers.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import patches.AbstractCardEnum;

import java.util.Iterator;

public class Defend extends CustomCard
{
    public static final String ID = "RemiliaMod:Defend";
    public static final String IMG_PATH = "images/cards/Defend.png";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public Defend() {
        super("RemiliaMod:Defend", Defend.NAME, IMG_PATH, COST, Defend.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        final int n = 5;
        this.baseBlock = n;
        this.block = n;
        this.tags.add(BaseModCardTags.BASIC_DEFEND);

    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        /*
        final Iterator<AbstractPower> s = AbstractDungeon.player.powers.iterator();
        while (s.hasNext()) {
            final AbstractPower p2 = s.next();
            if (p2.type == AbstractPower.PowerType.DEBUFF) {
                s.remove();
            }
        }
        */
    }
    
    public AbstractCard makeCopy() {
        return new Defend();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }
    
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:Defend");
        NAME = Defend.cardStrings.NAME;
        DESCRIPTION = Defend.cardStrings.DESCRIPTION;
    }
}
