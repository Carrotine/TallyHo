package biz.notacompany.tallyho;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class TallyAdapter extends BaseAdapter
{
    private static int TALLY_SIZE = 5;
    private Context _context;
    private int _count = 0;
    private TallyAdapter(Context context)
    {
        this._context = context;
    }
    TallyAdapter(Context context, int initialCount)
    {
        this(context);
        _count = initialCount;
    }
    @Override
    public int getCount()
    {
        return (_count / TALLY_SIZE)+1;
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ImageView imageView;
        if (view == null)
        {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(_context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(tallyImageIds[tallyState(i)]);
        return imageView;
    }
    void addTally()
    {
        this._count += 1;
    }
    void removeTally()
    {
        if (_count >= 1)
            this._count -= 1;
    }
    int getTallies()
    {
        return _count;
    }
    void setTallies(int newTallies)
    {
        this._count = newTallies;
    }
    private int tallyState(int i)
    {
        int tallyGroups = _count / TALLY_SIZE;
        if (tallyGroups <= i)
        {
            return (_count - (TALLY_SIZE * tallyGroups));
        }
        else
            return TALLY_SIZE;
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
