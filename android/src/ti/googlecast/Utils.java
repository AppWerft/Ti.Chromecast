package ti.googlecast;

import org.appcelerator.kroll.KrollDict;

import android.net.Uri;
import android.support.v7.media.MediaRouter.RouteInfo;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}

	public static KrollDict toKrollDict(RouteInfo route) {
		KrollDict kd = new KrollDict();
		kd.put("canDisconnect", route.canDisconnect());
		kd.put("name", route.getName());
		kd.put("description", route.getDescription());
		kd.put("enabled", route.isEnabled());
		kd.put("selected", route.isSelected());
		Uri uri = route.getIconUri();
		if (uri != null)
			kd.put("image", uri.toString());
		kd.put("connectionState", route.getConnectionState());
		kd.put("deviceType", route.getDeviceType());
		kd.put("provider", route.getProvider().toString());

		return kd;
	}

}
