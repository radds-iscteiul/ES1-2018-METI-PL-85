package ui;

import server.MailServer;

public class ReceiveEmailWindow {


	public static void main(String[] args) {
		String pop3Host = "pop.gmail.com";
		String storeType = "pop3s";
		String user = "metiG85.2018@gmail.com";
		String password = "RbDi1802";
		
		
		MailServer mailServer = new MailServer(user, password);
		mailServer.receiveEmail(user, password);
		
	}
}
