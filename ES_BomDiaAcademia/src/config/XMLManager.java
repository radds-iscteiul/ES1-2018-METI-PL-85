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

import engine.FacebookService;
import engine.Service;
import engine.ServiceType;
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
	                    String key = "";
	                    String secret = "";
	                    String token = "";
	                    String tokenSecret = "";
	                    boolean ative = false;
	                    	                   
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
	                                case "ative":
	                                	ative = Boolean.valueOf(elementoFilho.getTextContent());
	                                	break;
	                                	
	                                case "key":
	                                	key = elementoFilho.getTextContent();
	                                	break;
	                                	
	                                case "secret":
	                                	secret = elementoFilho.getTextContent();
	                                	break;
	                                	
	                                case "token":
	                                	token = elementoFilho.getTextContent();
	                                	break;
	                                	
	                                case "tokensecret":
	                                	tokenSecret = elementoFilho.getTextContent();
	                                	break;
	                                	
	                            }
	                        }                        
	                    }
	                    if(nome.equals("Twitter")) {
	                    	listaServicos.add(new TwitterService(Integer.valueOf(id),nome,user,password,ative,watch,key,secret,token,tokenSecret));
	                    } else if(nome.equals("Facebook")) {
	                    	listaServicos.add(new FacebookService(Integer.valueOf(id),nome,user,password,ative,token));
	                    } else if(nome.equals("Email")) {
	                    	 listaServicos.add(new Service(Integer.valueOf(id),nome,user,password,ative));
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
	    	
	    	Element ative = configXML.createElement("ative");
	    	ative.appendChild(configXML.createTextNode(Boolean.toString(s.isAtive()))); 	
	    	service.appendChild(ative);
	    	
	    	if(s.getName() == ServiceType.TWITTER) {
	    		TwitterService twitterService = (TwitterService) s;
	    		Element key = configXML.createElement("key");
		    	key.appendChild(configXML.createTextNode(twitterService.getKey())); 	
		    	service.appendChild(key);
		    	
		    	Element secret = configXML.createElement("secret");
		    	secret.appendChild(configXML.createTextNode(twitterService.getSecret())); 	
		    	service.appendChild(secret);
		    	
		    	Element token = configXML.createElement("token");
		    	token.appendChild(configXML.createTextNode(twitterService.getToken())); 	
		    	service.appendChild(token);
		    	
		    	Element tokenSecret = configXML.createElement("tokenSecret");
		    	tokenSecret.appendChild(configXML.createTextNode(twitterService.getTokenSecret())); 	
		    	service.appendChild(tokenSecret);
		    	
	    	} else if(s.getName() == ServiceType.FACEBOOK) {
	    		FacebookService facebookService = (FacebookService) s;
	    		Element token = configXML.createElement("token");
		    	token.appendChild(configXML.createTextNode(facebookService.getToken())); 	
		    	service.appendChild(token);
	    	}
	    	return service;
	    }
}
