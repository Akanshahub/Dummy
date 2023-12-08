package com.example.sqldemo3;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.sqldemo3.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    CustomerAdapter customerAdapter;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        dataBaseHelper=new DataBaseHelper(MainActivity.this);

        /* To show the list as soon as customer open the app. */
        ShowCustomersOnListVIew(dataBaseHelper);

        binding.btView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                    DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);


                ShowCustomersOnListVIew(dataBaseHelper);
                //Toast.makeText(MainActivity.this,everyone.toString(),Toast.LENGTH_SHORT).show();


            }
        });

binding.lvCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CustomerModel clickedCustomer= (CustomerModel) parent.getItemAtPosition(position);
        dataBaseHelper.delete(clickedCustomer);
        ShowCustomersOnListVIew(dataBaseHelper);
        Toast.makeText(MainActivity.this,"Deleted" + clickedCustomer.toString(),Toast.LENGTH_SHORT).show();    }
});

        binding.btAdd.setOnClickListener(v -> {
            CustomerModel customerModel1;
            try{
                customerModel1 = new CustomerModel(-1,binding.etName.getText().toString(),Integer.parseInt(binding.editTextNumber.getText().toString()),binding.swActive.isChecked());
               //Toast.makeText(MainActivity.this, customerModel1.getName().toString(),Toast.LENGTH_SHORT).show();

            }
            catch (Exception e){
                Toast.makeText(MainActivity.this,"Error Creating customer ",Toast.LENGTH_SHORT).show();
                customerModel1= new CustomerModel(-1,"error",0,false);
            }
            DataBaseHelper dataBaseHelper= new DataBaseHelper(MainActivity.this);
            boolean success = dataBaseHelper.addOne(customerModel1);
            ShowCustomersOnListVIew(dataBaseHelper);
            Toast.makeText(MainActivity.this,"Success= "+success,Toast.LENGTH_SHORT).show();
        });
    }

    private void ShowCustomersOnListVIew(DataBaseHelper dataBaseHelper) {
        customerAdapter = new CustomerAdapter(MainActivity.this,dataBaseHelper.getEveryone());
        binding.lvCustomerList.setAdapter(customerAdapter);

    }
}