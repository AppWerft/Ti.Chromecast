package ti.googlecast;

import android.net.Uri;

import androidx.mediarouter.media.MediaRouter.RouteInfo;

import org.appcelerator.kroll.KrollDict;

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
