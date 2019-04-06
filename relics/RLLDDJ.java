package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import powers.PLLDDJ;

public class RLLDDJ extends CustomRelic {
    public static final String ID = "RemiliaMod:RLLDDJ";

    public RLLDDJ() {
        super("RemiliaMod:RLLDDJ", new Texture("images/relics/RLLDDJ.png"), RelicTier.COMMON, LandingSound.SOLID);
    }


    @Override
    public void atPreBattle() {
        this.flash();

    }
    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PLLDDJ(AbstractDungeon.player, 1), 1));

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        this.counter = -1;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, mo, new StrengthPower(mo, 1), 1));
        }
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {

    }



    @Override
    public void onVictory() {

    }


}
