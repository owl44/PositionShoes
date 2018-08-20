package com.shoes.position.ui.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joooonho.SelectableRoundedImageView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity implements OnClickListener {

    @BindView(R.id.head_img)
    SelectableRoundedImageView headImg;
    @BindView(R.id.mine_head_img)
    LinearLayout mineHeadImg;
    @BindView(R.id.nike_name)
    TextView nikeName;
    @BindView(R.id.mine_nike_name)
    LinearLayout mineNikeName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.mine_phone_number)
    LinearLayout minePhoneNumber;
    DialogPlus dialog = null;
    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_personal_info, "个人信息");
    }

    @Override
    protected void setLogic() {

    }

    @OnClick({R.id.mine_head_img,R.id.mine_nike_name,R.id.mine_phone_number})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_head_img:
                showSelect();
                break;
            case R.id.mine_nike_name:
                break;
            case R.id.mine_phone_number:
                break;
        }
    }

    public void showSelect(){
        dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.dialog_select_img))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setOnClickListener(this)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        dialog.show();
    }

    @Override
    public void onClick(DialogPlus dialog, View view) {
        switch (view.getId()){
            case R.id.photograph:
                break;
            case R.id.select_img:
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
