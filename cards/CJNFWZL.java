package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.PFWZL;

public class CJNFWZL extends CustomCard
{
    public static final String ID = "RemiliaMod:CJNFWZL";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int DRAW = 2;
    private static final int DISCARD = 1;

    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNFWZL() {
        super("RemiliaMod:CJNFWZL", CJNFWZL.NAME, "images/cards/CJNFWZL.png",COST, CJNFWZL.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        final int n = CJNFWZL.BLOCK;
        this.baseBlock = n;
        this.block = n;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        //reflectedMap.put("RemiliaStringsMod:Remilia_SF", new Sfx("RemiliaStrings/sounds/Remilia_SF.ogg"));
        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_SF", 0F);
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PFWZL(AbstractDungeon.player, this.magicNumber),this.magicNumber));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(1);

        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CJNFWZL();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNFWZL");
        NAME = CJNFWZL.cardStrings.NAME;
        DESCRIPTION = CJNFWZL.cardStrings.DESCRIPTION;
    }
}
