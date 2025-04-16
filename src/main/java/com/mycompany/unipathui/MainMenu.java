package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame
{
    public MainMenu()
    {
        setTitle("Main Menu (Πανεπιστήμιο)");
        setSize(500,400); // screen size 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater( ()-> 
        {
            new MainMenu().setVisible(true);  
        }
        );
    }
}