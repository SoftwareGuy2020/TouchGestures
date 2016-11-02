package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapCountTextView;
    private TextView doubleTapCountTextView;
    private TextView longPressCountTextView;
    private TextView scrollCountTextView;
    private TextView flingCountTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapCountTextView = (TextView) findViewById(R.id.singleTapCountTextView);
        doubleTapCountTextView = (TextView) findViewById(R.id.doubleTapCountTextView);
        longPressCountTextView = (TextView) findViewById(R.id.longPressCountTextView);
        scrollCountTextView = (TextView) findViewById(R.id.scrollCountTextView);
        flingCountTextView = (TextView) findViewById(R.id.flingCountTextView);

        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        gestureDetector.setOnDoubleTapListener(this);
    }

    // Any touch event is now dispatched from Activity to the scrollview
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        ++singleTaps;
        singleTapCountTextView.setText(Integer.toString(singleTaps));
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap touch event");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        ++doubleTaps;
        doubleTapCountTextView.setText(Integer.toString(doubleTaps));
        gesturesLogTextView.append("\nonDoubleTapEvent touch event");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ++scrolls;
        scrollCountTextView.setText(Integer.toString(scrolls));
        gesturesLogTextView.append("\nonScroll: distanceX is " + Float.toString(v) + ", distanceY is "
        + Float.toString(v));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        ++longPresses;
        longPressCountTextView.setText(Integer.toString(longPresses));
        gesturesLogTextView.append("\nonLongPress touch event");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ++flings;
        flingCountTextView.setText(Integer.toString(flings));
        gesturesLogTextView.append("\nonScroll: velocityX is " + Float.toString(v) + ", velocityY is "
                + Float.toString(v));
        return true;
    }

    public void clearAll(View v) {
        gesturesLogTextView.setText("");
        singleTaps = doubleTaps = longPresses = scrolls = flings = 0;

        singleTapCountTextView.setText(Integer.toString(singleTaps));
        doubleTapCountTextView.setText(Integer.toString(doubleTaps));
        longPressCountTextView.setText(Integer.toString(longPresses));
        scrollCountTextView.setText(Integer.toString(scrolls));
        flingCountTextView.setText(Integer.toString(flings));

    }
}
