package cn.qzb.richeditor;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;


import java.util.List;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020-8-1
 * email:954101549@qq.com
 */
public class RE {
    private RichEditor editor = null;
    private int fontColor = 0xFF5E5E5E;
    private int fontBackGroundColor = Color.WHITE;
    private int fontSize = 3;
    private int lineHeight = 30;
    private boolean isBold = false;
    private boolean isItalic = false;
    private boolean isUnderline = false;
    private boolean isFocus = false;// 是否获取到焦点
    private String preState = "";

    private int textAlign = 0;//1left,2center,3right

    public RichEditor getEditor() {
        return editor;
    }

    public void setEditor(RichEditor editor) {
        this.editor = editor;
    }

    public int getFontColor() {
        return fontColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public int getFontBackGroundColor() {
        return fontBackGroundColor;
    }

    public void setFontBackGroundColor(int fontBackGroundColor) {
        this.fontBackGroundColor = fontBackGroundColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public void setItalic(boolean italic) {
        isItalic = italic;
    }

    public boolean isUnderline() {
        return isUnderline;
    }

    public void setUnderline(boolean underline) {
        isUnderline = underline;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public String getPreState() {
        return preState;
    }

    public void setPreState(String preState) {
        this.preState = preState;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    private String html;

    public int getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(int textAlign) {
        this.textAlign = textAlign;
    }

    public final static RE getInstance(RichEditor mEditor){
        RE re = new RE();
        re.init(mEditor);
        return re;
    }

    public final String getHtml() {
        if (editor.getHtml() == null || (TextUtils.isEmpty(editor.getText()) && !editor.getHtml().contains("<img") && editor.getHtml().startsWith("<") && !editor.getHtml().contains("&nbsp"))){
            return "";
        }else{
            return editor.getHtml();
        }
    }

    public final void setHtml(String value) {
        editor.setHtml(value);
    }

    private final void init(RichEditor mEditor) {
        if (mEditor == null){
            return;
        }
        this.editor = mEditor;
        editor.setTextColor(fontColor);
        editor.setTextBackgroundColor(fontBackGroundColor);
        editor.setFontSize(fontSize);
        editor.setLineHeight(lineHeight);
        mEditor.setOnDecorationChangeListener(new RichEditor.OnDecorationStateListener() {
            @Override
            public void onStateChangeListener(String text, List<RichEditor.Type> types) {
                Log.e("onStateChangeListener", text);
                Log.e("onStateChangeListener", getHtml());
                if (preState.equals(text)){
                    return;
                }
                preState = text;
                if (editor.canUpdate){
                    if (isBold != types.contains(RichEditor.Type.BOLD)){
                        editor.setBold();
                    }
                    if (isItalic != types.contains(RichEditor.Type.ITALIC)) {
                        editor.setItalic();
                    }
                    if (isUnderline != types.contains(RichEditor.Type.UNDERLINE)) {
                        editor.setUnderline();
                    }
                }
            }
        });
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                isFocus = true;// 文本改动过,说明肯定获取到了焦点
            }
        });
    }

    public final void setPlaceHolder(String placeHolder) {
        if (placeHolder != null){
            editor.setPlaceholder(placeHolder);
        }
    }

    /**
     * 是否可编辑
     */
    public final void setEditable(boolean editable) {
        editor.setInputEnabled(editable);
    }

    /**
     * 加粗或取消加粗
     */
    public final void setBold(){
        isBold = !isBold;
        editor.setBold();
    }

    /**
     * 斜体或取消斜体
     */
    public final void setItalic(){
        isItalic = !isItalic;
        editor.setItalic();
    }

    /**
     * 设置字体颜色
     */
    public final void setTextColor(int color) {
        fontColor = color;
        editor.setTextColor(color);
        reFreshState();
    }

    /**
     * 设置文字背景色
     */
    public final void setTextBackgroundColor(int color) {
        fontBackGroundColor = color;
        editor.setTextBackgroundColor(color);
        reFreshState();
    }

    /**
     * The size value must be between 1 and 7,
     * default value is 3
     *
     * @param fontSize
     */
    public final void setTextSize(int fontSize) {
        if (fontSize < 0 || fontSize > 7) {
            throw new IllegalStateException("The size value must be between 1 and 7");
        }
        this.fontSize = fontSize;
        editor.setFontSize(fontSize);
        reFreshState();
    }

    public final void setPadding(int left, int top, int right, int bottom) {
        editor.setPadding(left, top, right, bottom);
    }

    /**
     * 插入图片
     */
    public final void insertImage(String url, String alt) {
        insertImage(url, alt, 100);
    }

    /**
     * 插入图片
     * imageWidthPercent: 图片占屏宽度百分比
     */
    public final void insertImage(String url, String alt, int imageWidthPercent) {
        if (url != null){
            if (!isFocus) {
                editor.focusEditor();
            }
            editor.insertImage(url, alt, imageWidthPercent);
        }
    }

    /**
     * 图片大小自适应
     * 原始图片有多大,插入就有多大,宽高不做限制
     */
    public final void insertImageWrapWidth(String url, String alt) {
        if (url != null){
            if (!isFocus) {
                editor.focusEditor();
            }
            editor.insertImageWrapWidth(url, alt);
        }
    }

    /**
     * 下划线或取消下划线
     */
    public final void setUnderLine() {
        isUnderline = !isUnderline;
        editor.setUnderline();
    }

    /**
     * 撤回
     */
    public final void undo(){
        editor.undo();
    }

    /**
     * 恢复
     */
    public final void redo(){
        editor.redo();
    }

    /**
     * 居中
     */
    public final void setAlignCenter(){
        textAlign = 2;
        editor.setAlignCenter();
    }

    /**
     * 左对齐
     */
    public final void setAlignLeft(){
        textAlign = 1;
        editor.setAlignLeft();
    }
    /**
     * 右对齐
     */
    public final void setAlignRight(){
        textAlign = 3;
        editor.setAlignRight();
    }


    /**
     * 刷新编辑框状态
     * 这里加延时,是因为如果刚进页面就刷新状态的话,有时候页面还没有渲染好,刷新的状态获取不到
     * 一般如果出现状态错乱,可以尝试这里加点延时看看效果
     */
    public final void reFreshState() {
        reFreshState(0);
    }
    public final void reFreshState(long delay) {
        editor.postDelayed(new Runnable() {
            @Override
            public void run() {
                editor.refreshState();
            }
        },delay);
    }


    /**
     * 获取焦点,需要注意调用此方法后,placeholder会消失
     */
    public final void focus(){
        editor.focusEditor();
    }

    /**
     * 将编辑框滑动到最后
     */
    public final void moveToEnd(){
        editor.moveToEnd();
    }

    /**
     * 使用场景一般为要编辑某段富文本的时候,刚进入页面的时候,光标要显示到最后,并且编辑框的内容也要滑动到底部
     * 将div滑动到最后,可以配合focus()一起使用
     * 需要注意的是,刚进入页面的时候马上调用此方法可能会无效,因为页面还没有渲染好
     * 最好延时几百毫秒后调用
     */
    public final void moveToEndEdit(){
        focus();
        editor.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) editor.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editor, 0);
                moveToEnd();
                reFreshState(300);
            }
        },200);
    }

    /**
     * 设置行高
     * @param heightInPixel
     */
    public final void setLineHeight(int heightInPixel){
        editor.setLineHeight(heightInPixel);
    }
}


//<!--    $(".content").on('paste', function (e) {-->
//<!--        var $self = $(this);-->
//<!--        setTimeout(function() {-->
//<!--                var paste_values = $self.text();-->
//<!--                paste_values = paste_values.replace(/<[^<]*>/g,'');-->
//<!--                $self.text(paste_values);-->
//<!--        },0);-->
//<!--    });-->