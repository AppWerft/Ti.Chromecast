Ti.Chromecast
=============
This is a Titanium Mobile for Chromecast and still under work. For iOS version please look here: https://github.com/ajwhite/titanium-chromecast
The project is still under construction. Thanks for help to Андрей and Manumaticx.


Usage:
------
~~~
// reference the module
var Chromecast = require('ti.chromecast');

var MediaRouter = require('ti.chromecast').createMediaRouter();
MediaRouter.addEventListener("onchanged",fucntion(e){
    e.casts.forEach(function(cast){
        console.log(cast.getName);
    });
    // after selection of user in list:
    MediaRouter.select(e.casts[index]);
});
~~~
