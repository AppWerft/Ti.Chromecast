Ti.Chromecast
=============
This is a Titanium Mobile for Chromecast and still under work. For iOS version please look here: https://github.com/ajwhite/titanium-chromecast
The project is still under construction. Thanks for help to Андрей Ткаченко and Manumaticx.


Usage:
------

~~~
// reference the module
var Chromecast = require('ti.chromecast');

// create an instance of the device manager
var deviceManager = Chromecast.createDeviceManager({
    app: "APP_ID" // for custome player required or "DEFAULT_MEDIA_RECEIVER"
});

// listen for new devices
deviceManager.addEventListener('deviceOnline', function (e) {
    var device = e.device;

//connect to device
if (!deviceManager.isConnected()) {
    device.connect(function () {
        device.startApplication(function () {
            device.sendJsonMessage({foo: 'bar'});
        });
    });
}



~~~
