package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import engine.FacebookMessage;
import engine.MyMessage;
import engine.TwitterMessage;
/**
 * 
 * @author Rafael Dias
 *
 */
public class MessageListCellRenderer extends JLabel implements ListCellRenderer<Object> {
    public MessageListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
    	MyMessage message = (MyMessage) value;
    	ImageIcon imageIcon;
    	if(message instanceof FacebookMessage) {
    		imageIcon = new ImageIcon("images/Facebook.png");
    	} else if(message instanceof TwitterMessage) {
    		imageIcon = new ImageIcon("images/Twitter.png");
    	} else {
    		imageIcon = new ImageIcon("images/Email.png");
    	}
    	
        setText(value.toString());
        setIcon(imageIcon);

        Color background = Color.WHITE;
        Color foreground = Color.BLACK;

       
        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

        // check if this cell is selected
        } else if (isSelected) {
        	
            Border blackline = BorderFactory.createLineBorder(Color.black);
            setBorder(blackline);

        // unselected, and not the DnD drop location
        } else {
        	setBorder(null);
        	
        };

     
        	setBackground(background);
        	setForeground(foreground);
       

        return this;
    }
}
