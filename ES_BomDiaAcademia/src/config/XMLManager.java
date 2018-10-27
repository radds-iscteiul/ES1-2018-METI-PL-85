package config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import engine.Service;
import engine.TwitterService;

/**
 * 
 * @author Rafael Dias
 *
 */
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
	                    String user = "";
	                    String password = "";
	                    String watch = "";
	                    	                   
	                    for (int j = 0; j < tamanhoListaFilhos; j++) {
	                        	                       
	                        Node noFilho = listaDeFilhosDaPessoa.item(j);
	                        	                      
	                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){
	                            	                           
	                            Element elementoFilho = (Element) noFilho;
	                                                        
	                            switch(elementoFilho.getTagName()){
	                                case "nome":
	                                    nome = elementoFilho.getTextContent();
	                                    break;
	   
	                                case "utilizador":
	                                    user = elementoFilho.getTextContent();
	                                    break;
	                                   
	                                case "watch":
	                                    watch = elementoFilho.getTextContent();
	                                    break;
	                                    
	                                case "password":
	                                    password = elementoFilho.getTextContent();
	                                    break;
	                            }
	                        }                        
	                    }
	                    if(nome.equals("Twitter")) {
	                    	listaServicos.add(new TwitterService(Integer.valueOf(id),nome,user,password,watch));
	                    } else if(nome.equals("Facebook")) {
	                    	listaServicos.add(new Service(Integer.valueOf(id),nome,user,password));
	                    } else if(nome.equals("Email")) {
	                    	 listaServicos.add(new Service(Integer.valueOf(id),nome,user,password));
	                    }
	                    
	                }
	            }
	            return listaServicos;
	    }
	

	    public void writeXML(ArrayList<Service> servicos) throws TransformerException, ParserConfigurationException {
	    	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	    	
	    	Document configXML = documentBuilder.newDocument();
	    	
	    	Element root = configXML.createElement("servidores");
	    	configXML.appendChild(root);
	    	
	    	Element s;
	    	for (Service service : servicos) {
	    		s = this.composeServiceToXML(service, configXML);
		    	root.appendChild(s);
			}
	    	    	
	    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    	Transformer transformer = transformerFactory.newTransformer();
	    	
	    	DOMSource documentoFonte = new DOMSource(configXML);
	    	StreamResult documentoFinal = new StreamResult(new File("C:\\Users\\Rafael Dias\\Desktop\\teste.xml"));
	    	
	    	transformer.transform(documentoFonte, documentoFinal);
	    }
	    
	    private Element composeServiceToXML(Service s, Document configXML) {
	    	Element service = configXML.createElement("servidor");
	    	Attr id = configXML.createAttribute("id");
	    	id.setValue(String.valueOf(s.getId()));
	    	service.setAttributeNode(id);
	    
	    	
	    	Element nome = configXML.createElement("nome");
	    	nome.appendChild(configXML.createTextNode(s.getName().toString())); 	
	    	service.appendChild(nome);
	    	
	    	Element utilizador = configXML.createElement("utilizador");
	    	utilizador.appendChild(configXML.createTextNode(s.getUser())); 	
	    	service.appendChild(utilizador);
	    	
	    	Element password = configXML.createElement("password");
	    	password.appendChild(configXML.createTextNode(s.getPassword())); 	
	    	service.appendChild(password);
	    	
	    	return service;
	    }

	    public static void main(String[] args) {
	    	
	    	ArrayList<Service> services = new ArrayList<Service>();
	    	services.add(new Service(1, "Email", "a@gmail.com", "password"));
			try {
				new XMLManager().writeXML(services);
			} catch (TransformerException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
