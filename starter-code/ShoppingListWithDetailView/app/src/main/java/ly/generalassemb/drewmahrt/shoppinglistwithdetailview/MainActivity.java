package ly.generalassemb.drewmahrt.shoppinglistwithdetailview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistwithdetailview.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        ShoppingSQLiteOpenHelper instance = new ShoppingSQLiteOpenHelper(this);
        ListView mListView = (ListView) findViewById(R.id.shopping_list_view);

        final Cursor cursor = instance.showAllGroceries();
        CursorAdapter adapter = new CursorAdapter(MainActivity.this, cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return LayoutInflater.from(context).inflate(R.layout.grocery_item, viewGroup, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String groceryName = cursor.getString(cursor.getColumnIndex(
                        ShoppingSQLiteOpenHelper.COL_ITEM_NAME));

                TextView name = (TextView) view.findViewById(R.id.grocery_name);

                name.setText(groceryName);
            }
        };
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                cursor.moveToPosition(i);
                int id = cursor.getInt(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ID));
                intent.putExtra(ShoppingSQLiteOpenHelper.COL_ID, id);
                startActivity(intent);
            }
        });
    }
}
