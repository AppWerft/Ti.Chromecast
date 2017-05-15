/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.googlecast;

import java.util.ArrayList;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.support.v7.media.MediaControlIntent;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteInfo;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.AsyncResult;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiMessenger;
import org.appcelerator.titanium.TiApplication;

import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastContext;

@Kroll.module(name = "Tichromecast", id = "ti.googlecast")
public class ChromecastModule extends KrollModule {
	public static final String LCAT = "TiGC🎈🎈";

	@Kroll.constant
	public final int DEVICE_TYPE_BLUETOOTH = MediaRouter.RouteInfo.DEVICE_TYPE_BLUETOOTH;
	@Kroll.constant
	public final int DEVICE_TYPE_SPEAKER = MediaRouter.RouteInfo.DEVICE_TYPE_SPEAKER;
	@Kroll.constant
	public final int DEVICE_TYPE_TV = MediaRouter.RouteInfo.DEVICE_TYPE_TV;
	@Kroll.constant
	public final int DEVICE_TYPE_UNKNOWN = MediaRouter.RouteInfo.DEVICE_TYPE_UNKNOWN;

	@Kroll.constant
	public final int CONNECTION_STATE_DISCONNECTED = MediaRouter.RouteInfo.CONNECTION_STATE_DISCONNECTED;
	@Kroll.constant
	public final int CONNECTION_STATE_CONNECTING = MediaRouter.RouteInfo.CONNECTION_STATE_CONNECTING;
	@Kroll.constant
	public final int CONNECTION_STATE_CONNECTED = MediaRouter.RouteInfo.CONNECTION_STATE_CONNECTED;

	public final int NOTFOUND = -1;
	public static int instance = 0;

	public ChromecastModule() {
		super();
		getMainHandler().obtainMessage(MSG_MEDIAROUTER_INIT).sendToTarget();

	}

	// Standard Debugging variables

	final String DEFAULTID = CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID;
	@SuppressWarnings("unused")
	private MediaRouteSelector mediaRouteSelector;
	private static final int MSG_FIRST_ID = KrollModule.MSG_LAST_ID + 1;
	private static final int MSG_MEDIAROUTER_INIT = MSG_FIRST_ID + 99;
	private static final int MSG_MEDIAROUTER_START = MSG_FIRST_ID + 100;
	private static final int MSG_MEDIAROUTER_STOP = MSG_FIRST_ID + 101;
	private static final int MSG_MEDIAROUTER_SELECT = MSG_FIRST_ID + 102;
	private static final int MSG_MEDIAROUTER_GET = MSG_FIRST_ID + 103;

	protected static final int MSG_LAST_ID = MSG_FIRST_ID + 999;
	private CastDevice selectedDevice;
	private CastContext castContext;
	private Context ctx;
	private MediaRouter mediaRouter;
	private MediaRouter.Callback mediaRouterCallback = new MediaRouterCallback();
	private static ArrayList<RouteInfo> routeInfos = new ArrayList<RouteInfo>();

	@Override
	public boolean handleMessage(Message msg) {
		AsyncResult result = null;
		switch (msg.what) {
		case MSG_MEDIAROUTER_GET: {
			result = (AsyncResult) msg.obj;
			result.setResult(handleGetRoutes());
			return true;

		}
		case MSG_MEDIAROUTER_START: {
			handleStartRouter();
			return true;
		}
		case MSG_MEDIAROUTER_INIT: {
			handleInitRouter();
			return true;
		}
		case MSG_MEDIAROUTER_STOP: {
			handleStop();
			return true;
		}
		case MSG_MEDIAROUTER_SELECT: {
			handleSelectRoute((RouteInfoProxy) msg.obj);
			return true;
		}
		default: {
			return super.handleMessage(msg);
		}
		}
	}

	private KrollFunction onChanged;

	/* this runs (I hope so) in main thread */

