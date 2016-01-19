package jubair.khan.jokesandroidlib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by laptop on 1/20/2016.
 */
public class JokerFragment extends Fragment {

    public static final String LOG_TAG = JokerFragment.class.getSimpleName();

    public JokerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(LOG_TAG, "onCreateView called");
        View rootView = inflater.inflate(R.layout.joker_fragment, container, false);

        // read the Jokes Message passed from JokesProvider-->App-->jokesandroidlib
        if (getActivity().getIntent().getExtras().containsKey("JOKE_MESSAGE_KEY")) {
            String jokesMessage = getActivity().getIntent().getStringExtra("JOKE_MESSAGE_KEY");
            Toast.makeText(getActivity(), jokesMessage, Toast.LENGTH_SHORT).show();

            TextView textView = (TextView) rootView.findViewById(R.id.textView);
            textView.setText(jokesMessage);
        }

        return rootView;
    }


}
