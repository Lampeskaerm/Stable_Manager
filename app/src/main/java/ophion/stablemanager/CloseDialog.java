package ophion.stablemanager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by AK on 2/18/2016.
 */
public class CloseDialog extends DialogFragment {

    public interface NoticeDialogListener {
        public void onCloseDialogPositiveClick(DialogFragment dialog);
        public void onCloseDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener listener;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.discard_changes)
                .setPositiveButton(R.string.discard_caps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        listener.onCloseDialogPositiveClick(CloseDialog.this);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onCloseDialogNegativeClick(CloseDialog.this);
                    }
                });

        return builder.create();
    }
}
