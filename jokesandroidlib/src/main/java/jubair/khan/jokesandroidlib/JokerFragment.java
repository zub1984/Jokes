package jubair.khan.jokesandroidlib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by laptop on 1/20/2016.
 */
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
