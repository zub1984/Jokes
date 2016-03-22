package jubair.khan.jokes.app;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import jubair.khan.jokesandroidlib.events.MessageEvent;

/**
 * Created by laptop on 3/22/2016.
 */
public class AsyncTaskTest extends InstrumentationTestCase {

    private CountDownLatch mSignal;
    private Context mContext;

    @Override
    protected void setUp() throws Exception {
        mContext = getInstrumentation().getContext();
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mSignal.countDown();
        /**Register to receive events*/
        EventBus.getDefault().register(this);
    }

    @MediumTest
    public void testAsyncTask() throws Throwable {
        final EndpointsAsyncTask jokeTask = new EndpointsAsyncTask(mContext);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                jokeTask.execute();
            }
        });

       mSignal.await(5000, TimeUnit.MILLISECONDS);
    }


    public void onEvent(MessageEvent event) {
        assertNotNull(event.getMessage());
        mSignal.countDown();
    }

}
