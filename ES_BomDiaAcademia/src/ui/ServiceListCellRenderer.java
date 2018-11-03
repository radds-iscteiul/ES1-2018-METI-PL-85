package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import engine.Service;
/**
 * 
 * @author Rafael Dias
 *
 */
public class ServiceListCellRenderer extends JLabel implements ListCellRenderer<Object> {
    public ServiceListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
    	Service s = (Service) value;
    	ImageIcon imageIcon = new ImageIcon("images/" + s.getName().toString() + ".png");
    	System.out.println(imageIcon.getImageLoadStatus());
        setText(value.toString());
        setIcon(imageIcon);

        Color background;
        Color foreground;

       
        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

        // check if this cell is selected
        } else if (isSelected) {
        	
        	if(s.isAtive()){
        		background = Color.GREEN;
                foreground = Color.BLACK;
        	} else {
        		 background = Color.RED;
                 foreground = Color.BLACK;
        	}
            Border blackline = BorderFactory.createLineBorder(Color.black);
            setBorder(blackline);

        // unselected, and not the DnD drop location
        } else {
        	setBorder(null);
        	if(s.isAtive()){
        		background = Color.GREEN;
                foreground = Color.BLACK;
        	} else {
        		 background = Color.RED;
                 foreground = Color.BLACK;
        	}
        };

     
        	setBackground(background);
        	setForeground(foreground);
       

        return this;
    }
}
