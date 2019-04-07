package relics;

import basemod.abstracts.CustomRelic;
import cards.CFx;
import cards.CFxAttack;
import cards.CMABianfuzhen;
import cards.CMADc;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import powers.PLZYCY;
import powers.PQNXXG;

import java.io.IOException;

public class RXiangDui extends CustomRelic {
    public static final String ID = "RemiliaMod:RXiangDui";
    public static int Xds = 1;
    public static int Zjy;

    public RXiangDui() throws IOException {
        super("RemiliaMod:RXiangDui", new Texture("images/relics/RXiangDui.png"), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.SOLID);
        this.counter = 0;

    }

    @Override
    public void atPreBattle() {
        //this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PLZYCY(AbstractDungeon.player, Xds)));

    }

    @Override
    public String getUpdatedDescription() {
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + " NL " + a;
        }
        //ret = ret + " NL  NL Via\b takso\b estas:" + RXiangDui.Xds + " NL  NL Via\b sperto\b estas:" + RXiangDui.Zjy;
        return ret;
    }


    @Override
    public void atTurnStart() {
        //this.counter = 0;


    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        //System.out.println(card.cardID);
        //当前回合打出X张A攻击之后，添加一张蝙蝠阵
        if (card.cardID.indexOf("CAattack") != -1) {
            this.counter++;
            if (this.counter % 8 == 0) {
                //CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_HW", MathUtils.random(0.0f, 0.1f));
                this.flash();
                this.counter = 0;
                //蝙蝠阵
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMABianfuzhen(2), 1, false));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMADc(), 1, false));
                //飞行与飞空打击
                //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CFx(1), 1, false));
                //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CFxAttack(1), 1, false));
            }
        }
    }

    public static int SetZjy(int a) {
        //900经验为一个相对等级
        Zjy = Zjy + a;
        int lsxds = 0;
        for (int i = Zjy; i > 0; ) {
            lsxds++;
            i = i - 900;
        }
        /*if (lsxds > 9) {
            lsxds = 9;
        }*/
        Xds = lsxds;
        return Zjy;
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        PQNXXG.cd = false;//这条代码用来控制一回合只能获得一张彩蛋卡

       /* for (final AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                this.flash();
                System.out.println(300);
                SetZjy(50);
            }else if(m.type==MonsterRoomBoss){}
        }*/

        //判断什么房
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
            System.out.println("C：+" + 600 + "经验");
            SetZjy(600);
        } else if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            System.out.println("B：+" + 250 + "经验");
            SetZjy(250);

        } else if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom) {
            System.out.println("A：+" + 100 + "经验");
            SetZjy(100);

        } else if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.EventRoom) {
            System.out.println("R");
            //Zjy = 0;
            //Xds = 1;
        } else {
            System.out.println("R" + AbstractDungeon.getCurrRoom());
        }
        this.flash();

        final AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(Xds);
        }
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + " NL " + a;
        }
        ret = ret + " NL  NL Via\b takso\b estas:" + RXiangDui.Xds + " NL  NL Via\b sperto\b estas:" + RXiangDui.Zjy;
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, ret));
        this.initializeTips();
    }


}
