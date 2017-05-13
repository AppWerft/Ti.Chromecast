# Ti.Chromecast
This is a Titanium module for Google's chromecast and still under construction. For iOS version please look here: https://github.com/ajwhite/titanium-chromecast. Thanks for help to Андрей and Manumaticx.

![](https://avatars0.githubusercontent.com/u/4933765?v=3&s=200)

## Attention!!

The used google library (closed source) suite uses more then one aar. Every module can only contains one res folder. Therefore the build of app needs a patch. YOu find it in android/ti-cli-5  (or ti-cli-6) folder. This patch uses the aars in aar folder of module to create R.classes.

## Usage:
~~~
// reference the module
var Chromecast = require('ti.chromecast');

var MediaRouter = Chromecast.createMediaRouter();
MediaRouter.addEventListener("onchanged",fucntion(e){
    e.casts.forEach(function(cast){
        console.log(cast.getName);
    });
    // after selection of user in list:
    MediaRouter.select(e.casts[index]);
});
~~~
