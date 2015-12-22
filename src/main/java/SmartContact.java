import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by Anh on 12/22/2015.
 */
public class SmartContact {
    List<ContactItem> contactList;
    public void loadContactListFrom(String csvFile) {
        contactList = new Vector<ContactItem>();
        BufferedReader br = null;
        String line = "";
        int cnt = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while((line = br.readLine()) != null ) {
                cnt ++;
                String info[] = line.split(",");
                ContactItem item = new ContactItem();
                item.setName(info[0]);
                if(info.length > 1) item.setEmail(info[1]);
                if(info.length > 2) item.setNumber(info[2]);
                contactList.add(item);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();

        } catch(IOException e) {
            e.printStackTrace();

        } finally {
            if(br != null) {
                try{
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("number of lines of contacts.csv: " + String.valueOf(cnt) +" line(s)");
    }
    public List<ContactItem> getContact(String name) {
        List<ContactItem> retItems = new Vector<ContactItem>();
        String words[] = name.trim().split("[ ,\\p{Punct}]");
        int wn = 0;
        for(int i = 0; i < words.length; i++) if(!words[i].trim().isEmpty()) words[wn++] = words[i];
        for(ContactItem item : contactList ) {
            for(int t = 0, i = 0; i < item.getName().length(); i++) {
                if( t >= words.length ) {
                    retItems.add(item);
                    break;
                }
                if( words[t].regionMatches(0, item.getName(), i, words[t].length()) ) t++;
            }
        }
        return retItems;
    }
    public void showContactList() {
        for(ContactItem item : contactList ) {
            System.out.println(item.getName() +" "+item.getEmail()+" "+item.getNumber());
        }
    }
    public void showContactList(List<ContactItem> list) {
        for(ContactItem item : list ) {
            System.out.println(item.getName() +" "+item.getEmail()+" "+item.getNumber());
        }
    }
}
