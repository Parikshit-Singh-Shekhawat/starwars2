package app.starwars.decorators;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by apple on 25/08/18.
 */

public class DividerDecorator extends RecyclerView.ItemDecoration {

    private static final int[] ATTRIBUTES = new int[]{

            android.R.attr.listDivider
    };

    private Drawable mDivider;
    private int mOrientation;
    private Context context;
    private int margin;

    public DividerDecorator(Context context, int orientation, int margin) {

        this.context = context;
        this.margin = margin;
        final TypedArray a = context.obtainStyledAttributes(ATTRIBUTES);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {

        mOrientation = orientation;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

            drawVertical(c, parent);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left + dpToPx(margin), top, right - dpToPx(margin), bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }

    private int dpToPx(int dp) {

        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
