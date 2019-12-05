package me.sailor.demolist.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.sailor.demolist.R;
import me.sailor.libcommon.utils.DensityUtils;


/**
 * @author sailor
 * @description
 * @time 2018/11/27
 */
public class TitleRadioGroupView extends RadioGroup implements CompoundButton.OnCheckedChangeListener {

    /**
     * 标题类型
     */
    private int titleStyle;
    /**
     * 标题类型 默认为0 0为纯文本，其他为图片加文字
     */
    private final static int PLAIN_TEXT = 0;

    /**
     * 标题上面图片，最多四个标题，从左至右排列
     */
    private int pic1, pic2, pic3, pic4;
    /**
     * 图片list
     */
    private ArrayList<Integer> imgs;
    /**
     * 默认字体大小
     */
    private final static float DEFAULT_TEXT_SIZE = 50;

    /**
     * 标题控件列表
     */
    private List<RadioButton> mRadioButtons;
    /**
     * 标题背景色
     */
    private int titleBg;

    /**
     * 标题文字列表
     */
    private String[] mStrings;

    /**
     * 标题颜色
     */
    private int textColor;
    /**
     * 标题中间padding centerPadding
     */
    private int centerMargin;

    /**
     * 标题padding
     */
    private int top_bottom_padding, left_right_padding;
    /**
     * 标题文字大小
     */
    private float titleSize;

    private TitleSelectedListener mListener;


    public TitleRadioGroupView(Context context) {
        super(context, null);
    }

    public TitleRadioGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initLayout(context);
        initView();
    }



    private void initAttrs(Context context, AttributeSet attrs) {
        imgs = new ArrayList<>();
        setOrientation(HORIZONTAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleRadioGroupView);

        textColor = typedArray.getResourceId(R.styleable.TitleRadioGroupView_titleColor, R.color.selector_texttitleradio);
        titleBg = typedArray.getResourceId(R.styleable.TitleRadioGroupView_titleBg, R.drawable.selector_bgtitleradio);

        titleSize = typedArray.getDimension(R.styleable.TitleRadioGroupView_titleSize, DEFAULT_TEXT_SIZE);
        titleStyle = typedArray.getInt(R.styleable.TitleRadioGroupView_titleStyle, PLAIN_TEXT);
        top_bottom_padding = typedArray.getDimensionPixelOffset(R.styleable.TitleRadioGroupView_top_bottom_padding, 0);
        left_right_padding = typedArray.getDimensionPixelOffset(R.styleable.TitleRadioGroupView_left_right_padding, 0);
        centerMargin = typedArray.getDimensionPixelSize(R.styleable.TitleRadioGroupView_titleMargin, 0);
        //转为sp
        titleSize = DensityUtils.px2sp(context, titleSize);
        /**
         * 标题处理
         * 标题用“，”隔开，如：标题1，标题2
         */
        mStrings = Objects.requireNonNull(typedArray.getString(R.styleable.TitleRadioGroupView_titles)).split("，");
        pic1 = typedArray.getResourceId(R.styleable.TitleRadioGroupView_picofTitle1, 0);
        pic2 = typedArray.getResourceId(R.styleable.TitleRadioGroupView_picofTitle2, 0);
        pic3 = typedArray.getResourceId(R.styleable.TitleRadioGroupView_picofTitle3, 0);
        pic4 = typedArray.getResourceId(R.styleable.TitleRadioGroupView_picofTitle4, 0);
        imgs.add(pic1);
        imgs.add(pic2);
        imgs.add(pic3);
        imgs.add(pic4);
        typedArray.recycle();
    }
    private void initLayout(Context context) {
        mRadioButtons = new ArrayList<>();
        //设置radioButton LayoutParams的Margin,什么父布局就要用什么布局的LayoutParams，
        //比如这里要用RadioGroup的LayoutParams，LinearLayout不生效。
//        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        RadioGroup.LayoutParams tvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tvParams.setMargins(centerMargin / 2, 0, centerMargin / 2, 0);
        tvParams.weight = 1;
        tvParams.gravity = Gravity.CENTER;
        //根据标题个数循环创建设置radioButton
        for (int i = 0; i < mStrings.length; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setButtonDrawable(null);
            radioButton.setLayoutParams(tvParams);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setOnCheckedChangeListener(this);
            //设置Tag，Activity可根据tag判断是哪个radioButton被点击
            radioButton.setTag(i);
            addView(radioButton);
            mRadioButtons.add(radioButton);
        }
        //代码实现默认第一个选中
        mRadioButtons.get(0).setId(R.id.radiogroup_left_btn);
        check(mRadioButtons.get(0).getId());

    }

    private void initView() {
        //测试不判断为空   bg颜色默认view有这个属性
        if (titleStyle == 0) {
            for (int j = 0; j < mStrings.length; j++) {
                mRadioButtons.get(j).setText(mStrings[j]);
                mRadioButtons.get(j).setTextSize(titleSize);
                mRadioButtons.get(j).setTextColor(getResources().getColorStateList(textColor));
                mRadioButtons.get(j).setBackgroundResource(titleBg);
                mRadioButtons.get(j).setPadding(left_right_padding, top_bottom_padding, left_right_padding, top_bottom_padding);
            }
        } else if (titleStyle == 1) {
            Drawable top;
            for (int j = 0; j < mStrings.length; j++) {
                top = getResources().getDrawable(imgs.get(j));
                mRadioButtons.get(j).setText(mStrings[j]);
                mRadioButtons.get(j).setTextSize(titleSize);
                mRadioButtons.get(j).setTextColor(getResources().getColorStateList(textColor));
                mRadioButtons.get(j).setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                mRadioButtons.get(j).setBackgroundResource(titleBg);
                mRadioButtons.get(j).setPadding(left_right_padding, top_bottom_padding, left_right_padding, top_bottom_padding);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            if (mListener != null) {
                mListener.titleSelected(buttonView, isChecked);
            }
        }


//        buttonView.setTextSize(isChecked ? titleSize + 3 : titleSize);
    }


    public interface TitleSelectedListener {
        void titleSelected(CompoundButton buttonView, boolean isChecked);
    }

    public void setTitleSelectedListener(TitleSelectedListener listener) {
        this.mListener = listener;
    }

}
