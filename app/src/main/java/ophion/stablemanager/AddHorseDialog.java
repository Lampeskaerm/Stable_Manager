package ophion.stablemanager;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by AK on 2/15/2016.
 */
public class AddHorseDialog extends DialogFragment {

    public interface NoticeDialogListener {
        public void onAddHorseDialogSaveClick(DialogFragment dialog);
        public void onAddHorseDialogCancelClick(DialogFragment dialog);
    }

    NoticeDialogListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View rootView = inflater.inflate(R.layout.fragment_add_horse, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        ImageButton closeBtn = (ImageButton) rootView.findViewById(R.id.close_button);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddHorseDialogCancelClick(AddHorseDialog.this);
            }
        });

        Button saveBtn = (Button) rootView.findViewById(R.id.save_button);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddHorseDialogSaveClick(AddHorseDialog.this);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);


        // Use the Builder class for convenient dialog construction
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Fire missiles?")
                .setPositiveButton("Fire", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();*/
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
