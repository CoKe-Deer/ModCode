package cards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class AbstractCreature2 extends AbstractCreature {
    @Override
    public void damage(DamageInfo damageInfo) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }
    public void loadAnimation2(String atlasUrl, String skeletonUrl, float scale) {
        loadAnimation(atlasUrl,skeletonUrl,scale);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Sprite", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }
}
