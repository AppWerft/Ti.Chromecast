/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 * Warning: This file is GENERATED, and should not be modified
 */
var bootstrap = kroll.NativeModule.require("bootstrap"),
	invoker = kroll.NativeModule.require("invoker"),
	Titanium = kroll.binding("Titanium").Titanium;

function moduleBootstrap(moduleBinding) {
	function lazyGet(object, binding, name, namespace) {
		return bootstrap.lazyGet(object, binding,
			name, namespace, moduleBinding.getBinding);
	}

	var module = moduleBinding.getBinding("ti.chromecast.TichromecastModule")["Tichromecast"];
	var invocationAPIs = module.invocationAPIs = [];
	module.apiName = "Tichromecast";

	function addInvocationAPI(module, moduleNamespace, namespace, api) {
		invocationAPIs.push({ namespace: namespace, api: api });
	}

		addInvocationAPI(module, "Tichromecast", "Tichromecast", "createDeviceManager");

			if (!("__propertiesDefined__" in module)) {		
		Object.defineProperties(module, {
			"DeviceManager": {
				get: function() {
					var DeviceManager = lazyGet(this, "ti.chromecast.DeviceManagerProxy", "DeviceManager", "DeviceManager");
					return DeviceManager;
				},
				configurable: true
			},
		
		});
		module.constructor.prototype.createDeviceManager = function() {
			return new module.DeviceManager(arguments);
		}
		}
		module.__propertiesDefined__ = true;
		return module;

}
exports.bootstrap = moduleBootstrap;
