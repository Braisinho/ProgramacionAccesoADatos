package ejercicio13;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class XML {

    private static Date[] dates = new Date[30];

    /**
     * Este metodo se encarga de generar el XML con todos sus elementos y valores
     */
    public static void createXML(){
        rellenarDatos();
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;
        TransformerFactory transformerFactory = null;
        Transformer transformer = null;
        DOMSource source = null;
        StreamResult result = null;

        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            Element rootElement = doc.createElement("discos");
            doc.appendChild(rootElement);
           for (int i = 1; i<= 26; i++){
               Element album = doc.createElement("album");
               Attr id = doc.createAttribute("id");
               id.setValue(String.valueOf(i));
               album.setAttributeNode(id);
               rootElement.appendChild(album);
               Element title = doc.createElement("titulo");
               title.setTextContent("album: " + i);
               album.appendChild(title);
               Element group = doc.createElement("grupo");
               group.setTextContent("Grupo: " +i);
               album.appendChild(group);
               Element date = doc.createElement("fecha");
               Element year = doc.createElement("anho");
               year.setTextContent(dates[i].getYear()+"");
               date.appendChild(year);
               Element month = doc.createElement("mes");
               month.setTextContent(dates[i].getMonth()+"");
               date.appendChild(month);
               Element day = doc.createElement("dia");
               day.setTextContent(dates[i].getDay()+"");
               date.appendChild(day);
               album.appendChild(date);
               Element gender = doc.createElement("genero");
               album.appendChild(gender);
               Element music = doc.createElement("canciones");
               Element song = doc.createElement("cancion");
               Element song2 = doc.createElement("cancion");
               song.setTextContent("Cancion: "+ i);
               song2.setTextContent("Cancion: " + i*2);
               music.appendChild(song);
               music.appendChild(song2);
               album.appendChild(music);
           }
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            File file = new File("src/main/java/ejercicio13/discs.xml");
            result = new StreamResult(file);
            transformer.transform(source,result);
            System.out.println("Guardado");

            open(file);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo auxiliar para rellenar las fechas de forma aleatoria
     */
    private static void rellenarDatos(){
        Calendar c =Calendar.getInstance();
        c.set(2000, Calendar.JANUARY,1);
        Date fechaInicio = c.getTime();
        c.set(2022,Calendar.NOVEMBER,7);
        Date fechaFin = c.getTime();
        for (int i = 0; i< dates.length; i++){
            ThreadLocalRandom r =ThreadLocalRandom.current();
            Date rnd = new Date(r.nextLong(fechaInicio.getTime(), fechaFin.getTime()));
            dates[i] = rnd;
        }
    }


    /**
     * Este metodo se encarga de abrir el archivo y obtener el nodo raiz.
     * @param file archivo xml que queremos leer
     */
    private static void open(File file){
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
        if (n.getNodeName().equals("album")){
            System.out.print("=="+n.getNodeName());
        }

        if (n.getAttributes() != null && n.getAttributes().getLength() > 0){
            NamedNodeMap attributes = n.getAttributes();
            for (int j = 0; j<attributes.getLength(); j++){
                Node attribute = attributes.item(j);
                System.out.print(" (" + attribute.getChildNodes().item(0).getTextContent() + ")==\n");
            }
        }

        NodeList nodeChildrens = n.getChildNodes();


        for (int i = 0; i<nodeChildrens.getLength(); i++){
            Node child = nodeChildrens.item(i);

            if (child .getNodeType() == Node.ELEMENT_NODE){
                list(child);

            }else if (!child.getTextContent().trim().isEmpty() && n.getNodeName().equals("cancion")) {
                System.out.print(child.getTextContent() + "\n");

            }
        }
    }
}
