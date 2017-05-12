#!/bin/bash

APPID=ti.googlecast
VERSION=1.0.20

#cp android/assets/* iphone/

JARFILE="~/Library/Application\ Support/Titanium/modules/android/$APPID/$VERSION/chromecast.jar"



cd android;rm -rf build/classes;ti --platform android build --build-only --sdk 5.5.1.GA.AAR; unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/; cd ..


zip -d ~/Library/Application\ Support/Titanium/modules/android/$APPID/$VERSION/chromecast.jar org/appcelerator/titanium/gen/bindings.json;

#cp android/dist/$APPID-android-$VERSION.zip .
#cp iphone/$APPID-iphone-$VERSION.zip .