	private void handleInitRouter() {
		Log.d(LCAT, "–––––––––––––––––––––––– .. handleInitRouter()");
		ctx = TiApplication.getInstance().getApplicationContext();
		mediaRouter = MediaRouter.getInstance(ctx);
		castContext = CastContext.getSharedInstance(ctx);
		// https://github.com/googlecast/MediaRouter-Cast-Button-android/tree/master/src/com/example/mediarouter

		mediaRouteSelector = new MediaRouteSelector.Builder()
				.addControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO)
				.addControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO)
				.addControlCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK)
				.addControlCategory(
						CastMediaControlIntent
								.categoryForRemotePlayback(DEFAULTID)) //
				.build();
		Log.d(LCAT, "mediaRouteSelector built");
		// Create a MediaRouter callback for discovery events

		// in onResume, currently here TODO

	}

	private void handleStartRouter() {
		instance++;
		Log.d(LCAT, ">>>>>>>>>>>>> handleStartRouter " + instance);
		if (mediaRouter == null)
			handleInitRouter();
		mediaRouter.addCallback(mediaRouteSelector, mediaRouterCallback,
				MediaRouter.CALLBACK_FLAG_PERFORM_ACTIVE_SCAN);
		for (RouteInfo route : mediaRouter.getRoutes()) {
			if (!route.isDefault()) {
				Log.d(LCAT, " mediaRouter.getRoutes found a route !!!!!!!");
				Log.d(LCAT, route.toString());
				routeInfos.add(route);
			}
		}
		sendRoutingChangesBackToJavascriptLayer();
	}

	private void sendRoutingChangesBackToJavascriptLayer() {
		if (routeInfos != null) {
			List<KrollDict> list = new ArrayList<KrollDict>();
			KrollDict kd = new KrollDict();
			for (RouteInfo route : routeInfos) {
				list.add(Utils.toKrollDict(route));
			}
			kd.put("routes", list.toArray());
			if (onChanged != null) {
				List<RouteInfoProxy> plist = new ArrayList<RouteInfoProxy>();
				for (RouteInfo route : routeInfos) {
					plist.add(new RouteInfoProxy(route));
				}
				onChanged.call(getKrollObject(), plist.toArray());
			}
			if (hasListeners("changed")) {

				fireEvent("changed", kd);
				// routeInfos.toArray(new RouteInfoProxy[routeInfos.size()]));
			}
		}
	}

	@Kroll.method
	public void selectRoute(RouteInfoProxy proxy) {
		getMainHandler().obtainMessage(MSG_MEDIAROUTER_SELECT, proxy)
				.sendToTarget();

	}

	private void handleSelectRoute(RouteInfoProxy proxy) {
		if (mediaRouter != null)
			mediaRouter.selectRoute(proxy.route);
		else
			Log.w(LCAT, "mediaRouter was null, cannot selectRoute");
	}

	private RouteInfoProxy[] handleGetRoutes() {
		Log.d(LCAT, "handleGetRoute " + routeInfos.size());
		List<RouteInfoProxy> list = new ArrayList<RouteInfoProxy>();
		for (RouteInfo route : routeInfos) {
			list.add(new RouteInfoProxy(route));
		}
		//
		return (RouteInfoProxy[]) (list.toArray(new RouteInfoProxy[routeInfos
				.size()]));
	}

	@Kroll.method
	public RouteInfoProxy[] getRoutes() {
		ArrayList<RouteInfoProxy> proxyList = new ArrayList<RouteInfoProxy>();
		for (RouteInfo route : routeInfos) {
			proxyList.add(new RouteInfoProxy(route));
		}
		if (!proxyList.isEmpty())
			return proxyList.toArray(new RouteInfoProxy[proxyList.size()]);
		return null;
		//
		// return proxyList.toArray(new RouteInfoProxy[proxyList.size()]);

		// forces to Main thread:
		// return (RouteInfoProxy[]) TiMessenger
		// .sendBlockingMainMessage(getMainHandler().obtainMessage(
		// MSG_MEDIAROUTER_GET));
	}

	@Kroll.method
	public void stopBrowser() {
		// forces to Main thread:
		getMainHandler().obtainMessage(MSG_MEDIAROUTER_STOP).sendToTarget();
	}

	@Kroll.method
	public void stop() {
		// forces to Main thread:
		getMainHandler().obtainMessage(MSG_MEDIAROUTER_STOP).sendToTarget();
	}

	@Kroll.method
	public void start() {
		Log.d(LCAT, "public void start()");
		getMainHandler().obtainMessage(MSG_MEDIAROUTER_START).sendToTarget();
	}

	private class MediaRouterCallback extends MediaRouter.Callback {
		MediaRouterCallback() {
			Log.d(LCAT, "MediaRouterCallback init");
		}

		@Override
		public void onRouteAdded(MediaRouter router, RouteInfo route) {
			KrollDict kd = new KrollDict();
			// Add route to list of discovered routes
			synchronized (this) {
				if (!route.isDefault()
						&& getIndexInRoutesList(route) == NOTFOUND) {
					Log.d(LCAT, ">>>>>>>> onRouteAdded " + route);
					routeInfos.add(route);
					sendRoutingChangesBackToJavascriptLayer();
				} else
					Log.d(LCAT, "route was always part");
			}
		}

		@Override
		public void onRouteRemoved(MediaRouter router,
				MediaRouter.RouteInfo route) {
			Log.d(LCAT, "<<<<<<onRouteRemoved: info=" + route);
			synchronized (this) {
				Log.d(LCAT, "onRouteRemoved");
				int index = getIndexInRoutesList(route);
				if (index != NOTFOUND) {
					routeInfos.remove(index);
					sendRoutingChangesBackToJavascriptLayer();
				}
			}
		}

		@Override
		public void onRouteSelected(MediaRouter router, RouteInfo info) {
			// Log.d(LCAT, "onRouteSelected: info=" + info);
			selectedDevice = CastDevice.getFromBundle(info.getExtras());
			// Log.d(LCAT, selectedDevice.getFriendlyName());
			// Log.d(LCAT, selectedDevice.toString());
			// Just display a message for now; In a real app this would be the
			// hook to connect to the selected device and launch the receiver
			// app

		}

		@Override
		public void onRouteUnselected(MediaRouter router, RouteInfo route) {
			Log.d(LCAT, "<<<<<<<<<onRouteUnselected: info=" + route);
			selectedDevice = null;
		}
	}

	private int getIndexInRoutesList(RouteInfo route) {
		for (int i = 0; i < routeInfos.size(); i++) {
			RouteInfo routeInfo = routeInfos.get(i);
			if (routeInfo.equals(route)) {
				return i;
			}
		}
		return NOTFOUND;
	}

	private void handleStop() {
		routeInfos.clear();
		if (mediaRouter != null)
			mediaRouter.removeCallback(mediaRouterCallback);
	}

	@Override
	public void onStart(Activity activity) {
		super.onStart(activity);
		Log.d(LCAT, "[MODULE LIFECYCLE EVENT] start");
	}

	@Override
	public void onStop(Activity activity) {
		stopBrowser();
		super.onStop(activity);
	}

	@Override
	public void onPause(Activity activity) {
		stopBrowser();
		super.onPause(activity);
	}

	@Override
	public void onResume(Activity activity) {
		// start();
		super.onResume(activity);
	}

	@Override
	public void onDestroy(Activity activity) {
		stopBrowser();
		super.onDestroy(activity);
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
	}
}
