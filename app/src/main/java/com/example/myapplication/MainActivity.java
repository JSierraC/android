package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView resultado, resultado2;
    EditText num1, num2;
    double n1, n2, suma;
    Button btn1, btn2;
    String opciones[];
    Spinner combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = (TextView) findViewById(R.id.txtResultado);
        resultado2 = (TextView) findViewById(R.id.txtResultado2);
        num1 = (EditText) findViewById(R.id.txtNumeroUno);
        num2 = (EditText) findViewById(R.id.txtNumeroDos);
        combo = (Spinner) findViewById(R.id.cmbOperacion);

        opciones = getResources().getStringArray(R.array.operaciones);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, opciones);
        combo.setAdapter(adapter);
        btn1 = (Button)findViewById(R.id.btnCalcular);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar()){

                n1 = Double.parseDouble(num1.getText().toString());
                n2 = Double.parseDouble(num2.getText().toString());
                switch (combo.getSelectedItemPosition()){
                    case 0:
                        suma = n1+n2;
                        break;
                    case 1:
                        suma = n1-n2;
                        break;
                    case 2:
                        suma = n1*n2;
                        break;
                    case 3:
                        suma = n1/n2;
                        break;
                    default:
                        suma = 0;
                        break;
                }
                Log.d("index", ""+combo.getSelectedItemPosition());
                resultado.setText(""+suma);
                resultado2.setText(combo.getSelectedItem().toString());
                }
            }
        });


        btn2 = (Button)findViewById(R.id.btnBorrar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText("");
                num2.setText("");
                resultado.setText("");
                resultado2.setText("");
                combo.setSelection(0);
                num1.requestFocus();
            }
        });
    }

    public boolean validar(){
        int opc = combo.getSelectedItemPosition();
        if(num1.getText().toString().isEmpty()){
            num1.setError(getResources().getString(R.string.msj1));
            num1.requestFocus();
            return false;
        }else if(num2.getText().toString().isEmpty()){
            num2.setError(getResources().getString(R.string.msj2));
            num2.requestFocus();
            return false;
        }else if(Double.parseDouble(num2.getText().toString())==0 && opc==3 ){
            num2.setError(getResources().getString(R.string.msj3));
            num2.selectAll();
            return false;
        }
        return true;
    }
}
