package relics; //

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public abstract class AbstractClickRelic extends CustomRelic{
	
	public static final Logger logger = LogManager.getLogger(AbstractClickRelic.class);
	
	private boolean RclickStart;
	private boolean Rclick;

	public AbstractClickRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
		super(id, texture, tier, sfx);
		// TODO Auto-generated constructor stub
		this.Rclick=false;
		this.RclickStart=false;
	}

	
	protected abstract void onRightClick();
	
	@Override
	public void update() {
		super.update();
		if(this.RclickStart&&InputHelper.justReleasedClickRight) {
			if(this.hb.hovered) {
				this.Rclick=true;
			}
			this.RclickStart=false;
		}
		if((this.isObtained)&&(this.hb != null)&&((this.hb.hovered) && (InputHelper.justClickedRight))) {
			this.RclickStart=true;
			//logger.info("rcs");
		}
		if((this.Rclick)){
			//logger.info("rc");
			this.Rclick=false;
			this.onRightClick();
		}
	}

}
