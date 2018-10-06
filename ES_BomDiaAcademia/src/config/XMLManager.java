package config;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLManager {
	    /**
	     * @param args the command line arguments
	     * @throws ParserConfigurationException 
	     * @throws IOException 
	     * @throws SAXException 
	     */
	    public void readXML(String path) throws ParserConfigurationException, SAXException, IOException {
	        
	            //objetos para construir e fazer a leitura do documento
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            
	            String totalPath = path.substring(2);
	            System.out.println(totalPath);
	            //abre e faz o parser de um documento xml de acordo com o nome passado no parametro
	            Document doc = builder.parse("file:" +totalPath);
	            
	            //cria uma lista de pessoas. Busca no documento todas as tag pessoa
	            NodeList listaDePessoas = doc.getElementsByTagName("servidor");
	            
	            //pego o tamanho da lista de pessoas
	            int tamanhoLista = listaDePessoas.getLength();
	            
	            //varredura na lista de pessoas
	            for (int i = 0; i < tamanhoLista; i++) {
	                
	                //pego cada item (pessoa) como um n� (node)
	                Node noPessoa = listaDePessoas.item(i);
	                
	                //verifica se o noPessoa � do tipo element (e n�o do tipo texto etc)
	                if(noPessoa.getNodeType() == Node.ELEMENT_NODE){
	                    
	                    //caso seja um element, converto o no Pessoa em Element pessoa
	                    Element elementoPessoa = (Element) noPessoa;
	                    
	                    //j� posso pegar o atributo do element
	                    String id = elementoPessoa.getAttribute("id");
	                    
	                    //imprimindo o id
	                    System.out.println("ID = " + id);      
	                    
	                    //recupero os nos filhos do elemento pessoa (nome, idade e peso)
	                    NodeList listaDeFilhosDaPessoa = elementoPessoa.getChildNodes();
	                    
	                    //pego o tamanho da lista de filhos do elemento pessoa
	                    int tamanhoListaFilhos = listaDeFilhosDaPessoa.getLength();
	                            
	                    //varredura na lista de filhos do elemento pessoa
	                    for (int j = 0; j < tamanhoListaFilhos; j++) {
	                        
	                        //crio um no com o cada tag filho dentro do no pessoa (tag nome, idade e peso)
	                        Node noFilho = listaDeFilhosDaPessoa.item(j);
	                        
	                        //verifico se s�o tipo element
	                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){
	                            
	                            //converto o no filho em element filho
	                            Element elementoFilho = (Element) noFilho;
	                            
	                            //verifico em qual filho estamos pela tag
	                            switch(elementoFilho.getTagName()){
	                                case "nome":
	                                    //imprimo o nome
	                                    System.out.println("NOME=" + elementoFilho.getTextContent());
	                                    break;
	                                    
	                                case "endereco":
	                                    //imprimo a idade
	                                    System.out.println("IDADE=" + elementoFilho.getTextContent());
	                                    break;
	                                    
	                                case "utilizador":
	                                    //imprimo o peso
	                                    System.out.println("PESO=" + elementoFilho.getTextContent());
	                                    break;
	                                case "password":
	                                    //imprimo o peso
	                                    System.out.println("PESO=" + elementoFilho.getTextContent());
	                                    break;
	                            }
	                        }
	                    }
	                }
	            }
	    }
	    

}
