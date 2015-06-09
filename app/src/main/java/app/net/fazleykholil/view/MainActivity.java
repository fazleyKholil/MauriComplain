package app.net.fazleykholil.view;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // Creating JSON Parser object
    private JSONParser jParser = new JSONParser();
    private ArrayList<HashMap<String, String>> productsList;
    private HashMap<String,Complain> complains = new HashMap<String,Complain>();
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_COMPLAIN = "complain";
    private static final String TAG_ID = "complainId";
    private static final String TAG_DEPT = "department";
    private static final String TAG_DESC = "complainDetails";
    private static final String TAG_OTHER_DESC = "complainOtherDetails";
    private static final String TAG_DATE = "complainDate";
    private static final String TAG_CURRENT_TIMESATMP = "dateStamp";
    private static final String TAG_PLACE = "place";
    private static final String TAG_OTHER_PLACE = "additionalPlace";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_STATUS = "status";

    // products JSONArray
    JSONArray complainJSONArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadAllProducts().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Background Async Task to Load all complains by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<Context, HashMap<String,Complain>, HashMap<String,Complain>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected HashMap<String,Complain> doInBackground(Context... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(WS_CONFIG.allComplains_url, "GET", params);
            Log.d("All complains trace: ", json.toString());
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    complainJSONArray = json.getJSONArray(TAG_COMPLAIN);
                    for (int i = 0; i <complainJSONArray.length(); i++) {
                        JSONObject c = complainJSONArray.getJSONObject(i);
                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_AUTHOR);
                        String description = c.getString(TAG_DESC);
                        String Otherdescription = c.getString(TAG_OTHER_DESC);
                        String place = c.getString(TAG_PLACE);
                        String otherPlace = c.getString(TAG_OTHER_PLACE);
                        String date = c.getString(TAG_DATE);
                        String dateStamp = c.getString(TAG_CURRENT_TIMESATMP);
                        String status = c.getString(TAG_STATUS);
                        String department = c.getString(TAG_DEPT);


                        Complain complain = new Complain();
                        complain
                                .setId(Integer.parseInt(id))
                                .setAuthor(name)
                                .setDetails(description)
                                .setOtherDetails(Otherdescription)
                                .setPlace(place)
                                .setAdditionalPlace(otherPlace)
                                .setDate(date)
                                .setDateStamp(dateStamp)
                                .setStatus(status)
                                .setDepartment(department);
                        complains.put(complain.getAuthor(), complain);
                    }
                }
                else {
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return complains;
        }

        @Override
        protected void onPostExecute(HashMap<String, Complain> complainHm) {
            super.onPostExecute(complainHm);
            Iterator<String> itr = complains.keySet().iterator();
            while(itr.hasNext())
                complains.get(itr.next()).getDate();
        }

    }
}
