package ti.chromecast;

import java.util.List;

import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;

import android.content.Context;

import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;

class CastOptionsProvider implements OptionsProvider {
	private static final String LCAT = "Ti🎈🎈";

	public CastOptionsProvider() {
		Log.d(LCAT, TiApplication.getInstance().getApplicationContext()
				.getPackageName()
				+ "." + this.getClass().getSimpleName());
	}

	@Override
	public CastOptions getCastOptions(Context appContext) {
		// TODO getting ID
		String ID = CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID;
		CastOptions castOptions = new CastOptions.Builder()
				.setReceiverApplicationId(ID).build();
		return castOptions;
	}

	@Override
	public List<SessionProvider> getAdditionalSessionProviders(Context context) {
		return null;
	}

}
