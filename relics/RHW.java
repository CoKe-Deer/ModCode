package relics;

import basemod.abstracts.CustomRelic;
import cards.CMAXSq;
import cards.CPFWZL;
import characters.Remilia;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import powers.PFWZL;

import java.util.ArrayList;

public class RHW extends CustomRelic {
    public static final String ID = "RemiliaMod:RHW";

    public RHW() {
        super("RemiliaMod:RHW", new Texture("images/relics/RHW.png"), RelicTier.BOSS, LandingSound.SOLID);
    }


    @Override
    public void atPreBattle() {
        this.flash();

    }
    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        this.counter = -1;
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {

    }



    @Override
    public void onVictory() {

    }


}
