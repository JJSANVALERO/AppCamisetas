package com.svalero.appCamisetas;

import static com.svalero.appCamisetas.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.appCamisetas.db.AppDatabase;
import com.svalero.appCamisetas.domain.Shirt;


public class ShirtDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt_details);

        Intent intent = getIntent();
        String model = intent.getStringExtra("model");
        if (model == null)
            return;

        // Cargo los detalles del aviso
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Shirt shirt = db.shirtDao().getByModel(model);
        fillData(shirt);

    }


    private void fillData(Shirt shirt) {
        TextView tvModel = findViewById(R.id.tv_shirt_model);
        TextView tvDescription = findViewById(R.id.tv_shirt_description);
        TextView tvBrand = findViewById(R.id.tv_shirt_brand);

        tvModel.setText(shirt.getModel());
        tvDescription.setText(shirt.getDescription());
        tvBrand.setText(shirt.getBrand());
    }

    public void modifyButtonClicked(View view) {
        Intent intent = new Intent(this,ModifyShirtActivity.class);
        startActivity(intent);
    }


}