package mehfoud.helloworldandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by smehfoud on 7/3/13.
 */
public class ActivityB extends Activity {


    public static final String PREFS = "examplePrefs";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Log.d("VIVZ", "Button was clicked");
        //SharedPreferences preferences = this.getSharedPreferences(PREFS,0);
        //String userMessage = preferences.getString("userMessage", "no notes");


            final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
            SharedPreferences settings = getSharedPreferences(PREFS, 0);
            Map<String, Integer> items = (Map<String, Integer>) settings.getAll();

            sortByComparator(items);
            //Log.d("VIVZ", "got all references");
            String[] from = { "key", "value" };
            int[] to = { android.R.id.text1, android.R.id.text2 };
            for(String s : items.keySet()){
                //Log.d("VIVZ", "In for loop");
                HashMap<String,String> temp = new HashMap<String,String>();
                //Log.d("VIVZ", "made hashmap");
                temp.put("key", s);
                //Log.d("VIVZ", "key");
                temp.put("value", items.get(s).toString());
                //Log.d("VIVZ", "value");
                list.add(temp);
            }

        //Log.d("VIVZ", "out of for loop");
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, from, to);
        //Log.d("VIVZ", "made adapter");
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //listView.setAdapter(list);


    //    }
    }



    public static Map<String, String> sortByValue(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                Log.d("VIVZ", "...........................Compares");
                Log.d("VIVZ", "M2 value " + m2.getValue());
                Log.d("VIVZ", "M1 value " + m1.getValue());

                return (m2.getValue()).compareTo(m1.getValue());
            }
        });

        Map<String, String> result = new LinkedHashMap<String, String>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue().toString());
        }
        return result;
    }

    private static Map sortByComparator(Map unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                    .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}