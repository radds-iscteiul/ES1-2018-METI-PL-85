package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import engine.Controller;
import engine.FacebookService;
import engine.MyMessage;
import engine.Service;
import engine.ServiceType;
import engine.TwitterService;
import server.FacebookServer;
import server.MailServer;
import server.TwitterServer;
/**
 * 
 * @author Rafael Dias
 *
 */
public class ServicePanel extends JPanel{
	
	private MainWindow mainWindow;
	
	public JList<Service> lista;

	private JButton pull = new JButton("Get messages");
	public JButton toggle = new JButton("Enable");
	public JButton editService = new JButton("Edit");
	
	public ServicePanel(MainWindow mw) {
		super();
		this.setLayout(new BorderLayout());
		mainWindow = mw;
		
		JPanel buttons = new JPanel();
		pull.setPreferredSize(new Dimension(150,25));
		toggle.setPreferredSize(new Dimension(100,25));
		editService.setPreferredSize(new Dimension(100, 25));
		buttons.add(pull);
		buttons.add(toggle);
		buttons.add(editService);
		this.add(buttons,BorderLayout.SOUTH);
		this.setVisible(true);
		
		this.setDefaultList(Controller.getInstance().getAllServices());
		this.setListeners();
		
	}
	private void setDefaultList(ArrayList<Service> s){
		Service [] values = new Service[s.size()];
		int i = 0;
		for(Service service : s){
			values[i] = service;
			i++;
		}
		lista = new JList(values);
		lista.setFixedCellHeight(25);
		JScrollPane scrollPane = new JScrollPane(lista);
		this.add(scrollPane,BorderLayout.CENTER);
		lista.setCellRenderer(new ServiceListCellRenderer());
	}
	
	private void setListeners() {
		toggle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Service selected = ServicePanel.this.lista.getSelectedValue();
				if(!ServicePanel.this.lista.isSelectionEmpty()) {
					Controller.getInstance().toogleServiceState(selected);
					ServicePanel.this.lista.repaint();
				}
			}
		});
		toggle.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!ServicePanel.this.lista.isSelectionEmpty()) {
					if(ServicePanel.this.lista.getSelectedValue().isAtive()){
						ServicePanel.this.toggle.setText("Disable");
					} else {
						ServicePanel.this.toggle.setText("Enable");
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!ServicePanel.this.lista.isSelectionEmpty()) {
					if(ServicePanel.this.lista.getSelectedValue().isAtive()){
						ServicePanel.this.toggle.setText("Disable");
					} else {
						ServicePanel.this.toggle.setText("Enable");
					}
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		lista.addListSelectionListener(new ListSelectionListener() {

			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(ServicePanel.this.lista.getSelectedValue().isAtive()){
					ServicePanel.this.toggle.setText("Disable");
				} else {
					ServicePanel.this.toggle.setText("Enable");
				}
				
			}
		});
		
		pull.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 List<Service> s = Controller.getInstance().getAtiveServices();
				 System.out.println("Serviços Ativos: ");
				 List<MyMessage> messages = new ArrayList<MyMessage>();
				 for (Service service : s) {
					if(service.getName() == ServiceType.TWITTER) {
						TwitterService twitterService = (TwitterService) service;
						TwitterServer ts = new TwitterServer(twitterService);
						messages.addAll(ts.getTweetsFromUser(twitterService.getWatch(), 10));
					}
					
					if(service.getName() == ServiceType.FACEBOOK) {
						FacebookService fbService = (FacebookService) service;
						FacebookServer fs = new FacebookServer(fbService);
						messages.addAll(fs.getTimelinePosts());
					}
					
					if(service.getName() == ServiceType.EMAIL) {
						MailServer emailServer = new MailServer(service.getUser(),service.getPassword());
						messages.addAll(emailServer.receiveEmail(service.getUser(), service.getPassword()));
					}
				}
				 Controller.getInstance().addAllMessages(messages);
				 mainWindow.updateMessageListUI();
				
			}
		});
		editService.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Service s = (Service)lista.getSelectedValue();
				if(s != null) {
					if(s instanceof TwitterService) {
						new TwitterEditionWindow((TwitterService)s);
					} else if(s instanceof FacebookService) {
						new FacebookEditionWindow((FacebookService)s);
					} else { //Email
						new ServiceEditionWindow(s);
					}
				}
			}
		});
	}
	
	public JList<Service> getServiceList() {
		return lista;
	}
	public JButton getMessagePullButton() {
		return pull;
	}
	public JButton getToggleButton() {
		return toggle;
	}
	public JButton getEditServiceButton() {
		return editService;
	}
}
