package com.arthur.applistacompras.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.arthur.applistacompras.R;
import com.arthur.applistacompras.model.Lista;

public class MainActivity extends AppCompatActivity {

    private EditText editItemName;
    private EditText editItemPrice;
    private ListView listView;
    private Button buttonAdicionar;
    private Button buttonLimpar;
    private Lista lista;
    private TextView textViewQuantidade;
    private TextView textViewValor;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editItemName = findViewById(R.id.editItemName);
        editItemPrice = findViewById(R.id.editItemPrice);
        listView = findViewById(R.id.listView);
        buttonAdicionar = findViewById(R.id.button);
        buttonLimpar = findViewById(R.id.button2);
        lista = new Lista("Minha Lista de Compras");
        textViewQuantidade = findViewById(R.id.textViewQuantidade);
        textViewValor = findViewById(R.id.textViewValor);

        buttonAdicionar.setOnClickListener(v -> {
            String itemName = editItemName.getText().toString();
            String itemPrice = editItemPrice.getText().toString().trim();

            if (!itemPrice.isEmpty()) {
                try {
                    // Troque a vírgula por ponto se o usuário estiver usando separador decimal brasileiro
                    itemPrice = itemPrice.replace(",", ".");

                    double price = Double.parseDouble(itemPrice);
                    if (!itemName.isEmpty() && price > 0) {
                        lista.adicionarItem(itemName + " - " + "R$ " + price);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Digite um valor numérico válido (ex: 12.50)", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "O campo de preço está vazio", Toast.LENGTH_SHORT).show();
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista.getItens());
            listView.setAdapter(adapter);
            editItemName.setText("");
            editItemPrice.setText("");
        });

        buttonLimpar.setOnClickListener(v -> {
            lista.getItens().clear();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista.getItens());
            listView.setAdapter(adapter);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.removerItem(lista.getItens().get(position));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista.getItens());
            listView.setAdapter(adapter);
            return false;
        });


    }
}