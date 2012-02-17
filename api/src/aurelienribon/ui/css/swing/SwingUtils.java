package aurelienribon.ui.css.swing;

import aurelienribon.ui.css.StyleRule;
import aurelienribon.ui.css.StyleRuleSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Paint;
import java.net.URL;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class SwingUtils {
	public static Color asColor(StyleRuleSet rs, StyleRule rule, int paramId) {
		return (Color) rs.getParams(rule).get(paramId);
	}

	public static Paint asPaint(StyleRuleSet rs, StyleRule rule, int paramId) {
		return (Paint) rs.getParams(rule).get(paramId);
	}

	public static Insets asInsets(StyleRuleSet rs, StyleRule rule) {
		List<Object> params = rs.getParams(rule);
		if (params.get(0) == null) return null;

		int top, left, bottom, right;

		if (params.size() == 1) {
			top = left = bottom = right = (Integer) params.get(0);
			return new Insets(top, left, bottom, right);
		}

		if (params.size() == 2) {
			top = bottom = (Integer) params.get(0);
			left = right = (Integer) params.get(1);
			return new Insets(top, left, bottom, right);
		}

		if (params.size() == 4) {
			top = (Integer) params.get(0);
			left = (Integer) params.get(1);
			bottom = (Integer) params.get(2);
			right = (Integer) params.get(3);
			return new Insets(top, left, bottom, right);
		}

		assert false;
		return null;
	}

	public static int asHAlign(StyleRuleSet rs, StyleRule rule, int paramId) {
		String val = (String) rs.getParams(rule).get(paramId);

		if (val.equals("leading")) return SwingConstants.LEADING;
		else if (val.equals("trailing")) return SwingConstants.TRAILING;
		else if (val.equals("left")) return SwingConstants.LEFT;
		else if (val.equals("right")) return SwingConstants.RIGHT;
		else if (val.equals("center")) return SwingConstants.CENTER;

		assert false;
		return -1;
	}

	public static int asVAlign(StyleRuleSet rs, StyleRule rule, int paramId) {
		String val = (String) rs.getParams(rule).get(paramId);

		if (val.equals("bottom")) return AbstractButton.BOTTOM;
		else if (val.equals("top")) return AbstractButton.TOP;
		else if (val.equals("center")) return AbstractButton.CENTER;

		assert false;
		return -1;
	}

	public static Font asFont(StyleRuleSet rs, StyleRule rule) {
		List<Object> params = rs.getParams(rule);
		if (params.get(0) instanceof Font) return (Font) params.get(0);

		String p1 = (String) params.get(0);
		String p2 = (String) params.get(1);
		int p3 = (Integer) params.get(2);

		int style = -1;

		if (p2.equals("plain")) style = Font.PLAIN;
		else if (p2.equals("italic")) style = Font.ITALIC;
		else if (p2.equals("bold")) style = Font.BOLD;
		else assert false;

		return new Font(p1, style, p3);
	}

	public static Icon asIcon(StyleRuleSet rs, StyleRule rule, int paramId) {
		Object param = rs.getParams(rule).get(paramId);

		if (param instanceof URL) return new ImageIcon((URL) param);
		if (param instanceof String) return new ImageIcon((String) param);
		if (param instanceof Icon) return (Icon) param;
		if (param == null) return null;

		assert false;
		return null;
	}

	public static Border asBorder(StyleRuleSet rs, StyleRule rule, int paramId) {
		return (Border) rs.getParams(rule).get(paramId);
	}
}