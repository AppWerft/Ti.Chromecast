# Ti.Chromecast
This is a Titanium module for Google's chromecast and still under construction. For iOS version please look here: https://github.com/ajwhite/titanium-chromecast. Thanks for help to Андрей and Manumaticx.

![](https://avatars0.githubusercontent.com/u/4933765?v=3&s=200)

## Attention!!

The used google library (closed source) suite uses more then one aar. Every module can only contains one res folder. Therefore the build of app needs a patch. YOu find it in android/ti-cli-5  (or ti-cli-6) folder. This patch uses the aars in aar folder of module to create R.classes.

## Usage:

### Browsing of chromcasts:
```javascript
// reference the module
var Chromecast = require('ti.chromecast');

var MediaRouter = Chromecast.createMediaRouter({
    lifecycleContainer : window  // for liefcycle management of discoverer
});
// you can ask:
var routes = MediaRouter.getRoutes();

// or listen to changes
MediaRouter.addEventListener("onchanged",fucntion(e){
    e.routes.forEach(function(route){
        console.log(route.getName());
    });
});
```
With this result you can build a selector for device. I.e. an Ti.UI.OptionDialog().

### Selecting of route (device)

```javascript
Route.select();
Route.addEventListener("selected",function(){
});
```
