package io.explod.android.app.fonts;

import io.explod.android.app.iface.ISetTypeface;
import io.explod.android.app.view.exception.InvalidViewException;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class to handle the loading of
 * 
 * @author evan
 * 
 */
public class Fonts {

	/**
	 * Load a font from assets. Shortcut method.
	 * 
	 * @see {@link android.graphics.Typeface#createFromAsset(AssetManager, String)}
	 * 
	 * @param context
	 *            Originating context
	 * @param path
	 *            String path after the {@literal assets} folder.
	 * @return The loaded {@link android.graphics.Typeface}
	 */
	public static final Typeface loadFontFromAssets(Context context, String path) {
		final AssetManager assets = context.getAssets();
		final Typeface font = Typeface.createFromAsset(assets, path);
		return font;
	}

	/**
	 * Apply a {@link android.graphics.Typeface} to a list of {@link android.view.View} objects specified by ids. <br/>
	 * <br/>
	 * Supported view classes and subclasses are: <br/>
	 * - {@link android.widget.Button}<br/>
	 * - {@link android.widget.TextView}<br/>
	 * - {@link android.widget.EditText}<br/>
	 * - {@link android.widget.ImageButton}<br/>
	 * - {@link io.explod.android.app.iface.ISetTypeface}<br/>
	 * 
	 * @param font
	 *            The font to apply to the views
	 * @param root
	 *            Root {@link android.view.View} containing the subviews to search for
	 * @param viewIds
	 *            Comma seperated list of view resIds that are under {@value root}
	 * @throws InvalidViewException
	 *             Thrown when a {@link android.view.View} is found that is not one of the supported types
	 * 
	 */
	public static final void setViewFonts(Typeface font, View root, int... viewIds) throws InvalidViewException {
		for (final int viewId : viewIds) {
			final View view = root.findViewById(viewId);
			if (view instanceof TextView) {
				((TextView) view).setTypeface(font);
			} else if (view instanceof Button) {
				((Button) view).setTypeface(font);
			} else if (view instanceof EditText) {
				((EditText) view).setTypeface(font);
			} else if (view instanceof ISetTypeface) {
				((ISetTypeface) view).setTypeface(font);
			} else {
				final String message = String.format("Invalid view: #%x: %s", viewId, view);
				throw new InvalidViewException(message);
			}
		}
	}

}
