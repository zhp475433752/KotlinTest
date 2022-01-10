package com.zwwl.kotlintest.proxy;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.zwwl.kotlintest.R;

/**
 * Created by zhanghuipeng on 2022/1/8.
 */
class ViewClickListenerProxy implements View.OnClickListener{
    private static final String TAG = "ViewClickListenerProxy";
    private View.OnClickListener origin;

    public ViewClickListenerProxy(View.OnClickListener origin) {
        this.origin = origin;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.e(TAG, "---view-id----"+id);
        if (id == R.id.btnHook) {
            Toast toast = Toast.makeText(v.getContext(), "我是动态代理的click", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            if (origin != null) {
                origin.onClick(v);
            }
        }

    }
}
