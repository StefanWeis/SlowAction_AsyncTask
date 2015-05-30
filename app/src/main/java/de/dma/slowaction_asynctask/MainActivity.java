package de.dma.slowaction_asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(final View v) {

        EditText input = (EditText) findViewById(R.id.editText_eingabe);

        final String s = input.getText().toString();
        final String succFormatString = "SUCCESS";

        try {
            final long total = Long.parseLong(s);

            if (total < 0) {
                throw new NumberFormatException();
            }

            new MyAsyncTask() {

                @Override
                protected void onProgressUpdate(final Long... values) {
                    EditText input = (EditText) findViewById(R.id.editText_eingabe);
                    input.setText(String.valueOf(values[0]));
                }

                @Override
                protected void onPostExecute(final Long result) {
                    TextView output = (TextView) findViewById(R.id.textView_ergebnis);
                    output.setText(String.format(succFormatString, result));
                }
            }.execute(Long.valueOf(total));

        } catch (final Exception e) {
            TextView output = (TextView) findViewById(R.id.textView_ergebnis);
            final String message = "FAILURE" + " " + e.getMessage();
            output.setText(message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
