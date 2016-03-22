package jubair.khan.jokes.app;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
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
