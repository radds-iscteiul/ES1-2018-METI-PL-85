package engine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MyMessage {
	
	private String from;
	private Date time;
	private String header;
	private String message;
	private Set<Integer> conjindices;
	
	
	public MyMessage(String f, Date d, String h, String m) {
		this.from = f;
		this.time = d;
		this.header = h;
		this.message = m;
		this.conjindices=new HashSet<>();
	}

	

	public MyMessage(String header, String message) {
		this.header = header;
		this.message = message;
	}



	public String getFrom() {
		return from;
	}


	public Date getTime() {
		return time;
	}


	public String getHeader() {
		return header;
	}


	public String getMessage() {
		return message;
	}
	


	public int getNrOcorrencias() {
		return conjindices.size();
	}
	
	public void clearIndices() {
		conjindices.clear();
	}

	public Set<Integer> getConjindices() {
		return conjindices;
	}

	public void setConjindices(Set<Integer> conjindices) {
		this.conjindices = conjindices;
	}
	
}
