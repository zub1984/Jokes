package jubair.khan.jokes.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import de.greenrobot.event.EventBus;
import jubair.khan.jokes.app.utility.Constants;
import jubair.khan.jokesandroidlib.JokerActivity;
import jubair.khan.jokesandroidlib.events.MessageEvent;
/*Copyright (C) 2015  Mohammad Jubair Khan (zub1984.kn@gmail.com) - An Udacity Android Nano Degree, Project 4: Build It Bigger.
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.*/
public class JokesAppActivity extends AppCompatActivity {
    private ProgressBar progressBar;

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

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
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
        beginJoke();
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