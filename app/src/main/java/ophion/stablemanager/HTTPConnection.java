package ophion.stablemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ophion.stablemanager.objects.Horse;
import ophion.stablemanager.objects.User;

/**
 * Created by AK on 2/16/2016.
 */
public class HTTPConnection {
    private String response;
    private URL url;
    private ProgressDialog pDialog;
    private JSONObject jsonObj;
    private String connectionType;
    private LinkedHashMap<String,RequestParams> hm;

    public HTTPConnection(Context context) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }

    public void Connect (LinkedHashMap<String,RequestParams> hashMap) {
        pDialog.show();
        this.hm = hashMap;
        HashMap.Entry<String,RequestParams> entry = hm.entrySet().iterator().next();
        connectionType = entry.getKey();
        RequestParams params = entry.getValue();
        String str;
        if(connectionType == "get_horses"){
            str = params.toString();
            if(str.contains("owner_id=0")){
                params.remove("owner_id");
                params.put("owner_id",MainActivityFragment.user.getID());
            }
            Log.d("Params", str);
        }
        hm.remove(connectionType);
        String path = "http://www.ophion-programming.com/" + connectionType + ".php";
        AsyncConnect(params, path);
    }

    private void AsyncConnect (RequestParams params, String path) {
        TextHttpResponseHandler rh = new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("HTTPConnection", "FAILED");
                throwable.printStackTrace();
                if(hm.isEmpty())
                    pDialog.hide();
                else
                    Connect(hm);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                // called when response HTTP status is "200 OK"
                Log.d("HTTPConnection", "SUCCESSFULL!!");
                try {
                    jsonObj = new JSONObject(responseString);
                    ManipulateData(jsonObj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart() {
                // called before request is started
                Log.d("HTTPConnection", "started");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.d("HTTPConnection", "Retrying");
            }

            @Override
            public void onFinish() {

            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(path, params,rh);
    }

    private void ManipulateData(JSONObject jsonObj) {
        switch(connectionType) {
            case "get_user":
                try {
                    JSONObject userInfo = jsonObj.getJSONObject("user");
                    User user = new User(userInfo.getInt("user_id"));
                    user.setName(userInfo.getString("first_name"), userInfo.getString("last_name"));
                    user.setAgeFromBirthday(userInfo.getString("birthday"));
                    user.setEmail(userInfo.getString("email"));
                    user.setFacebookID(userInfo.getString("facebook_id"));
                    user.setPassword(userInfo.getString("password"));
                    user.setPhonenumber(userInfo.getString("phonenumber"));
                    MainActivityFragment.user = user;
                    //Log.d("test","User breakpoint test");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "get_horses":
                try {
                    JSONObject horsesInfo = jsonObj.getJSONObject("horses");
                    Iterator<?> keys = horsesInfo.keys();

                    while(keys.hasNext()) {
                        String key = (String)keys.next();
                        if (horsesInfo.get(key)  instanceof JSONObject ) {
                            JSONObject jHorse = (JSONObject) horsesInfo.get(key);
                            Horse h = new Horse(jHorse.getInt("horse_id"));
                            h.setName(jHorse.getString("name"));
                            h.setBoxId(jHorse.getInt("box_id"));
                            h.setStableId(jHorse.getInt("stable_id"));
                            h.setOwnerId(jHorse.getInt("owner_id"));
                            if(h.getOwnerId() == MainActivityFragment.user.getID()) {
                                h.setOwner(MainActivityFragment.user);
                                MainActivityFragment.user.addOwnedHorse(h.getId(),h);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "add_horse":
                try {
                    JSONObject jsonHorse = jsonObj.getJSONObject("horse");
                    int horseId = jsonHorse.getInt("horse_id");
                    Horse h = new Horse(horseId);
                    h.setAge(jsonHorse.getInt("age"));
                    h.setName(jsonHorse.getString("name"));
                    MainActivityFragment.user.addOwnedHorse(horseId, h);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        if(hm.isEmpty())
            pDialog.hide();
        else
            Connect(hm);
    }
}
