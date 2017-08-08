package zhou.com.loveyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import zhou.com.loveyou.R;

public class UploadActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initUI();
    }

    private void initUI() {
        ImageView iv_upload_back = findViewById(R.id.iv_upload_back);
        iv_upload_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_upload_back:
                finish();
                break;
        }
    }
}
