package utils;

import java.util.HashMap;
import javax.persistence.Persistence;

public class SchemaBuilder {

    public static void main(String[] args) {
        System.out.println("Building the Table(s)");

     
        Persistence.generateSchema("pu", null);
       
    }

}
