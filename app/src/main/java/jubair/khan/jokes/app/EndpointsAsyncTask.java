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

/**
 * Created by laptop on 1/22/2016.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private Context mContext;

    EndpointsAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            String projectId = mContext.getString(R.string.gce_project_id);

            //https://jokesteller-1199.appspot.com/_ah/api/
            //https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints

            Uri.Builder builder = new Uri.Builder();
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
            return Constants.GCE_IO_EXCEPTION;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        EventBus.getDefault().post(new MessageEvent(result));
    }
}
