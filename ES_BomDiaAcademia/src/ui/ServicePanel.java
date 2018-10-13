package ui;

import java.awt.BorderLayout;
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
import engine.Service;

public class ServicePanel extends JPanel{
	
	private MainWindow mainWindow;
	
	private JList<Service> lista;
	private JButton pull = new JButton("Get messages");
	private JButton toggle = new JButton("Enable/Disable");
	
	public ServicePanel(MainWindow mw) {
		super();
		this.setLayout(new BorderLayout());
		mainWindow = mw;
		
		JPanel buttons = new JPanel();
		buttons.add(pull);
		buttons.add(toggle);
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
		JScrollPane scrollPane = new JScrollPane(lista);
		this.add(scrollPane,BorderLayout.CENTER);
		lista.setCellRenderer(new MyCellRenderer());
	}
	
	private void setListeners() {
		toggle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Service selected = ServicePanel.this.lista.getSelectedValue();
				Controller.getInstance().toogleServiceState(selected);
				ServicePanel.this.lista.repaint();
			}
		});
		toggle.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(ServicePanel.this.lista.getSelectedValue().isAtive()){
					ServicePanel.this.toggle.setText("Disable");
				} else {
					ServicePanel.this.toggle.setText("Enable");
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
				 for (Service service : s) {
					 System.out.println(service.toString());
				}
				
			}
		});
	}
	
}
