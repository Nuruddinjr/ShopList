package uz.iutlab.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView loggedAs;
    ListView productsList;
    Button buyButton;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggedAs = (TextView)findViewById(R.id.loggedAsXML);
        productsList = (ListView)findViewById(R.id.productsListXML);
        buyButton = (Button)findViewById(R.id.buyButtonXML);

        Intent intent = new Intent(getIntent());
        userName = intent.getStringExtra("user_name");

        loggedAs.setText(getString(R.string.logged_as, userName));




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.available_products, android.R.layout.simple_list_item_multiple_choice);
        productsList.setAdapter(adapter);
        productsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int choice_count= productsList.getCount();

                String checked = "";
                SparseBooleanArray sparseBooleanArray = productsList.getCheckedItemPositions();

                for(int i = 0; i < choice_count; i++)
                {

                    if(sparseBooleanArray.get(i) == true)
                    {
                        checked += productsList.getItemAtPosition(i).toString() + " ";

                    }
                    else{

                    }

                }
                if(checked!=null && !checked.equals("")){
                    Toast.makeText(MainActivity.this, checked, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SendEmailActivity.class);
                    intent.putExtra("checked_items",checked);
                    intent.putExtra("user_name",userName);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Please choose item", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }





}
