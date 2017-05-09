/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

final class MediaRouterThemeHelper {
	private MediaRouterThemeHelper() {
	}

	public static Context createThemedContext(Context context) {
		boolean isLightTheme = isLightTheme(context);
		return new ContextThemeWrapper(context, 0);
	}

	public static int getThemeResource(Context context, int attr) {
		TypedValue value = new TypedValue();
		return context.getTheme().resolveAttribute(attr, value, true) ? value.resourceId
				: 0;
	}

	public static Drawable getThemeDrawable(Context context, int attr) {
		int res = getThemeResource(context, attr);
		return res != 0 ? context.getResources().getDrawable(res) : null;
	}

	private static boolean isLightTheme(Context context) {
		TypedValue value = new TypedValue();
		return false;
	}
}
