package com.example.dokdofamily01;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListPopupWindow;
import android.widget.Spinner;

import java.lang.reflect.Field;

public class CustomSpinner extends android.support.v7.widget.AppCompatSpinner {

    private Context context;
    private TaleActivity taleActivity;

    public CustomSpinner(Context context) {
        super(context);
        this.context = context;

    }

    public CustomSpinner(Context context, int mode) {
        super(context, mode);
        this.context = context;
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        this.context = context;
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
        this.context = context;
    }

    public void setTaleActivity(TaleActivity tale) {

        taleActivity = tale;
    }


    /**
     * An interface which a client of this Spinner could use to receive
     * open/closed events for this Spinner.
     */
    public interface OnSpinnerEventsListener {

        /**
         * Callback triggered when the spinner was opened.
         */
        void onSpinnerOpened(Spinner spinner);

        /**
         * Callback triggered when the spinner was closed.
         */
        void onSpinnerClosed(Spinner spinner);

    }

    private OnSpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;

    // implement the Spinner constructors that you need

    @Override
    public boolean performClick() {
        // register that the Spinner was opened so we have a status
        // indicator for when the container holding this Spinner may lose focus
        Field popup = null;
        try {
            popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            ListPopupWindow window = (ListPopupWindow)popup.get(taleActivity.goPage);
            Log.d("slheight", taleActivity.height+"/");
            Log.d("showmenuheight", taleActivity.showMenuHeight+"/");
            window.setHeight((int)(taleActivity.height - (taleActivity.showMenuHeight*2.3f)));
//            window.setHeight(300);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened(this);
        }
        return super.performClick();
    }

    /**
     * Register the listener which will listen for events.
     */
    public void setSpinnerEventsListener(
            OnSpinnerEventsListener onSpinnerEventsListener) {
        mListener = onSpinnerEventsListener;
    }

    /**
     * Propagate the closed Spinner event to the listener from outside if needed.
     */
    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {

            mListener.onSpinnerClosed(this);

        }


        taleActivity.autoCloseMenu(3000);

    }

    /**
     * A boolean flag indicating that the Spinner triggered an open event.
     *
     * @return true for opened Spinner
     */
    public boolean hasBeenOpened() {
        return mOpenInitiated;
    }

    public void onWindowFocusChanged (boolean hasFocus) {
        if (hasBeenOpened() && hasFocus) {
            performClosedEvent();
        }
    }

}