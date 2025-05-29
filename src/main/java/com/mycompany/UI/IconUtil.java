package com.mycompany.unipathui;

import javax.swing.*;

public class IconUtil 
{
    private static final ImageIcon icon = new ImageIcon(IconUtil.class.getResource("/icons/logo.png"));

    // Εφαρμογή στο JFrame
    public static void apply(JFrame frame) 
    {
        frame.setIconImage(icon.getImage());
    }

    // Εφαρμογή στο JDialog
    public static void apply(JDialog dialog) 
    {
        dialog.setIconImage(icon.getImage());
    }
}
