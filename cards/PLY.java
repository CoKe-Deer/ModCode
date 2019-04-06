package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import patches.AbstractCardEnum;
import powers.Yakumo_Yukari_NL3;
import powers.Yakumo_Yukari_XS;

import java.util.Iterator;

public class PLY extends CustomCard {
    public static final String ID = "RemiliaMod:PLY";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/PLY.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 1;

    public PLY() {
        super("RemiliaMod:PLY", PLY.NAME, IMG_PATH, COST, PLY.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseDamage = ATTACK_DMG;
        this.damage = ATTACK_DMG;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        Yakumo_Yukari_NL3.sfsf = false;
        Yakumo_Yukari_XS.cdie = false;
        final Iterator<AbstractPower> s = AbstractDungeon.player.powers.iterator();
        while (s.hasNext()) {
            final AbstractPower p2 = s.next();
            if (p2.type == AbstractPower.PowerType.DEBUFF) {
                s.remove();
            }
        }

    }

    public AbstractCard makeCopy() {
        return new PLY();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:PLY");
        NAME = PLY.cardStrings.NAME;
        DESCRIPTION = PLY.cardStrings.DESCRIPTION;
    }
}
