package net.liucs.listviewdemo;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // Create an array containing the items for our list
            String[] data = new String[80];
            for(int i = 0; i < data.length; i++) {
                data[i] = "Item " + (i+1);
            }
            // Create an array adapter, specifying the layout and the
            // ID of the text view within that layout.
            ArrayAdapter<String> aa = new ArrayAdapter<String>(
                    getActivity(), R.layout.single_row, R.id.textView, data
                    );

//            IconItem[] items = {
//                    new IconItem(R.drawable.config_users, "Friends"),
//                    new IconItem(R.drawable.kcalc, "History"),
//                    new IconItem(R.drawable.kfm, "Log"),
//                    new IconItem(R.drawable.kedit, "Profile")
//            };
//            IconArrayAdapter aa = new IconArrayAdapter(getActivity(), R.layout.single_row, items);
            // Grab the list view and give it our adapter.
            ListView lv = (ListView)rootView.findViewById(R.id.listView);
            lv.setAdapter(aa);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("ListView", "You clicked #" + position);
                }
            });
            return rootView;
        }
    }

    public static class IconItem {
        public int drawable;
        public String label;
        public IconItem(int drawable, String label) {
            this.drawable = drawable;
            this.label = label;
        }
    }

    public static class IconArrayAdapter extends ArrayAdapter<IconItem> {

        private int rowLayout;
        private IconItem[] items;

        public IconArrayAdapter(Context context, int layout, IconItem[] data) {
            super(context, layout, data);
            this.rowLayout = layout;
            this.items = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(rowLayout, parent, false);
            IconItem item = items[position];
            TextView textView = (TextView) rowView.findViewById(R.id.textView);
            textView.setText(item.label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
            imageView.setImageResource(item.drawable);
            return rowView;
        }
    }
}
