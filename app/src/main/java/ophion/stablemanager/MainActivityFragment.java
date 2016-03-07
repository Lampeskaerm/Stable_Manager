package ophion.stablemanager;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import ophion.stablemanager.objects.Horse;
import ophion.stablemanager.objects.User;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends AppCompatActivity implements AddHorseDialog.NoticeDialogListener, CloseDialog.NoticeDialogListener{
    //navigation bar variables
    private String[] menuTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence title;
    private ActionBarDrawerToggle drawerToggle;

    //Facebook login variables
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean loggedInToFacebook;

    //HTTPRequest variables
    private RequestParams params;
    private boolean initDataDownloaded = false;

    //User variables
    private Profile facebookProfile;
    public static User user;

    //Test variables
    private Button testButton;

    //Temporary dialog
    private DialogFragment tempDialog;

    public static void main (String[] args) {

    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        /* Initialize facebook SDK *
         * Must be initialized before setContentView */
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!initDataDownloaded) {
            loggedInToFacebook = isLoggedInFacebook();

            if (loggedInToFacebook) {
                facebookProfile = Profile.getCurrentProfile();
                GetStartInformation();
            }
            initDataDownloaded = true;
        }

        /*
        Setup loginbutton with callback
         */
        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("LoginActivity", "It was a success!");
                RunGraphRequest(loginResult);
                facebookProfile = Profile.getCurrentProfile();
                GetStartInformation();
            }

            @Override
            public void onCancel() {
                Log.d("LoginActivity", "The process was cancelled!");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("LoginActivity", exception.getCause().toString());
            }
        });

        /*
        Initialize variables for navigation drawer
         */
        menuTitles = getResources().getStringArray(R.array.menuTitles);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, menuTitles));
        // Set the list's click listener
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //Setup the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        //Create the drawer toggle
        title = getTitle();
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
               toolbar , R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

    } //Ends onCreate

    private void GetStartInformation() {
        params = new RequestParams();
        params.put("facebook_id",facebookProfile.getId());
        LinkedHashMap<String,RequestParams> hashMap = new LinkedHashMap<>();
        hashMap.put("get_user", params);
        params.put("owner_id", 0);
        hashMap.put("get_horses",params);
        makeHttpRequest(hashMap);
        Log.d("HTTPCLIENT","HTTP request is done");
    }

    private boolean isLoggedInFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return (accessToken != null && !accessToken.isExpired());
    }

    private void RunGraphRequest(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.d("LoginActivity Graph", response.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void makeHttpRequest(LinkedHashMap<String,RequestParams> map) {
        HTTPConnection con = new HTTPConnection(MainActivityFragment.this);
        con.Connect(map);
        params = new RequestParams();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position

        Fragment fragment = null;

        switch(position) {
            case 1: //My Horses
                fragment = new YourHorsesFragment();
                break;
            case 4: //User
                fragment = new UserFragment();
                break;
            default:
                break;
        }

        if(fragment == null) {
            fragment = new Fragment();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(menuTitles[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAddHorseDialogSaveClick(DialogFragment dialog) {
        View rootView = dialog.getView();
        String name = ((EditText) rootView.findViewById(R.id.name)).getText().toString();
        name = setUpperCase(name);
        String age = ((EditText) rootView.findViewById(R.id.age)).getText().toString();
        params.put("name",name);
        params.put("age",age);
        params.put("owner_id",user.getID());
        LinkedHashMap<String,RequestParams> map = new LinkedHashMap<>();
        map.put("add_horse",params);
        makeHttpRequest(map);
        getSupportActionBar().show();
        dialog.dismiss();
    }

    @Override
    public void onDialogCancelClick(DialogFragment dialog) {
        DialogFragment closeDialog = new CloseDialog();
        tempDialog = dialog;
        closeDialog.show(getSupportFragmentManager(),"close");
    }

    @Override
    public void onCloseDialogPositiveClick(DialogFragment dialog) {
        tempDialog.dismiss();
        getSupportActionBar().show();
        dialog.dismiss();
    }

    @Override
    public void onCloseDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
        tempDialog = null;
    }

    public String setUpperCase(String str) {
        String strings[];
        String caps = "";
        strings = str.split(" ");
        for(int i=0;i<strings.length;i++){
            String str1 = strings[i];
            String str2 = str1.substring(0,1).toUpperCase() + str1.substring(1);
            if(i != strings.length-1)
                caps += str2 + " ";
            else
                caps += str2;
        }

        return caps;
    }
}
