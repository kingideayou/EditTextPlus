package me.next.edittextplus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


/**
 * Created by NeXT on 16/5/11.
 */
public class EditTextPlus extends EditText {

    private static final int BUTTON_RES_ID = R.drawable.et_right_hide;
    private static final int BUTTON_CHECKED_RES_ID = R.drawable.et_right_show;
    private static final int BUTTON_PRESSED_RES_ID = R.drawable.et_right_show;

    private int buttonResId;
    private int buttonCheckedResId;
    private int buttonPressedResId;

    private Drawable buttonNormalDrawable;
    private Drawable buttonPressedDrawable;
    private Drawable buttonCheckedDrawable;

    private OnButtonClickListener mOnButtonClickListener;
    private OnButtonCheckListener mOnButtonCheckListener;

    private int editTextType = EditTextPlusType.NORMAL.ordinal();
    private boolean updateEditTextType = false;

    public EditTextPlus(Context context) {
        this(context, null);
    }

    public EditTextPlus(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle); //attrs can not set 0
    }

    public EditTextPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initResource(context, attrs);
        if (editTextType != EditTextPlusType.NORMAL.ordinal()) {
            initButton(context);
            initListener();
        }
    }

    private void initListener() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Drawable rightButton =  getCompoundDrawables()[2];
                if (rightButton == null) {
                    return false;
                }
                if (motionEvent.getAction() != MotionEvent.ACTION_DOWN) {
                    return false;
                }
                if (motionEvent.getX() > getMeasuredWidth() - getPaddingRight()
                        - rightButton.getIntrinsicWidth()) {
                    if (editTextType == EditTextPlusType.CLICKABLE.ordinal()) {
                        if (mOnButtonClickListener != null) {
                            mOnButtonClickListener.onButtonClick();
                        }
                    } else if (editTextType == EditTextPlusType.CHECKABLE.ordinal()) {
                        if (mOnButtonCheckListener != null) {
                            if (isChecked()) {
                                setInputType(129);//hide password
                            } else {
                                setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            }
                            mOnButtonCheckListener.onButtonCheck(isChecked());
                            setButtonVisible(true);
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextType == EditTextPlusType.CLICKABLE.ordinal() && isFocused()) {
                    if (s.length() > 0) {
                        setButtonVisible(true);
                    } else {
                        setButtonVisible(false);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void initButton(Context context) {
        buttonNormalDrawable = ContextCompat.getDrawable(context, buttonResId);
        buttonPressedDrawable = ContextCompat.getDrawable(context, buttonPressedResId);
        buttonCheckedDrawable = ContextCompat.getDrawable(context, buttonCheckedResId);
        setBounds(buttonNormalDrawable);
        setBounds(buttonPressedDrawable);
        setBounds(buttonCheckedDrawable);
        setButtonVisible(editTextType == EditTextPlusType.CHECKABLE.ordinal());
    }

    private void setBounds(Drawable drawable) {
        drawable.setBounds(0, 0,
                drawable.getIntrinsicWidth() / 2,
                drawable.getIntrinsicHeight() / 2);
    }

    private void initResource(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        buttonResId = typedArray.getResourceId(R.styleable.EditTextPlus_button_img_normal, BUTTON_RES_ID);
        buttonCheckedResId = typedArray.getResourceId(R.styleable.EditTextPlus_button_img_checked, BUTTON_CHECKED_RES_ID);
        buttonPressedResId = typedArray.getResourceId(R.styleable.EditTextPlus_button_img_pressed, BUTTON_PRESSED_RES_ID);
        editTextType = typedArray.getInt(R.styleable.EditTextPlus_etp_type, EditTextPlusType.NORMAL.ordinal());
        typedArray.recycle();
    }

    public void setButtonVisible(boolean showButton) {
        Drawable currentShowDrawable = null;
        if (editTextType == EditTextPlusType.CHECKABLE.ordinal()) {
            if (isChecked()) {
                currentShowDrawable = buttonCheckedDrawable;
            } else {
                currentShowDrawable = buttonNormalDrawable;
            }
            currentShowDrawable.setVisible(true, false);
        } else if (editTextType == EditTextPlusType.CLICKABLE.ordinal()) {
            currentShowDrawable = buttonNormalDrawable;
            currentShowDrawable.setVisible(showButton, false);
        }
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(
                drawables[0],
                drawables[1],
                showButton ? currentShowDrawable : null,
                drawables[3]);
    }

    public boolean isChecked() {
        return getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    }

    public enum EditTextPlusType {
        NORMAL, CLICKABLE, CHECKABLE
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        mOnButtonClickListener = onButtonClickListener;
    }

    public void setOnButtonCheckListener(OnButtonCheckListener onButtonCheckListener) {
        mOnButtonCheckListener = onButtonCheckListener;
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

    public interface OnButtonCheckListener {
        void onButtonCheck(boolean isChecked);
    }

}
