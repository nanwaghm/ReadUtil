package uploadUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadFiles {

	public void ReadPDFFiles() throws IOException {
				 
	      File file = new File("D://Sample.pdf");
	      PDDocument document = PDDocument.load(file);	      
	      PDFTextStripper pdfStripper = new PDFTextStripper();
	      String text = pdfStripper.getText(document);
	      System.out.println(text);
	      document.close();
	}
	
	public void ReadExcelFiles() throws IOException {
		File file = new File("C:\\Users\\nandini.waghmare\\Downloads\\Test1.xlsx");   
		FileInputStream fis = new FileInputStream(file);    
		 
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(0);        
		XSSFRow row;
	    XSSFCell cell;

	    int rows; 
	    rows = sheet.getPhysicalNumberOfRows();

	    int cols =0; 
	    int tmp = 0;

	    for(int r = 0; r < rows; r++) {
	        row = sheet.getRow(r);
	        cols = row.getPhysicalNumberOfCells();
	        if(row != null) {
	            for(int c = 0; c < cols; c++) {
	                cell = row.getCell((short)c);
	                if(cell != null) {
	                	System.out.println("string is :"+cell.getStringCellValue());
	                }}}
	       	              
	    }
	}
	
	
	public void ReadXmlFiles() throws ParserConfigurationException, SAXException, IOException {
		
		File fXmlFile = new File("D:\\SampleXml.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();
        System.out.println();
        

        System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
        
        NodeList nList = doc.getElementsByTagName("Highscore");
        Node child = nList.item(0);
        
        NodeList nL = child.getChildNodes();    
        System.out.println("----------------------------");
        int i = 1;
        for (int temp = 0; temp < nL.getLength(); temp++) {
            Node nNode = nL.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println(i + "," + eElement.getAttribute("name") + "," + eElement.getAttribute("score"));
                i++;
            }
        }
        
	}
	
	

}
