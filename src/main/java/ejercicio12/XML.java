package ejercicio12;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XML {


    /**
     * Este metodo se encarga de abrir el archivo y obtener el nodo raiz.
     * @param file archivo xml que queremos leer
     */
    public static void open(File file){
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;

        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();

            Document documento = builder.parse(file);

            list(documento.getDocumentElement());

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo utiliza la recursividad para recorrer los elementos del XML
     * @param n nodo a recorrer
     */
    private static void list(Node n){
        System.out.println(n.getNodeName());

        if (n.getAttributes() != null && n.getAttributes().getLength() > 0){
            NamedNodeMap attributes = n.getAttributes();
            for (int j = 0; j<attributes.getLength(); j++){
                Node attribute = attributes.item(j);
                list(attribute);
            }
        }

        NodeList nodeChildrens = n.getChildNodes();

        for (int i = 0; i<nodeChildrens.getLength(); i++){
            Node child = nodeChildrens.item(i);

            if (child .getNodeType() == Node.ELEMENT_NODE){
                list(child);

            } else if (!child.getTextContent().trim().isEmpty()) {
                System.out.print("  Valor: " +child.getTextContent() + "\n");

            }
        }
    }


}
