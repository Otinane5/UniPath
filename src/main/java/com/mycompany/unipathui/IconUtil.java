package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class IconUtil {

    // Static ImageIcon loaded from resources
    private static final ImageIcon icon = new ImageIcon(IconUtil.class.getResource("/icons/logo.png"));

    // Apply to JFrame
    public static void apply(JFrame frame) {
        frame.setIconImage(icon.getImage());
    }

    // Apply to JDialog
    public static void apply(JDialog dialog) {
        dialog.setIconImage(icon.getImage());
    }
}
