package ly.generalassemb.drewmahrt.shoppinglistwithdetailview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int id = getIntent().getIntExtra(ShoppingSQLiteOpenHelper.COL_ID, 0);

        ShoppingSQLiteOpenHelper helper = new ShoppingSQLiteOpenHelper(this);

        String description = helper.getColItemDescription(id);
        String price = helper.getColItemPrice(id);
        String type = helper.getColItemType(id);
        String name = helper.getColItemName(id);

        TextView descriptionTextView = (TextView) findViewById(R.id.item_description);
        TextView priceTextView = (TextView) findViewById(R.id.item_price);
        TextView typeTextView = (TextView) findViewById(R.id.item_type);
        TextView nameTextView = (TextView) findViewById(R.id.name);

        descriptionTextView.setText(description);
        priceTextView.setText("$ "+price);
        typeTextView.setText("Type: "+type);
        nameTextView.setText(name);
    }
}
