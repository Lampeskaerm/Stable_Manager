package ophion.stablemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.net.URL;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ophion.stablemanager.objects.User;

/**
 * Created by AK on 2/16/2016.
 */
public class HTTPConnection {
    private String response;
    private URL url;
    private ProgressDialog pDialog;
    private JSONObject jsonObj;

    public HTTPConnection(Context context) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void Connect (RequestParams params, String connectionType) {
        String path = "http://www.ophion-programming.com/" + connectionType + ".php";
        AsyncConnect(params, path);
    }

    private void AsyncConnect (RequestParams params, String path) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(path, params,
                new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("HTTPConnection","FAILED");
                pDialog.hide();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                // called when response HTTP status is "200 OK"
                Log.d("HTTPConnection", "SUCCESSFULL!!");
                try {
                    jsonObj = new JSONObject(responseString);
                    JSONObject userInfo = jsonObj.getJSONObject("user");
                    User user = new User(userInfo.getInt("user_id"));
                    user.SetName(userInfo.getString("first_name"),userInfo.getString("last_name"));
                    user.SetAgeFromBirthday(userInfo.getString("birthday"));
                    user.SetEmail(userInfo.getString("email"));
                    user.SetFacebookID(userInfo.getString("facebook_id"));
                    user.SetPassword(userInfo.getString("password"));
                    user.SetPhonenumber(userInfo.getString("phonenumber"));
                    MainActivityFragment.user = user;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pDialog.hide();
            }

            @Override
            public void onStart() {
                // called before request is started
                Log.d("HTTPConnection","started");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.d("HTTPConnection","Retrying");
            }
        });

    }
}
