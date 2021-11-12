package co.chu3la.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.springframework.stereotype.Service;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.annotations.PdfTextMarkupAnnotationWidget;
import com.spire.pdf.graphics.PdfRGBColor;

@Service
public class PdfService {
	
	public void extractHighlightedWords(String input) {
        //Load the PDF file  
        PdfDocument pdf = new PdfDocument();  
        pdf.loadFromFile(input);  
        //Create a new txt file to save the extracted text  
        String result = "/home/chu3la/Documents/thanks.txt";  
        File file = new File(result);  
        if (!file.exists()) {  
            file.delete();  
        } 
        try {
        file.createNewFile();  
        FileWriter fw = new FileWriter(file, true);  
        BufferedWriter bw = new BufferedWriter(fw);  
        bw.write("Extracted highlighted text:");  
        System.out.println();
        PdfPageBase page = pdf.getPages().get(0);  
        for (int i = 0; i < page.getAnnotationsWidget().getCount(); i++) {  
            if (page.getAnnotationsWidget().get(i) instanceof PdfTextMarkupAnnotationWidget) {  
                PdfTextMarkupAnnotationWidget textMarkupAnnotation = (PdfTextMarkupAnnotationWidget) page.getAnnotationsWidget().get(i);  
                bw.write(page.extractText(textMarkupAnnotation.getBounds()));  
                //Get the highlighted color  
                PdfRGBColor color = textMarkupAnnotation.getColor();  
//                bw.write("Color=" + (color.getR() & 0XFF) + "," + (color.getG() & 0XFF) + "," + (color.getB() & 0XFF) + "\n");  
            }  
        }
        bw.flush();  
        bw.close();  
        fw.close();
        }catch(Exception e) {
        	System.err.println(e);
        }
        
    }  
}


