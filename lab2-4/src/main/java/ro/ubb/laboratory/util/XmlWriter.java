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

    public void writeToFile(T entity, Iterable<Student> students) {
        FileWriter writer = null;
        try {
            String content = "";
            String block = "";
            //reader = new BufferedReader(new FileReader(fileToBeModified));
            for (Student st : students) {
                block = "<entity class=\""+st.getClass()+"\">"+"\n"+
                        "<field name=\"serialNumber\"" +  "type=\"java.lang.String\""+ "value=\"" + st.getSerialNumber()+"\"/>"+"\n"+
                        "<field name=\"name\"" + "type=\"java.lang.String\"" +  "value=\"" + st.getName()+ "\"/>" + "\n" +
                        "<field name=\"id\"" +  "type=\"java.lang.Object\"" +  "value=\"" + st.getId() + "\"/>";
                content = content + block + System.lineSeparator();
            }
            writer = new FileWriter(new File(fileName));
            writer.write(content);


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
