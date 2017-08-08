package zhou.com.loveyou.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import zhou.com.loveyou.R;
import zhou.com.loveyou.activity.RelationActivity;
import zhou.com.loveyou.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener,View.OnTouchListener,GestureDetector.OnGestureListener{


    private static final int FLING_MIN_DISTANCE = 150;
    private static final int FLING_MIN_VELOCITY = 0;
    private ImageView right_icon;
    private ImageView iv_select;
    private Button bt_pass;
    private Button bt_like;
    private GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //获取控件，全局化后用于其他地方
        right_icon = view.findViewById(R.id.right_picture);
        iv_select = view.findViewById(R.id.iv_select);
        bt_pass = view.findViewById(R.id.bt_pass);
        bt_like = view.findViewById(R.id.bt_like);

        //点击事件
        right_icon.setOnClickListener(this);
        bt_like.setOnClickListener(this);
        bt_pass.setOnClickListener(this);

        //滑动事件
        gestureDetector = new GestureDetector(this);
        iv_select.setOnTouchListener(this);
        iv_select.setLongClickable(true);

        return view;
    }

    /**
     *  控件的事件处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //右图标
            case R.id.right_picture:
                Intent intent = new Intent(getContext(), RelationActivity.class);
                startActivity(intent); 
                break;
            //喜欢
            case R.id.bt_like:
                showLike();
                break;
            //不喜欢
            case R.id.bt_pass:
                showDislike();
                break;
        }
    }

    /**
     * 点击
     * 滑动
     * 不喜欢
     * 逻辑是图片下一张，但是不将她的id等信息保存
     */
    private void showDislike() {
        ToastUtil.show(getContext(),"不喜欢");
    }

    /**
     * 点击
     * 滑动
     * 喜欢
     * 逻辑是图片下一张，将她的id等信息保存
     */
    private void showLike() {
        ToastUtil.show(getContext(),"喜欢");
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //判断左右移动的距离，触动事件
        if(motionEvent.getX() - motionEvent1.getX() > FLING_MIN_DISTANCE && Math.abs(v)>FLING_MIN_VELOCITY){
            showDislike();
        }else if(motionEvent1.getX()-motionEvent.getX()>FLING_MIN_DISTANCE && Math.abs(v)>FLING_MIN_VELOCITY){
            showLike();
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("HomeFragment","是我啊");
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
