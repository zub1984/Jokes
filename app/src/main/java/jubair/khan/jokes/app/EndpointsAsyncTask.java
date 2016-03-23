package jubair.khan.jokes.app;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jubair.jokes.backend.myApi.MyApi;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import jubair.khan.jokes.app.utility.Constants;
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

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private Context mContext;


    public EndpointsAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            //Log.v("EndpointsAsyncTask", "doInBackground");

            //https://jokesteller-1199.appspot.com/_ah/api/
            //https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints

           Uri.Builder builder = new Uri.Builder();
           String projectId = mContext.getString(R.string.gce_project_id);
            builder.scheme("https")
                    .authority(projectId + ".appspot.com")
                    .appendPath("_ah")
                    .appendPath("api")
                    .appendPath("");
            String myUrl = builder.build().toString();

            MyApi.Builder appBuilder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(myUrl);

            myApiService = appBuilder.build();
        }

        try {
            return myApiService.getJokes().execute().getData();
        } catch (IOException e) {
            //Log.v("EndpointsAsyncTask", "Failed : " + e.getMessage());
            return Constants.GCE_IO_EXCEPTION;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        EventBus.getDefault().post(new MessageEvent(result));
    }
}
