package biz.notacompany.tallyho;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    TallyAdapter adapter;
    SharedPreferences preferences;
    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Toast.makeText(this, String.valueOf(myToolbar.showOverflowMenu()), Toast.LENGTH_SHORT).show();
        preferences = this.getPreferences(Context.MODE_PRIVATE);
        adapter = new TallyAdapter(this, preferences.getInt(getString(R.string.pref_tallies), 0));

        myToolbar.setSubtitle(String.valueOf(adapter.getTallies()));
        GridView grid = findViewById(R.id.grid_Tallies);
        grid.setAdapter(adapter);
    }
    protected void onPause()
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(getString(R.string.pref_tallies), adapter.getTallies());
        edit.apply();
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.item_setTallies:
                setTallies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addTally(View view)
    {
        adapter.addTally();
        adapter.notifyDataSetChanged();
        myToolbar.setSubtitle(String.valueOf(adapter.getTallies()));
    }

    public void removeTally(View view)
    {
        adapter.removeTally();
        adapter.notifyDataSetChanged();
        myToolbar.setSubtitle(String.valueOf(adapter.getTallies()));
    }



    public void setTallies()
    {
        final EditText inputTallies = new EditText(this);
        inputTallies.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputTallies.setHint(String.valueOf(adapter.getTallies()));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_settally);
        builder.setView(inputTallies);
        builder.setPositiveButton(R.string.alert_set, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                adapter.setTallies(Integer.parseInt(inputTallies.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // nothing
            }
        });
        builder.show();
    }
}
