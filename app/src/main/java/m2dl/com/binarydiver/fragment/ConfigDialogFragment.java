package m2dl.com.binarydiver.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import m2dl.com.binarydiver.MainActivity;
import m2dl.com.binarydiver.R;
import m2dl.com.binarydiver.controls.DiveControl;
import m2dl.com.binarydiver.data.Difficulty;
import m2dl.com.binarydiver.data.Jeu;
import m2dl.com.binarydiver.exceptions.UnsupportedMaterialException;

/**
 * Created by Lucas-PCP on 28/01/2016.
 */
public class ConfigDialogFragment  extends DialogFragment implements View.OnTouchListener {

    private Jeu jeu;
    GestureDetector mGestureDetector;

    public static ConfigDialogFragment newInstance() {
        ConfigDialogFragment dialog = new ConfigDialogFragment();

        return dialog;
    }
    @Override
    public void onResume() {
        super.onResume();

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            @Override
            public boolean onKey(android.content.DialogInterface dialog, int keyCode,
                                 android.view.KeyEvent event) {

                if ((keyCode ==  android.view.KeyEvent.KEYCODE_BACK))
                {
                    //This is the filter
                    if (event.getAction()!= KeyEvent.ACTION_DOWN)
                        return true;
                    else
                    {
                        //Hide your keyboard here!!!!!!
                        return true; // pretend we've processed it
                    }
                }
                else
                    return false; // pass on to be processed as normal
            }
        });
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.config_fragment, container, false);
        final RadioButton hardcore = (RadioButton)v.findViewById(R.id.radioButtonHardcore);
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                hardcore.setVisibility(View.VISIBLE);
                hardcore.setChecked(true);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
        mGestureDetector.setIsLongpressEnabled(true);
        final RadioGroup radioGroupDiff = (RadioGroup) v.findViewById(R.id.radioGroupDiff);
        getDialog().setTitle("Configurer votre partie");
        getDialog().setCanceledOnTouchOutside(false);
        jeu = ((MainActivity) getActivity()).getJeu();
        TextView diff = (TextView)v.findViewById(R.id.textView);
        diff.setOnTouchListener(this);
        TextView txHighScore = (TextView) v.findViewById(R.id.textViewHighScore);
        TextView txLastScore = (TextView) v.findViewById(R.id.textViewLastScore);


        txHighScore.setText("Meilleur Score: " + jeu.getHighscore());
        txLastScore.setText("Dernier score: " + jeu.getLastscore());

        Button buttonValider = (Button)v.findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                switch (radioGroupDiff.getCheckedRadioButtonId()) {
                    case R.id.radioButtonFacile:
                        jeu.setDifficulty(Difficulty.FACILE);
                        break;
                    case R.id.radioButtonNormal:
                        jeu.setDifficulty(Difficulty.NORMAL);
                        break;
                    case R.id.radioButtonDifficile:
                        jeu.setDifficulty(Difficulty.DIFFICILE);
                        break;
                    case R.id.radioButtonHardcore:
                        jeu.setDifficulty(Difficulty.HARDCORE);
                }

                RadioGroup radioGroupSexe = (RadioGroup) v.findViewById(R.id.radioGroupSexe);

                jeu.setSex(radioGroupSexe.getCheckedRadioButtonId() == R.id.radioButtonHomme);

                jeu.createPerso();

                jeu.startGame();

                try {
                    DiveControl diveControl = new DiveControl(getContext(), jeu.getPerso());
                } catch (UnsupportedMaterialException e) {
                    e.printStackTrace();
                }

                getDialog().dismiss();
                hardcore.setVisibility(View.GONE);
                ((MainActivity) getActivity()).launch();

            }
        });

        return v;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
