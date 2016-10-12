package your.package.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jrvansuita on 30/07/15.
 */
public class Icon {

    private int alpha = ImageBuilder.DEFAULT_ALPHA;
    private int color = 0;
    private int icon = 0;
    private boolean reverseAlpha = false;
    private boolean focus = false;
    private Bitmap bitmap;
    
    Icon(ImageView iv) {
        this.iv = iv;
    }

    private ImageView iv;

    public static Icon on(ImageView iv) {
        return new Icon(iv);
    }

    public static void put(ImageView iv, int icon) {
        new Icon(iv).icon(icon).put();
    }

    Icon(View v) {
        this.v = v;
    }

    private View v;

    public static void put(View v, int icon) {
        new Icon(v).icon(icon).put();
    }

    public static Icon on(View iv) {
        return new Icon(iv);
    }

    Icon(TextView tv, int pos) {
        this.tv = tv;
        this.pos = pos;
    }

    private TextView tv;
    private int pos;

    public static Icon left(TextView tv) {
        return new Icon(tv, Gravity.LEFT);
    }

    public static void left(TextView tv, int icon) {
        put(tv, icon, Gravity.LEFT);
    }

    public static Icon right(TextView tv) {
        return new Icon(tv, Gravity.RIGHT);
    }

    public static void right(TextView tv, int icon) {
        put(tv, icon, Gravity.RIGHT);
    }

    public static Icon top(TextView tv) {
        return new Icon(tv, Gravity.TOP);
    }

    public static void top(TextView tv, int icon) {
        put(tv, icon, Gravity.TOP);
    }

    public static Icon bottom(TextView tv) {
        return new Icon(tv, Gravity.BOTTOM);
    }

    public static void bottom(TextView tv, int icon) {
        put(tv, icon, Gravity.BOTTOM);
    }

    private static void put(TextView tv, int icon, int pos) {
        new Icon(tv, pos).icon(icon).put();
    }

    public Icon alpha(int a) {
        this.alpha = a;
        return this;
    }

    public Icon color(int res) {
        this.color = res;
        return this;
    }

    public Icon icon(int res) {
        this.icon = res;
        return this;
    }

    public Icon bitmap(Bitmap b) {
        this.bitmap = b;
        return this;
    }

    public Icon white(Bitmap bitmap) {
        return bitmap(bitmap).color(android.R.color.white);
    }

    public Icon white(int icon) {
        return icon(icon).color(android.R.color.white);
    }

    public Icon blue(Bitmap bitmap) {
        return bitmap(bitmap).color(android.R.color.holo_blue_dark);
    }

    public Icon blue(int icon) {
        return icon(icon).color(android.R.color.holo_blue_dark);
    }

    public Icon black(int icon) {
        return icon(icon).color(android.R.color.black);
    }

    public Icon gray(int icon) {
        return icon(icon).color(android.R.color.darker_gray);
    }

    public static void clear(TextView tv) {
        tv.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public void put() {
        if (v != null) {
            Utils.background(v, new SelectorDrawable(v.getContext()));
        } else if (iv != null) {
            iv.setImageDrawable(new SelectorDrawable(iv.getContext()));
        } else if (tv != null) {
            tv.setCompoundDrawablesWithIntrinsicBounds(getForPosition(Gravity.LEFT), getForPosition(Gravity.TOP), getForPosition(Gravity.RIGHT), getForPosition(Gravity.BOTTOM));
        }
    }

    private Drawable getForPosition(int i) {
        return pos == i ? new SelectorDrawable(tv.getContext()) : null;
    }

    public Icon reverse(int icon) {
        this.reverseAlpha = !reverseAlpha;
        return this.icon(icon);
    }

    public static void focus(TextView tv, int icon) {
        focus(tv, icon, Gravity.LEFT);
    }


    public static void focus(TextView tv, int icon, int pos) {
        Icon i = new Icon(tv, pos);
        i.focus = true;
        i.icon(icon).put();
    }

    public class SelectorDrawable extends StateListDrawable {
        private Context context;

        public SelectorDrawable(Context context) {
            super();
            this.context = context;

            if (focus) {
                addState(new int[]{android.R.attr.state_focused}, get(reverseAlpha));
                addState(StateSet.WILD_CARD, get(!reverseAlpha));
            } else {
                addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, get(!reverseAlpha));
                addState(new int[]{-android.R.attr.state_enabled}, get(!reverseAlpha));
                addState(StateSet.WILD_CARD, get(reverseAlpha));
            }
        }

        @Override
        protected boolean onStateChange(int[] states) {
            if (context != null && color > 0) {
                setColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_IN);
            }

            return super.onStateChange(states);
        }

        private Drawable get(boolean putAlpha) {
            Drawable d;

            if (bitmap != null) {
                d = new BitmapDrawable(context.getResources(), bitmap);
            } else {
                d = context.getResources().getDrawable(icon);
            }

            return new BitmapDrawable(context.getResources(), alpha(((BitmapDrawable) d).getBitmap(), putAlpha ? alpha : 255));
        }
    }
    
     public static Bitmap alpha(Bitmap input, int alpha) {
        Bitmap output = Bitmap.createBitmap(input.getWidth(), input.getHeight(), input.getConfig());

        Paint transparentPaint = new Paint();
        transparentPaint.setAlpha(alpha);

        Canvas canvas = new Canvas(output);
        canvas.drawBitmap(input, 0, 0, transparentPaint);


        return output;
    }
}
