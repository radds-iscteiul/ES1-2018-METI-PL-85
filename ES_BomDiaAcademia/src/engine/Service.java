package engine;

public class Service {
	
	private int id;
	private String name;
	private String endereco;
	private String user;
	private String password;
	
	private boolean ative = true;
	
	public Service(int id, String n, String e, String u, String p) {
		this.id = id;
		this.name = n;
		this.endereco = e;
		this.user = u;
		this.password = p;
	}
	public boolean toogleAtive() {
		ative = !ative;
		return ative;
	}
	public String getName() {
		return this.name;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public boolean isAtive() {
		return this.ative;
	}
	@Override
	public String toString() {
		return this.name+ " ; " + this.endereco;
	}

}
