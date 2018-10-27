package ui;

import java.util.Date;

import engine.EmailMessage;
import engine.MyMessage;

public class ReadMessageWindow extends MessageWindow{

	public ReadMessageWindow(MyMessage m) {
		super();
		loadMessageDetails(m);
	}

	private void loadMessageDetails(MyMessage m) {
		this.from.setText(m.getFrom());
		this.from.setEditable(false);
		
		//this.to.setText(m.getTo());
		this.to.setEditable(false);
		
		//this.subject.setText(m.getHeader());
		this.subject.setEditable(false);
		
		this.body.setText(m.getMessage());
		this.body.setEditable(false);		
	}
	
	public static void main(String[] args) {
		ReadMessageWindow m = new ReadMessageWindow(new EmailMessage("radds", "r", "Oi", new Date(), "texto"));
	}
}
