var appc = require('node-appc');
var Builder = require('titanium-sdk/lib/builder');
var DOMParser = require('xmldom').DOMParser;
var fs = require('fs');
var path = require('path');
var util = require('util');
var wrench = require('wrench');

var i18nLib = appc.i18n(__dirname);
var __ = i18nLib.__;
var xml = appc.xml;

function AndroidBaseBuilder() {
	Builder.apply(this, arguments);
}

util.inherits(AndroidBaseBuilder, Builder);

AndroidBaseBuilder.prototype.writeXmlFile = function writeXmlFile(srcOrDoc, dest) {
	var filename = path.basename(dest),
		destExists = fs.existsSync(dest),
		destDir = path.dirname(dest),
		srcDoc = typeof srcOrDoc == 'string' ? (new DOMParser({ errorHandler: function(){} }).parseFromString(fs.readFileSync(srcOrDoc).toString(), 'text/xml')).documentElement : srcOrDoc,
		destDoc,
		dom = new DOMParser().parseFromString('<resources/>', 'text/xml'),
		root = dom.documentElement,
		nodes = {},
		_t = this,
		byName = function (node) {
			var n = xml.getAttr(node, 'name');
			if (n) {
				if (nodes[n] && n !== 'app_name') {
					_t.logger.warn(__('Overwriting XML node %s in file %s', String(n).cyan, dest.cyan));
				}
				nodes[n] = node;
			}
		},
		byTagAndName = function (node) {
			var n = xml.getAttr(node, 'name');
			if (n) {
				nodes[node.tagName] || (nodes[node.tagName] = {});
				if (nodes[node.tagName][n] && n !== 'app_name') {
					_t.logger.warn(__('Overwriting XML node %s in file %s', String(n).cyan, dest.cyan));
				}
				nodes[node.tagName][n] = node;
			}
		};

	// If we don't deal with a resource set just try to copy it over
	if (srcDoc.tagName !== 'resources') {
		this.logger.debug(__('Copying %s => %s', srcOrDoc.cyan, dest.cyan));
		fs.existsSync(destDir) || wrench.mkdirSyncRecursive(destDir);
		destExists && fs.unlinkSync(dest);
		fs.writeFileSync(dest, '<?xml version="1.0" encoding="UTF-8"?>\n' + srcDoc.toString());
		return;
	}

	if (destExists) {
		// we're merging
		destDoc = (new DOMParser({ errorHandler: function(){} }).parseFromString(fs.readFileSync(dest).toString(), 'text/xml')).documentElement;
		xml.forEachAttr(destDoc, function (attr) {
			root.setAttribute(attr.name, attr.value);
		});
		if (typeof srcOrDoc == 'string') {
			this.logger.debug(__('Merging %s => %s', srcOrDoc.cyan, dest.cyan));
		}
	} else {
		// copy the file, but make sure there are no dupes
		if (typeof srcOrDoc == 'string') {
			this.logger.debug(__('Copying %s => %s', srcOrDoc.cyan, dest.cyan));
		}
	}

	xml.forEachAttr(srcDoc, function (attr) {
		root.setAttribute(attr.name, attr.value);
	});

	switch (filename) {
		case 'arrays.xml':
		case 'attrs.xml':
		case 'bools.xml':
		case 'colors.xml':
		case 'dimens.xml':
		case 'ids.xml':
		case 'integers.xml':
		case 'strings.xml':
			destDoc && xml.forEachElement(destDoc, byName);
			xml.forEachElement(srcDoc, byName);
			Object.keys(nodes).forEach(function (name) {
				root.appendChild(dom.createTextNode('\n\t'));
				if (filename == 'strings.xml') {
					nodes[name].setAttribute('formatted', 'false');
				}
				root.appendChild(nodes[name]);
			});
			break;

		default:
			destDoc && xml.forEachElement(destDoc, byTagAndName);
			xml.forEachElement(srcDoc, byTagAndName);
			Object.keys(nodes).forEach(function (tag) {
				Object.keys(nodes[tag]).forEach(function (name) {
					root.appendChild(dom.createTextNode('\n\t'));
					root.appendChild(nodes[tag][name]);
				});
			});
			break;
	}

	root.appendChild(dom.createTextNode('\n'));
	fs.existsSync(destDir) || wrench.mkdirSyncRecursive(destDir);
	destExists && fs.unlinkSync(dest);
	fs.writeFileSync(dest, '<?xml version="1.0" encoding="UTF-8"?>\n' + dom.documentElement.toString());
};

module.exports = AndroidBaseBuilder;