package jubair.khan.jokes.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jubair.jokes.Joker;

import jubair.khan.jokes.app.utility.Constants;
import jubair.khan.jokesandroidlib.JokerActivity;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Joker myJoker = new Joker();
            Toast.makeText(this, "Pass joke to android library.", Toast.LENGTH_SHORT).show();
            Log.v(LOG_TAG, "passing the jokes message to android library.");
            launchLibraryActivity(myJoker.getJoke());
        }
    }

    /*
    There we go! Now we can launch the activity from our android library, and it's
    easy to reuse that activity between different apps!
     */

    public void launchLibraryActivity(String jokesMessage) {
        Intent myIntent = new Intent(this, JokerActivity.class);
        myIntent.putExtra(Constants.JOKE_MESSAGE_KEY, jokesMessage);
        startActivity(myIntent);
    }
}
