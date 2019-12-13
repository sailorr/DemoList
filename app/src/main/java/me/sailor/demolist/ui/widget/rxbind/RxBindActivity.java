package me.sailor.demolist.ui.widget.rxbind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxCompoundButton;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Unit;
import me.sailor.demolist.R;
import me.sailor.libcommon.base.BaseActivity;

public class RxBindActivity extends BaseActivity {
    private Button btn1, btn2, btn3, btn4;
    private CheckBox checkBox;
    private static final String TAG = "RxBindActivity";
    public CompositeDisposable compositeDisposable;
    private TextInputLayout til1, til2;
    private TextInputEditText edname, edpwd;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_rx_bind;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.reg);
        checkBox = findViewById(R.id.checkbox);
        til1 = findViewById(R.id.til);
        til2 = findViewById(R.id.til2);
        edname = findViewById(R.id.edName);
        edpwd = findViewById(R.id.edPwd);
        RxView.clicks(btn1)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        rxjava();
                    }
                });
        RxView.clicks(btn2)
                //首次监听 2秒内
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        showToast("两秒内只能点击一次！");
                    }
                });
        RxView.longClicks(btn3)

                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        showToast("long click");
                    }
                });


        btn4.setEnabled(false);

        RxCompoundButton.checkedChanges(checkBox)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        btn4.setEnabled(aBoolean);
                        btn4.setBackgroundResource(aBoolean ? R.color.gray1 : R.color.gray3);
                    }
                });
        RxView.clicks(btn4)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        til1.setErrorEnabled(false);
                        til2.setErrorEnabled(false);
                        //验证用户名和密码
                        if (validateAccount(edname.getText().toString()) && validatePassword(edpwd.getText().toString())) {
                            Toast.makeText(RxBindActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (account.isEmpty()) {
            showError(til1, "用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            showError(til2, "密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(til2, "密码长度为6-18位");
            return false;
        }

        return true;
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, RxBindActivity.class);
        context.startActivity(starter);
    }

    private void rxjava() {
        // 设置60秒
        final long count = 10;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                // map()转换
                .map(new Function<Long, Object>() {

                    @Override
                    public Object apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                //指定 Subscriber 的回调发生在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        btn1.setEnabled(false);
                        btn1.setTextColor(Color.GRAY);
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        btn1.setText(value + "秒重发");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        btn1.setEnabled(true);
                        btn1.setTextColor(Color.RED);
                        btn1.setText("发送验证码");
                    }
                });
    }
}
