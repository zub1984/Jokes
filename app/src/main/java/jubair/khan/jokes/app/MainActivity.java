package jubair.khan.jokes.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.greenrobot.event.EventBus;
import jubair.khan.jokesandroidlib.events.MessageEvent;
import jubair.khan.jokes.app.utility.Constants;
import jubair.khan.jokesandroidlib.JokerActivity;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Register to receive events*/
        EventBus.getDefault().register(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if (null != view) {
            // call AsyncTask from MainActivity
            new EndpointsAsyncTask(getApplicationContext()).execute();
        }
    }


    /**
     * EventBus uses this method to pass specified event to classes which is registered
     */
    public void onEvent(MessageEvent event) {
        Intent myIntent = new Intent(this, JokerActivity.class);
        myIntent.putExtra(Constants.JOKE_MESSAGE_KEY, event.getMessage());
        startActivity(myIntent);

    }

    @Override
    protected void onDestroy() {
        /**
         * You need to unregister EventBus while your activity destroying.
         * */
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
