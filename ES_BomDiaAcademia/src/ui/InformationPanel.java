package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import engine.Controller;
import engine.FilterType;
import engine.MyMessage;
import engine.Service;
import engine.ServiceType;

/**
 * 
 * @author Rafael Dias
 *
 */
public class InformationPanel extends JPanel{
	
	private MainWindow mainWindow;
	
	private JList<MyMessage> displayedMessages;
	private JButton composeEmail;
	private JComboBox<FilterType> filters;
	private JTextField keyword;
	
	public InformationPanel(MainWindow mw) {
		super();
		this.setLayout(new BorderLayout());
		
		this.mainWindow = mw;
		
		this.setDefaultList();
		composeEmail = new JButton("Send email");
		
		JScrollPane scrollPane = new JScrollPane(displayedMessages);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(composeEmail,BorderLayout.SOUTH);
		
		JPanel filterPanel = new JPanel();
		Dimension d = new Dimension(200,25);
		
		filters = new JComboBox<FilterType>(new FilterType[] {
			FilterType.DATE_DECRESCENT,FilterType.DATE_CRESCENT,FilterType.KEYWORD,FilterType.NONE
		});
		filters.setPreferredSize(d);
		keyword = new JTextField("");
		keyword.setPreferredSize(d);
		keyword.setEditable(false);
		keyword.setEnabled(false);
		
		filterPanel.add(filters);
		filterPanel.add(keyword);
		
		this.add(filterPanel,BorderLayout.NORTH);
		
		this.setListeners();
		this.setVisible(true);
		
	}
	
	private void setDefaultList() {
		DefaultListModel<MyMessage> model = new DefaultListModel<MyMessage>();
		displayedMessages = new JList<MyMessage>(model);
		displayedMessages.setCellRenderer(new MessageListCellRenderer());
		displayedMessages.setFixedCellHeight(30);
	}
	private void setListeners() {
		composeEmail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Service email = Controller.getInstance().getService(ServiceType.EMAIL);
				if(email != null) {
					new SendEmailWindow(email.getUser(), email.getPassword());
				} else {
					JOptionPane.showMessageDialog(null, "No Email account registed", "Warning",
					        JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		displayedMessages.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            MyMessage selectedMessage = (MyMessage)list.getSelectedValue();
		            if(selectedMessage != null) {
		            	new ReadMessageWindow(selectedMessage);
		            }
		        }
		    }
		});
	
		
		filters.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				InformationPanel.this.updateMessageUI();			
			}
		});
		keyword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<MyMessage> allMessages = Controller.getInstance().getAllMessages();
				displayedMessages.removeAll();
				DefaultListModel<MyMessage> newModel = new DefaultListModel<MyMessage>();
				for (MyMessage m : allMessages) {
					if(Controller.getInstance().wordFilter(m, InformationPanel.this.keyword.getText().trim())) {
						newModel.addElement(m);
					}
				}
				displayedMessages.setModel(newModel);
				
			}
		});
		keyword.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(keyword.isEditable() && keyword.getText().equals("insert keyword")) {
					keyword.setText("");
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(keyword.isEditable() && keyword.getText().isEmpty()) {
					keyword.setText("insert keyword");
				}
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void updateMessageUI() {
		
		FilterType type = (FilterType)filters.getSelectedItem();
		
		if(type.getFilterValue().equals("Keyword")) {	
			keyword.setEnabled(true);
			keyword.setEditable(true);
		} else {
			keyword.setEnabled(false);
			keyword.setEditable(false);
			keyword.setText("");
		
			displayedMessages.removeAll();
			List<MyMessage> allMessages = Controller.getInstance().getAllMessages();
			DefaultListModel<MyMessage> newModel = new DefaultListModel<MyMessage>();
			
			if(type.getFilterValue().equals("None")){									
	
			} else if(type.getFilterValue().equals("Recent")) {
				allMessages = Controller.getInstance().orderMessageByDateD(allMessages);
			} else if(type.getFilterValue().equals("Older")) {
				allMessages = Controller.getInstance().orderMessageByDateC(allMessages);
			}
			
			for (MyMessage m : allMessages) {
				newModel.addElement(m);
			}
			displayedMessages.setModel(newModel);
		}
		this.displayedMessages.updateUI();
	}
}
