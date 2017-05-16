# Ti.Chromecast
This is a Titanium module for controling Google's chromecast and WIP. Thanks for help to Андрей, Stefan and Manumaticx.

For iOS version please look here: https://github.com/ajwhite/titanium-chromecast. 

![](https://avatars0.githubusercontent.com/u/4933765?v=3&s=200)

## Attention!!

The used google library (google-play-service.cast and others) uses more then one aar. In current version of Titanium SDK every module can only contains one res folder. Therefore the build process of app needs a patch. You find it in android/ti-cli-5  (or ti-cli-6) folder. This patch uses the aars in aar folder of module to create R.classes.

## Constants

### Device types
- [x] DEVICE_TYPE_BLUETOOTH 
- [x] DEVICE_TYPE_SPEAKER 
- [x] DEVICE_TYPE_TV 
- [x] DEVICE_TYPE_UNKNOWN 
- [x] DEVICE_TYPE_BLUETOOTH 

### Connection state
- [x] CONNECTION_STATE_DISCONNECTED
- [x] CONNECTION_STATE_CONNECTING
- [x] CONNECTION_STATE_CONNECTED

### Category
- [x] CATEGORY_LIVE_AUDIO
- [x] CATEGORY_LIVE_VIDEO
- [x] CATEGORY_REMOTE_PLAYBACK

## Usage:

### Initialisation
First you have to put this line into app.js:
```javascript
require('ti.chromecast');
```

### Browsing for chromecasts:
```javascript
// reference the module
var Chromecast = require('ti.chromecast');

Chromecast.start({
    changed : function(routes) {
		routes.forEach(route) {
			console.log(route.getName())
		}
 	},
 	categories : [Cast.CATEGORY_LIVE_AUDIO, Cast.CATEGORY_REMOTE_PLAYBACK]
});
// you can ask:
var routes = MediaRouter.getRoutes();
routes && routes.forEach(function(){
	// call some methods, explained below
});

// or listen to changes
MediaRouter.addEventListener("onchanged",fucntion(e){
    e.routes.forEach(function(route){
       // call some methods, explained below
    });
});
```
You can use callback or event listener.
With stop you can stop the listener:
```javascript
MediaRouter.stop();    // after closing windo or hiding of dialog
```
With the result (array of routes) you can build a selector for device. I.e. a Ti.UI.OptionDialog().

## Methods of route
A route has these methods:

- [x] getName();
- [x] getDescription();
- [x] isSelected();
- [x] isEnabled();
- [x] getDeviceType();
- [x] getConnectionState()
- [x] select(); // next chapter

### Selecting of route (device)

```javascript
Route.select();
Route.addEventListener("selected",function(){
});
```

### Sending control request

- [x] playRemotePlayback()
- [x] enqueueRemotePlayback() 
- [x] startLiveAudio()
- [x] startLiveVideo()


