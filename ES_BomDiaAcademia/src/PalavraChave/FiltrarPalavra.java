package PalavraChave;

import java.util.HashSet;
import java.util.Set;



public class FiltrarPalavra {

	private String palavra;
	private Set<Integer> conjindices;
	
	public FiltrarPalavra (String palavra ){
		this.palavra=palavra;
		this.conjindices=new HashSet<>();
	}

	public String getPalavra() {
		return palavra;
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
	
	public int getNrOcorrencias() {
		return conjindices.size();
	}

	@Override
	public String toString() {
		return "FiltrarPalavra [palavra=" + palavra + ", conjindices=" + conjindices + "]";
	}

	public void procurar(String pedido){
	//	System.out.println(getNrOcorrencias() + " Antes");
		clearIndices();
	//	System.out.println(getNrOcorrencias() + " Depois");
		Set <Integer> conjindices = new HashSet <Integer>(); 
		for(int i = 0; ( i < palavra.length()-pedido.length()); i++) {

			String aux = palavra.substring(i, i+pedido.length());
			if( pedido.equals(aux)) {
				conjindices.add(i);
			}
		}

		setConjindices(conjindices);
	}


	
	
}
