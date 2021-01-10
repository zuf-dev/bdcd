import java.io.*;
import com.linuxense.javadbf.*;


public class bdc {

    public static void main(String args[]) {

        DBFReader reader = null;
        try {

            // create a DBFReader object
            reader = new DBFReader(new FileInputStream(args[0]));

            // get the field count if you want for some reasons like the following

            int numberOfFields = reader.getFieldCount();

            // use this count to fetch all field information
            // if required

            for (int i = 0; i < numberOfFields; i++) {

                DBFField field = reader.getField(i);

                // do something with it if you want
                // refer the JavaDoc API reference for more details
                //
                 System.out.println(field.getName());
            }

            // Now, lets us start reading the rows

            Object[] rowObjects;
            String id = null;
            String app = null;
            String email = null;
            int i = 0;
            int dead = 0;
            PrintStream printer = new PrintStream("loginy.csv");
            System.setOut(printer);
            System.out.println("user_login,user_email,source_user_id,password");

            while ((rowObjects = reader.nextRecord()) != null) {

                id = rowObjects[0].toString();
                app = rowObjects[10].toString();
                email = i + "@me.com";

                if (rowObjects[3] == (null)) {
                    rowObjects[3] = "0";

                }
                dead = Integer.parseInt(rowObjects[3].toString());

                if (dead!=99) {

                    System.out.println(id + "," + email + "," + i + "," + app);
                }
            i++;

        }
            // By now, we have iterated through all of the rows

        } catch (DBFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            DBFUtils.close(reader);
        }
    }
}