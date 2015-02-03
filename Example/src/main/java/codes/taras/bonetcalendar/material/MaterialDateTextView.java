package codes.taras.bonetcalendar.material;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bonet.example.bonetcalendarviewactivity.R;

/**
 *
 * @author Taras Rogov
 *
 */
public class MaterialDateTextView extends TextView {

    private int selectorColor = getContext().getResources().getColor(R.color.material_selected_day_blue);
    private boolean checked = false;

    public MaterialDateTextView(Context context) {
        super(context);
    }

    public MaterialDateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialDateTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //will make view square.
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int size = right - left;
        if(isChecked()) {
            if(Build.VERSION.SDK_INT >= 16) {
                // drawing the circle selector
                Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(selectorColor);
                float circleCenter = size / 2;
                float circleRadius = size / 2 - 0.2f * size;
                canvas.drawCircle(circleCenter, circleCenter, circleRadius, paint);
                Drawable src = new BitmapDrawable(getResources(), bitmap);
                this.setBackground(src);
            } else {
                this.setBackgroundColor(selectorColor);
            }
        } else {
            this.setBackgroundResource(android.R.color.transparent);
        }
        super.onLayout(changed, left, top, right, top + size);
    }

    public int getSelectorColor() {
        return selectorColor;
    }

    public void setSelectorColor(int selectorColor) {
        this.selectorColor = selectorColor;
    }

    public void setSelectorColorResource(int selectorColorResource) {
        this.selectorColor = getContext().getResources().getColor(selectorColorResource);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        if(this.checked != checked) {
            invalidate();
            requestLayout();
        }
        this.checked = checked;
    }

}
