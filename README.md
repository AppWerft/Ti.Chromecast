Ti.Chromecast
=============
This is a Titanium Mobile for Chromecast and still under work. For iOS version please look here: https://github.com/ajwhite/titanium-chromecast
The project is still under construction. Thanks for help to Андрей Ткаченко and Manumaticx.


Usage:
------
~~~
// reference the module
var Chromecast = require('ti.chromecast');

var deviceManager = require('ti.chromecast').createDeviceManager();
deviceManager.getMediaRouteSelector('DEFAULT_MEDIA_RECEIVER');// for custome player required or "DEFAULT_MEDIA_RECEIVER"
deviceManager.addEventListener('deviceOnline', function(e) {
    var device = e.device;
});



~~~
