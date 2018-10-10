package demo;

import java.util.Random;

public class Demo {

	int attempt = 1;
	boolean failed = false;
	
	public boolean play(int guess){
		Random r = new Random();
		int got = r.nextInt(guess+1);
		if(got == guess) {
			System.out.println("Congrats, u won");
			return true;
		} else {
			this.failed();
			return false;
		}	
	}
	private void failed(){
		System.out.println("Sorry, u lost");
		failed = false ? true : failed;
		attempt++;
	}
	
}
