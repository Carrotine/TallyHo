package biz.notacompany.tallyho;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Observable;
import java.util.Observer;

public class Tally extends android.support.v7.widget.AppCompatImageView
{
    private int _tally = 0;
    public Tally(Context context)
    {
        super(context);
    }

    public Tally(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public Tally(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setTally(int tally)
    {
        this._tally = tally;
        this.setImageResource(tallyImageIds[_tally]);
    }

    private Integer[] tallyImageIds = {
            R.drawable.tally0,
            R.drawable.tally1,
            R.drawable.tally2,
            R.drawable.tally3,
            R.drawable.tally4,
            R.drawable.tally5,
    };

}
