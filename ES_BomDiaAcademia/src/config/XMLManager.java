package config;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import engine.Service;


public class XMLManager {
	    /**
	     * 
	     * @param args the command line arguments
	     * @throws ParserConfigurationException 
	     * @throws IOException 
	     * @throws SAXException 
	     */
	    public ArrayList<Service> readXML(String path) throws ParserConfigurationException, SAXException, IOException {
	        
	     
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            
	            String totalPath = path.substring(2);
	       
	            Document doc = builder.parse("file:" +totalPath);
	            
	           
	            NodeList listaDePessoas = doc.getElementsByTagName("servidor");
	            
	           
	            int tamanhoLista = listaDePessoas.getLength();
	            
	            ArrayList<Service> listaServicos = new ArrayList<Service>();
	            
	           
	            for (int i = 0; i < tamanhoLista; i++) {
	                	            
	                Node noPessoa = listaDePessoas.item(i);
	                	               
	                if(noPessoa.getNodeType() == Node.ELEMENT_NODE){
	                    	                 
	                    Element elementoPessoa = (Element) noPessoa;
	                    	                  
	                    String id = elementoPessoa.getAttribute("id");
	                    	                   
	                    NodeList listaDeFilhosDaPessoa = elementoPessoa.getChildNodes();
	                    	                    
	                    int tamanhoListaFilhos = listaDeFilhosDaPessoa.getLength();
	                     
	                    String nome = "";
	                    String endereco = "";
	                    String user = "";
	                    String password = "";
	                    	                  
	                    for (int j = 0; j < tamanhoListaFilhos; j++) {
	                        	                       
	                        Node noFilho = listaDeFilhosDaPessoa.item(j);
	                        	                      
	                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){
	                            	                           
	                            Element elementoFilho = (Element) noFilho;
	                                                        
	                            switch(elementoFilho.getTagName()){
	                                case "nome":
	                                    nome = elementoFilho.getTextContent();
	                                    break;
	                                    
	                                case "endereco":	                                   
	                                    endereco = elementoFilho.getTextContent();
	                                    break;
	                                    
	                                case "utilizador":
	                                    user = elementoFilho.getTextContent();
	                                    break;
	                                    
	                                case "password":
	                                    password = elementoFilho.getTextContent();
	                                    break;
	                            }
	                        }                        
	                    }
	                    listaServicos.add(new Service(Integer.valueOf(id),nome,endereco,user,password));
	                }
	            }
	            return listaServicos;
	    }
	

	    

}
