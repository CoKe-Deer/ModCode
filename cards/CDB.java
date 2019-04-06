package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import patches.AbstractCardEnum;

public class CDB extends CustomCard {
    public static final String ID = "RemiliaMod:CDB";
    public static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "images/cards/CDB.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 1;

    public CDB() {
        super("RemiliaMod:CDB", CDB.NAME, IMG_PATH, COST, CDB.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.damage = ATTACK_DMG;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.upgradeBaseCost(1);
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0; i < 4; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

    }

    public AbstractCard makeCopy() {
        return new CDB();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CDB");
        NAME = CDB.cardStrings.NAME;
        DESCRIPTION = CDB.cardStrings.DESCRIPTION;
    }
}
