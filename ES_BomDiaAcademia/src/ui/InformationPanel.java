package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import engine.Controller;
import engine.EmailMessage;
import engine.FilterType;
import engine.MyMessage;
import engine.Service;
import engine.ServiceType;

public class InformationPanel extends JPanel{
	
	private MainWindow mainWindow;
	
	private JList<MyMessage> displayedMessages;
	private JButton composeMessage;
	private JComboBox filters;
	private JTextField keyword;
	
	public InformationPanel(MainWindow mw) {
		super();
		this.setLayout(new BorderLayout());
		
		this.mainWindow = mw;
		
		DefaultListModel<MyMessage> model = new DefaultListModel<MyMessage>();
		displayedMessages = new JList<MyMessage>(model);
		composeMessage = new JButton("Send email");
		
		JScrollPane scrollPane = new JScrollPane(displayedMessages);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(composeMessage,BorderLayout.SOUTH);
		
		Service email = Controller.getInstance().getService(ServiceType.EMAIL);
		
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
		
		this.setListeners(email.getUser(),email.getPassword());
		this.setVisible(true);
		
	}
	
	private void setListeners(String user,String pw) {
		composeMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SendEmailWindow(user, pw);
			}
		});
		if(user == "" && pw == "") {
			composeMessage.setEnabled(false);
		}
		
		displayedMessages.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            EmailMessage selectedMessage = (EmailMessage)list.getSelectedValue();
		            if(selectedMessage != null) {
		            	new ReadMessageWindow(selectedMessage);
		            }
		        } else if (evt.getClickCount() == 3) {

		            // Triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
	
		filters.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FilterType type = (FilterType)filters.getSelectedItem();
				System.out.println(type.getFilterValue());
				
				if(type.getFilterValue().equals("Keyword")) {
					
					keyword.setEnabled(true);
					keyword.setEditable(true);
					keyword.setText("insert keyword");
					
				} else {
					keyword.setEnabled(false);
					keyword.setEditable(false);
					keyword.setText("");
				
					if(type.getFilterValue().equals("None")){									
						List<MyMessage> allMessages = Controller.getInstance().getAllMessages();
						displayedMessages.removeAll();
						DefaultListModel<MyMessage> newModel = new DefaultListModel<MyMessage>();
						for (MyMessage m : allMessages) {
							newModel.addElement(m);
						}
						displayedMessages.setModel(newModel);
					} 
				}
				
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
	}

}
