package ro.ubb.laboratory.util;

import ro.ubb.laboratory.domain.BaseEntity;
import ro.ubb.laboratory.domain.Student;

import java.beans.Expression;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class XmlReaderProblem<ID, T extends BaseEntity<ID>> {
    private String fileName;
    public XmlReaderProblem(String fileName) {
        this.fileName = fileName;
    }

    private String cleanString(String s){
        s = s.replaceAll("\"","");
        s = s.replaceAll(">","");
        s = s.replaceAll("<","");
        s = s.replaceAll("/","");
        return s;
    }

    public List<T> loadEntities() {
        List<T> entities = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new File(fileName));
            HashMap<String,String> hm = new HashMap<String,String>();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if (line.contains("<entity")){
                    //
                    line = line.replaceAll("[\"<>]","");
                    String objectClass = line.split("=")[1];


                    //read the fields
                    String field1 = sc.nextLine();
                    //remove garbage
                    field1 = cleanString(field1);
                    //split
                    String[] args = field1.split("\\s+");
                    //create a map key value and then acces the fields of a student
                    String[] tokens;
                    hm.put(args[2].split("=")[0],args[2].split("=")[1]);
                    hm.put(args[3].split("=")[0],args[3].split("=")[1]);
                    hm.put(args[4].split("=")[0],args[4].split("=")[1]);
                    Integer number = Integer.parseInt(hm.get("value"));
                    //here we could validate the type
                    hm.clear();
                    String field2 = sc.nextLine();
                    field2 = cleanString(field2);
                    args = field2.split("\\s+");
                    hm.put(args[2].split("=")[0],args[2].split("=")[1]);
                    hm.put(args[3].split("=")[0],args[3].split("=")[1]);
                    hm.put(args[4].split("=")[0],args[4].split("=")[1]);
                    String text = hm.get("value");
                    hm.clear();
                    String field3 = sc.nextLine();
                    field3 = cleanString(field3);

                    args = field3.split("\\s+");

                    hm.put(args[2].split("=")[0],args[2].split("=")[1]);
                    hm.put(args[3].split("=")[0],args[3].split("=")[1]);
                    hm.put(args[4].split("=")[0],args[4].split("=")[1]);
                    Long id = Long.parseLong(hm.get("value"));
                    hm.clear();

                    //create student and append it to the list
                    T s = (T)Class.forName(objectClass).getDeclaredConstructor(Integer.class,String.class).newInstance(number,text);
                    s.setId((ID)id);
                    entities.add(s);
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return entities;
    }


}