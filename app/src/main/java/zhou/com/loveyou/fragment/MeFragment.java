package zhou.com.loveyou.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import zhou.com.loveyou.R;
import zhou.com.loveyou.activity.AboutActivity;
import zhou.com.loveyou.activity.DetailsActivity;
import zhou.com.loveyou.activity.GuideActivity;
import zhou.com.loveyou.activity.SafetyActivity;
import zhou.com.loveyou.activity.SplashActivity;
import zhou.com.loveyou.utils.ContantsValue;
import zhou.com.loveyou.utils.SpUtil;
import zhou.com.loveyou.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {


    private static final int RESULT_OK = 1;
    private static final int RESULT_DETAILS = 2 ;
    private PopupWindow mPopupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        //获取控件
        ImageView iv_headphoto = view.findViewById(R.id.iv_headphoto);
        TextView tv_me_name = view.findViewById(R.id.tv_me_name);
        ImageView iv_gender = view.findViewById(R.id.iv_gender);
        TextView tv_edit = view.findViewById(R.id.tv_edit);
        TextView tv_safety = view.findViewById(R.id.tv_safety);
        TextView tv_privacy = view.findViewById(R.id.tv_privacy);
        TextView tv_look_photo = view.findViewById(R.id.tv_look_photo);
        TextView tv_about = view.findViewById(R.id.tv_about);
        TextView tv_new = view.findViewById(R.id.tv_new);
        TextView tv_log_off = view.findViewById(R.id.tv_log_off);

        //给点击处设置点击事件
        iv_headphoto.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
        tv_safety.setOnClickListener(this);
        tv_privacy.setOnClickListener(this);
        tv_look_photo.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_new.setOnClickListener(this);
        tv_log_off.setOnClickListener(this);
        return view;
    }

    /**
     * 给点击处设置点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_headphoto:

                break;
            case R.id.tv_edit:
                intent = new Intent(getContext(), DetailsActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.tv_safety:
                intent = new Intent(getContext(), SafetyActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_privacy:
                ToastUtil.show(getContext(),"隐私管理，有待处理");
                break;
            case R.id.tv_look_photo:
                ToastUtil.show(getContext(),"图片浏览设置，有待处理");
                break;
            case R.id.tv_about:
                intent = new Intent(getContext(),AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_new:
                ToastUtil.show(getContext(),"新手引导，有待处理");
                intent = new Intent(getContext(),GuideActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_log_off:
                showPopupWindow();
                break;
            case R.id.tv_ensure_log_off:
                SpUtil.remove(getContext(), ContantsValue.LOGIN_OVER);
                intent = new Intent(getContext(),SplashActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.tv_cancel:
                mPopupWindow.dismiss();
                break;
        }
    }

    /**
     * 从下网上弹出窗口
     */
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow, null);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT) );
        mPopupWindow.setOutsideTouchable(true);
        TextView tv_ensure_log_off = contentView.findViewById(R.id.tv_ensure_log_off);
        TextView tv_cancel = contentView.findViewById(R.id.tv_cancel);
        tv_ensure_log_off.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        mPopupWindow.setAnimationStyle(R.style.contextMenuAnim);
        mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM,0,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (RESULT_OK == resultCode) {
                    Bundle bundle = data.getExtras();
                    String dd = bundle.getString("dd");
                    System.out.println(dd);
                    ToastUtil.show(getContext(), dd);
                }
                break;
            case 2:
                if (RESULT_DETAILS == resultCode) {
                    Bundle bundle = data.getExtras();
                    String dd = bundle.getString("ee");
                    System.out.println(dd);
                    ToastUtil.show(getContext(), dd);
                }
                break;
        }
    }
}
