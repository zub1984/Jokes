package jubair.khan.jokesandroidlib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class JokerFragment extends Fragment {

    public JokerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.v(LOG_TAG, "onCreateView called");
        View rootView = inflater.inflate(R.layout.joker_fragment, container, false);
        // read the Jokes Message passed from JokesProvider-->App-->jokesandroidlib
        if (getActivity().getIntent().getExtras().containsKey("JOKE_MESSAGE_KEY")) {
            String jokesMessage = getActivity().getIntent().getStringExtra("JOKE_MESSAGE_KEY");

            TextView textView = (TextView) rootView.findViewById(R.id.textView);
            textView.setText(jokesMessage);
        }

        return rootView;
    }



}
