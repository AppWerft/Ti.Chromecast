#!/bin/bash

APPID=ti.googlecast
VERSION=1.0.8

#cp android/assets/* iphone/
#cd android;rm -rf build/classes;ti --platform android build --build-only --sdk 5.5.1.GA.AAR;  unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/;cd ..
#cd iphone/; python build.py;  unzip -uo  $APPID-iphone-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/;cd ..

cd android;rm -rf build/classes;ant -v;  unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/;cd ..



cp android/dist/$APPID-android-$VERSION.zip .
#cp iphone/$APPID-iphone-$VERSION.zip .
