import fxexample.Person;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PersonApp {

    public static void main(String[] args) {
        List<Person> people = Person.createPersonList();

        Map<Integer, String> idNameMap = new HashMap<>();
        for (Person p : people) {
            idNameMap.put(p.getId(), p.getFName());
        }

        Iterator<Map.Entry<Integer, String>> it = idNameMap.entrySet().iterator();
        while (it.hasNext()) {
           Map.Entry<Integer, String> entry = it.next();
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

    }
}
