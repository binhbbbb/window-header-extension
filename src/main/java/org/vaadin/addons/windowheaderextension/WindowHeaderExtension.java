package org.vaadin.addons.windowheaderextension;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionServerRpc;
import org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionState;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.FontIcon;
import com.vaadin.ui.Window;

public class WindowHeaderExtension extends AbstractExtension {

    private final List<WindowButtonClickListener> listeners = new ArrayList<WindowButtonClickListener>();

    private WindowHeaderExtensionServerRpc rpc = new WindowHeaderExtensionServerRpc() {

        public void buttonClick() {
            for (WindowButtonClickListener listener : listeners) {
                listener.buttonClicked();
            }
        }
    };

    private WindowHeaderExtension() {
        registerRpc(rpc);
    }

    @Override
    public WindowHeaderExtensionState getState() {
        return (WindowHeaderExtensionState) super.getState();
    }

    /**
     * Add additional header button (icon) with tooltip to Window
     *
     * @param win
     *            Window
     * @param icon
     *            FontIcon
     * @param tooltipText
     *            text for button tooltip
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, FontIcon icon, String tooltipText,
            WindowButtonClickListener clickListener) {
        WindowHeaderExtension ex = new WindowHeaderExtension();
        ex.getState().iconHtml = icon.getHtml();
        ex.getState().tooltipText = tooltipText;
        ex.listeners.add(clickListener);
        ex.extend(win);
    }

    /**
     * Add addtitional header button (icon) to Window
     *
     * @param win
     *            Window
     * @param icon
     *            FontIcon
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, FontIcon icon,
            WindowButtonClickListener clickListener) {
        extend(win, icon, "", clickListener);
    }

}
