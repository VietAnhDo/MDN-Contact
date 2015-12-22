import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by Anh on 12/22/2015.
 */
public class Main {
    public String[] splitMain(String line) {
        List<String> list = new Vector<String>();
        String tmp = "";
        boolean open = false;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i)=='"')open = !open;
                else
                    if( !open && line.charAt(i)==',') {
                        list.add(tmp);
                        tmp = "";
                    }
                    else
                        if( line.charAt(i) == ',') tmp += '-';
                            else
                                tmp = tmp + line.charAt(i);
        }
        list.add(tmp);
        return list.toArray(new String[list.size()]);
    }
    public void stream() {
        BufferedReader br = null;
        BufferedWriter brW = null;
        String line;
        int cnt = 0;
        try {
            br = new BufferedReader(new FileReader("target/google.csv"));
            brW = new BufferedWriter(new FileWriter("target/tmp.csv"));
            while((line = br.readLine()) != null ) {
                cnt ++;
                String info[] = splitMain(line);
                System.out.println(info.length);
                brW.write(info[0]);
                brW.write(",");
                brW.write(info[28]);
                brW.write(",");
                System.out.print(info[0] + " " + info[28] + " ");
                if(info.length > 30) {
                    System.out.print(info[30]);
                    brW.write(info[30]);
                }
                brW.newLine();
                System.out.println();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if( br != null) {
                try{
                    br.close();
                    brW.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println(cnt);
    }
    public static void main(String args[]) {
        SmartContact sc = new SmartContact();
        sc.loadContactListFrom("target/contacts.csv");
        sc.showContactList(sc.getContact("   bá@+-*/^"));
    }
}
