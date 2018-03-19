package ro.ubb.laboratory.util;

import ro.ubb.laboratory.domain.BaseEntity;
import ro.ubb.laboratory.domain.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XmlWriter<ID, T extends BaseEntity<ID>> {

    private String fileName;

    public XmlWriter(String fileName) {
        this.fileName = fileName;
    }

    private void writeFirstPart(FileWriter writer){
        try {
            String content = "";
            content += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + "\n";
            content += "<students>" + "\n"; //here could be also name of the class
            content += "\n";
            writer.write(content);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void writeToFile(T entity, Iterable<Student> students) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(fileName));
            writeFirstPart(writer);
            String content = "";
            String block = "";
            for (Student st : students) {
                block = "   <entity class=\""+st.getClass().getName()+"\">"+"\n"+
                        "       <field name=\"serialNumber\"" + " " + "type=\"java.lang.String\""+ " " +"value=\"" + st.getSerialNumber()+"\"/>"+"\n"+
                        "       <field name=\"name\"" + " " + "type=\"java.lang.String\"" + " " + "value=\"" + st.getName()+ "\"/>" + "\n" +
                        "       <field name=\"id\"" + " " + "type=\"java.lang.Object\"" + " " + "value=\"" + st.getId() + "\"/>" + "\n" +
                        "   </entity>" + "\n";

                content = content + block + System.lineSeparator();
            }
            writer.write(content);
            writer.write(" </students>");


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
