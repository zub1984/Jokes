package jubair.khan.jokes.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import de.greenrobot.event.EventBus;
import jubair.khan.jokes.app.utility.Constants;
import jubair.khan.jokesandroidlib.JokerActivity;
import jubair.khan.jokesandroidlib.events.MessageEvent;

public class JokesAppActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Register to receive events*/
        EventBus.getDefault().register(this);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginJoke();
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A713156BBFF692B66DD149B92FDAE139")
                .build();

        mInterstitialAd.loadAd(adRequest);
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

    public void tellJoke(View view){
        progressBar.setVisibility(View.VISIBLE);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            beginJoke();
        }
    }

    @SuppressWarnings("unchecked")
    private void beginJoke() {
        // Fetch jokes from GCE
        new EndpointsAsyncTask(getApplicationContext()).execute();
    }

    /**
     * EventBus uses this method to pass specified event to classes which is registered
     */
    public void onEvent(MessageEvent event) {
        // Hide Progress bar
        progressBar.setVisibility(View.GONE);
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