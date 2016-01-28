package m2dl.com.binarydiver.fragment;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import m2dl.com.binarydiver.R;

/**
 * Created by Lucas-PCP on 28/01/2016.
 */
public class ConfigDialogFragment  extends DialogFragment{

    public static ConfigDialogFragment newInstance() {
        ConfigDialogFragment dialog = new ConfigDialogFragment();
        Bundle args = new Bundle();
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.config_fragment, container, false);
        getDialog().setTitle("Configurer votre partie");
        return v;
    }
}
