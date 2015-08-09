package com.example.meusmedicos;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements OnTimeSetListener {
	
    private static final String TAG = DatePickerFragment.class.getSimpleName();

    /***
     * We have to call the method of SimpleInput class before which this variable
     * should be initialized.
     * ***/
    private EditText mInputEditText;

    private Calendar mCurrentTime;

    private boolean mIsSetMinDate = false;
    private long mMinDate = 0;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current time as the default values for the picker
	final Calendar c = Calendar.getInstance();
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE);
	
	// Create a new instance of TimePickerDialog and return it
	return new TimePickerDialog(getActivity(), this, hour, minute,
			DateFormat.is24HourFormat(getActivity()));
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		   Log.v(TAG, "#onTime set Hour: " + hourOfDay + " minute: " + minute);

	        // The month is zero indexed.
	        mInputEditText.setText(hourOfDay + ":" + minute);
	        mCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	        mCurrentTime.set(Calendar.MINUTE, minute);
	}
	
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH || Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            Log.v(TAG, "#onDismiss text is to be cleared");
            // the dialog has been dismissed
            mInputEditText.setText(null);
        }
    }

    public void show(FragmentManager fragmentManager, String tag, EditText editText, Calendar time) {
        this.show(fragmentManager, tag);
        mInputEditText = editText;
        mCurrentTime = time;
    }

}
