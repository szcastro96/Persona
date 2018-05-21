package com.gamma.persona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gamma.persona.Datos.Persona;
import com.gamma.persona.entidades.DBHelper;

public class ModificarActivity extends AppCompatActivity {

    private EditText id, nombre;
    private Button btnBuscar, btnEliminar, btnActualizar, btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        inicializarControles();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona p = DBHelper.myDB.findUser(id.getText().toString());
                if (p == null){
                    Toast.makeText(getApplicationContext(), "El usuario no fue encontrado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
                else{
                    nombre.setText(p.getNombre());
            }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper.myDB.editUser(new Persona(id.getText().toString(),nombre.getText().toString()));
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper.myDB.deleteUser(id.getText().toString());
                limpiar();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }

    public void inicializarControles(){
        id = findViewById(R.id.duiM);
        nombre = findViewById(R.id.txtNombreM);
        btnBuscar = findViewById(R.id.buttonBuscarM);
        btnEliminar = findViewById(R.id.buttonEliminar);
        btnActualizar = findViewById(R.id.buttonActualizar);
        btnLimpiar = findViewById(R.id.buttonLimpiar);
    }

    public void limpiar(){
        nombre.setText("");
        id.setText("");
    }

}
