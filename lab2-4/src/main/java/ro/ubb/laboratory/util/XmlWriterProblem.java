package ro.ubb.laboratory.util;

import ro.ubb.laboratory.domain.BaseEntity;
import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XmlWriterProblem<ID, T extends BaseEntity<ID>> {

    private String fileName;

    public XmlWriterProblem(String fileName) {
        this.fileName = fileName;
    }

    private void writeFirstPart(FileWriter writer){
        try {
            String content = "";
            content += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + "\n";
            content += "<problems>" + "\n"; //here could be also name of the class
            content += "\n";
            writer.write(content);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void writeToFile(T entity, Iterable<Problem> problems) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(fileName));
            writeFirstPart(writer);
            String content = "";
            String block = "";
            for (Problem p : problems) {
                block = "   <entity class=\""+p.getClass().getName()+"\">"+"\n"+
                        "       <field name=\"number\"" + " " + "type=\"java.lang.Integer\""+ " " +"value=\"" + p.getNumber()+"\"/>"+"\n"+
                        "       <field name=\"text\"" + " " + "type=\"java.lang.String\"" + " " + "value=\"" + p.getText()+ "\"/>" + "\n" +
                        "       <field name=\"id\"" + " " + "type=\"java.lang.Object\"" + " " + "value=\"" + p.getId() + "\"/>" + "\n" +
                        "   </entity>" + "\n";

                content = content + block + System.lineSeparator();
            }
            writer.write(content);
            writer.write(" </problems>");


        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                //Closing the resources
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
