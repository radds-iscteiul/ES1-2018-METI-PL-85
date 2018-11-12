package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.FacebookService;
import server.FacebookServer;

public class SendFacebookWindow extends SendWindow{

	private FacebookServer fbserver;
	
	private JButton loadImage;
	private JButton loadGIF;
	private JComboBox<String> location;
	private JComboBox<String> list;
	private String[] locations = { "Timeline", "Group", "Page" };
	private String[] locations2 = { "a", "b", "c" };
	private GridBagConstraints c = new GridBagConstraints();
	DefaultComboBoxModel model;
	
	public SendFacebookWindow(FacebookService fbs) {
		super();
		fbserver = new FacebookServer(fbs);
		
		labels = new JPanel(new GridBagLayout());
		startComponents();
		this.pack();
	}
	
	private void startComponents() {
		service = new JLabel("Where do you want to post?",JLabel.TRAILING);
		service.setFont(new Font("Serif", Font.BOLD, 22));
		
		c.gridx=1;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
		labels.add(service,c);
		
		//JLabel a = new JLabel();
		//labels.add(a);
		
		location = new JComboBox<>(locations);
		c.gridx=0;
		c.gridy=1;
		labels.add(location,c);
		location.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(location.getSelectedItem().toString());
				String[] s;
				switch (location.getSelectedItem().toString()) {
				case "Group":
					model.removeAllElements();
					for (int i = 0; i < fbserver.getUserGroups().size(); i++) {
						model.addElement(fbserver.getUserGroups().get(i).getName());
					}
					break;
				case "Page":
					model.removeAllElements();
					for (int i = 0; i < fbserver.getUserPages().size(); i++) {
						model.addElement(fbserver.getUserPages().get(i).getName());
					}
					break;
				case "Timeline":
					model.removeAllElements();
					model.addElement("ISCTE-IUL");
					model.addElement("IShitTécnico");
					break;
				default:
					break;
				}
				
				pack();
			}
			
		});
		list = new JComboBox<>(locations2);
		list.setPreferredSize(new Dimension(275, 25));
		model = (DefaultComboBoxModel) list.getModel();
		c.gridx=1;
		c.gridy=1;
		labels.add(list,c);
		
		
		//SpringUtilities.makeCompactGrid(labels, 2, 2, 5, 5, 5, 5);
		this.add(labels, BorderLayout.NORTH);
		
		body.setText("What are you thinking off?");
		
		loadImage = new JButton("Load Image");
		loadGIF = new JButton("Load GIF");
		this.buttons.add(loadImage);
		this.buttons.add(loadGIF);
		this.add(buttons, BorderLayout.SOUTH);
		this.send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (location.getSelectedItem().toString()) {
				case "Group":
					fbserver.postStatusToFacebookGroup(list.getSelectedItem().toString(), body.getText());
					break;
				case "Timeline":
					fbserver.postStatusToFacebookTimeline(body.getText());
					break;
				case "Page":
					fbserver.postStatusToFacebookPage(list.getSelectedItem().toString(), body.getText());
					break;
				default:
					break;
				}				
			}
		});
		this.setVisible(true);
	}
}
