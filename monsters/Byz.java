package monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.GiantHead;
import com.megacrit.cardcrawl.monsters.beyond.Nemesis;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.Yakumo_Yukari_Time_Warp;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import event.CDSJ10;
import event.CDSJ11;
import powers.Yakumo_Yukari_NL3;
import powers.Yakumo_Yukari_Thousand_Cuts;

import java.util.ArrayList;
import java.util.Iterator;

public class Byz extends AbstractMonster {
    public static final String ID = "Remilia_Byz";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    public static int HP = 10000;
    public static final int A_2_HP = 10000;
    private static final byte REVERBERATE = 2;
    private static final byte RIPPLE = 3;
    private static final byte HEAD_SLAM = 4;
    private static final byte HASTE = 5;
    private static final int REVERB_DMG = 7;
    private static final int REVERB_AMT = 3;
    private static final int A_2_REVERB_DMG = 8;
    private static final int RIPPLE_BLOCK = 20;
    private static final int HEAD_SLAM_DMG = 26;
    private static final int A_2_HEAD_SLAM_DMG = 32;
    private int reverbDmg;
    private int headSlamDmg;
    private static final int HEAD_SLAM_STICKY = 1;
    private static final int RIPPLE_DEBUFF_TURNS = 1;
    private boolean usedHaste;
    private boolean firstTurn;

    public Byz() {
        super(Byz.NAME, Byz.ID, A_2_HP, -10.0f, -30.0f, 476.0f, 410.0f, null, -50.0f, 30.0f);
        try {
            this.usedHaste = false;
            this.firstTurn = true;
            if (AbstractDungeon.ascensionLevel >= 10) {
                HP = HP + 500;
            } else {
                HP = HP + 1000;
            }
            HP = HP + (CDSJ11.ydcs * 1000);
            this.setHp(HP);
            this.loadAnimation("images/Remilia/animate/Remilia_Byzzz.atlas", "images/Remilia/animate/Remilia_Byzzz.json", 0.3F);
            final AnimationState.TrackEntry e = this.state.setAnimation(0, "Sprite", true);
            e.setTime(e.getEndTime() * MathUtils.random());
            this.reverbDmg = 12 + (CDSJ11.ydcs * 5);
            this.headSlamDmg = 12 + (CDSJ11.ydcs * 5);

            this.type = EnemyType.BOSS;

            this.damage.add(new DamageInfo(this, this.reverbDmg, DamageInfo.DamageType.NORMAL));
            this.damage.add(new DamageInfo(this, this.headSlamDmg, DamageInfo.DamageType.NORMAL));
            mode.add(new Nemesis());
            mode.add(new GiantHead());
            //mode.add(new Lagavulin(false));
        } catch (Exception e) {
            System.out.println("恭喜你第三次，遇到了百年难得一遇的问题，你是核心玩家");
            e.printStackTrace();
        }
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        /*
        if (MathUtils.random(0) == 0) {
            CardCrawlGame.music.playTempBgmInstantly("llw.mp3", true);
        } else {
            CardCrawlGame.music.playTempBgmInstantly("pm.mp3", true);
        }*/
        CardCrawlGame.music.playTempBgmInstantly("byz.mp3", true);
        UnlockTracker.markBossAsSeen("WIZARD");

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new Yakumo_Yukari_Time_Warp(this, 3)));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new Yakumo_Yukari_Thousand_Cuts(this, 3)));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new Yakumo_Yukari_NL3(this, 1)));

        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new UnawakenedPower(this)));

        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new CuriosityPower(this, 2 )));

        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new RitualPower(this, 10)));

    }

    private static ArrayList<AbstractMonster> mode = new ArrayList<AbstractMonster>();

    @Override
    public void takeTurn() {
        final Iterator<AbstractPower> s = this.powers.iterator();
        while (s.hasNext()) {
            final AbstractPower p = s.next();
            //if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID.equals("Shackled")) {
            //    s.remove();
            //}
            if (p.ID.indexOf("Poison") != -1) {
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, "Ĉi     \btiu     \bruzo    \bestas   \bsenutila    \bpor     \bmi.", 0.5f, 3.0f));
                //System.out.println("清理了takeqweqweqwwqerqwrqwrqwrqwrwrqwrwqr");
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, p.amount));
                s.remove();
                break;
            }
        }
        if (this.firstTurn) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(this, Byz.DIALOG[0], 0.5f, 2.0f));
            this.firstTurn = false;
            AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Cultist(200.0F, 130.0F), true, 1));
            //AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BronzeOrb(200.0f, 130.0f, 2), true, 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new StrengthPower(AbstractDungeon.player, -3), -3));
            return;
        }
        switch (this.nextMove) {
            case 2: {
                for (int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.RED_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.55f));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
                }
                break;
            }
            case 3: {
                //AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BronzeOrb(200.0f, 130.0f, 2), true, 1));

                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(mode.get(MathUtils.random(0, 1)), true, 1));


                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, Byz.DIALOG[1], 0.5f, 3.0f));

                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 999));

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new StrengthPower(AbstractDungeon.player, -10), -10));

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 10), 10));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 100, true), 150));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 100, true), 150));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                break;
            }
            case 4: {
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, Byz.DIALOG[0], 0.5f, 3.0f));
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4f));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.POISON));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new DrawReductionPower(AbstractDungeon.player, 1)));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Wound(), 3));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Writhe(), 3));
                //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 3));

                break;
            }
            case 5: {
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, Byz.DIALOG[2], 0.5f, 2.0f));
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(this));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this, this, "Shackled"));
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth / 2 - this.currentHealth));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Byz(), true, 1));
                if (AbstractDungeon.ascensionLevel >= 19) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, this.headSlamDmg));
                    break;
                }
                break;
            }
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if (this.currentHealth < this.maxHealth / 2 && !this.usedHaste) {
            this.usedHaste = true;
            this.setMove((byte) 5, Intent.BUFF);
            return;
        }
        if (num < 45) {
            if (!this.lastTwoMoves((byte) 2)) {
                this.setMove((byte) 2, Intent.ATTACK, this.damage.get(0).base, 997, true);
                return;
            }
            this.getMove(AbstractDungeon.aiRng.random(50, 99));
        } else if (num < 80) {
            if (!this.lastMove((byte) 4)) {
                this.setMove((byte) 4, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
                return;
            }
            if (AbstractDungeon.aiRng.randomBoolean(0.66f)) {
                this.setMove((byte) 2, Intent.ATTACK, this.damage.get(0).base, 997, true);
                return;
            }
            this.setMove((byte) 3, Intent.DEFEND_DEBUFF);
        } else {
            if (!this.lastMove((byte) 3)) {
                this.setMove((byte) 3, Intent.DEFEND_DEBUFF);
                return;
            }
            this.getMove(AbstractDungeon.aiRng.random(74));
        }
    }

    @Override
    public void damage(final DamageInfo info) {
        super.damage(info);

    }

    @Override
    public void die() {
        if (!AbstractDungeon.getCurrRoom().cannotLose) {
            this.useFastShakeAnimation(5.0f);
            CardCrawlGame.screenShake.rumble(1.0f);
            super.die();

        }
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Remilia_Byz");
        NAME = Byz.monsterStrings.NAME;
        MOVES = Byz.monsterStrings.MOVES;
        DIALOG = Byz.monsterStrings.DIALOG;
    }
}
