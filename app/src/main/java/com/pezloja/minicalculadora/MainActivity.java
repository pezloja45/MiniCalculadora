package com.pezloja.minicalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_punto, btn_mas, btn_por, btn_menos, btn_dividir, btn_root, btn_inv, btn_masMenos, btn_igual, btn_ac;
    TextView str_resultado;
    String numero;
    double primerNumero = 0.0;
    double segundoNumero = 0.0;
    String operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_punto = findViewById(R.id.btn_punto);
        btn_mas = findViewById(R.id.btn_mas);
        btn_por = findViewById(R.id.btn_por);
        btn_menos = findViewById(R.id.btn_menos);
        btn_dividir = findViewById(R.id.btn_dividir);
        btn_root = findViewById(R.id.btn_root);
        btn_inv = findViewById(R.id.btn_inv);
        btn_masMenos = findViewById(R.id.btn_masMenos);
        btn_igual = findViewById(R.id.btn_igual);
        btn_ac = findViewById(R.id.btn_ac);

        str_resultado = findViewById(R.id.str_resultado);

        btn_0.setOnClickListener(this::mostrarNumero);
        btn_1.setOnClickListener(this::mostrarNumero);
        btn_2.setOnClickListener(this::mostrarNumero);
        btn_3.setOnClickListener(this::mostrarNumero);
        btn_4.setOnClickListener(this::mostrarNumero);
        btn_5.setOnClickListener(this::mostrarNumero);
        btn_6.setOnClickListener(this::mostrarNumero);
        btn_7.setOnClickListener(this::mostrarNumero);
        btn_8.setOnClickListener(this::mostrarNumero);
        btn_9.setOnClickListener(this::mostrarNumero);
        btn_punto.setOnClickListener(this::mostrarNumero);
        btn_ac.setOnClickListener(view -> limpiar());
        btn_mas.setOnClickListener(view -> operacion("+"));
        btn_menos.setOnClickListener(view -> operacion("-"));
        btn_por.setOnClickListener(view -> operacion("x"));
        btn_dividir.setOnClickListener(view -> operacion("/"));
        btn_root.setOnClickListener(view -> calcularRoot());
        btn_inv.setOnClickListener(view -> calcularInv());
        btn_masMenos.setOnClickListener(view -> negativoPositivo());
        btn_igual.setOnClickListener(view -> calcular());
    }

    private void negativoPositivo() {
        numero = str_resultado.getText().toString();
        double negativo = Double.parseDouble(numero);
        negativo *= -1;
        numero = String.valueOf((Double) negativo);
        str_resultado.setText(numero);
    }

    private void calcular() {
        Double calculo = 0.0;
        segundoNumero = Double.parseDouble(str_resultado.getText().toString());
        if (operacion.equals("+")) {
            calculo = primerNumero + segundoNumero;
        } else if (operacion.equals("-")) {
            calculo = primerNumero - segundoNumero;
        } else if (operacion.equals("x")) {
            calculo = primerNumero * segundoNumero;
        } else if (operacion.equals("/")) {
            calculo = primerNumero / segundoNumero;
        }
        limpiar();
        str_resultado.setText(calculo.toString());
    }

    private void calcularRoot() {
        // Obtiene el número actual mostrado en str_resultado
        segundoNumero = Double.parseDouble(str_resultado.getText().toString());
        // Calcula la raíz cuadrada
        double resultado = Math.sqrt(segundoNumero);
        // Muestra el resultado en el TextView
        str_resultado.setText(String.valueOf(resultado));
    }

    private void calcularInv() {
        segundoNumero = Double.parseDouble(str_resultado.getText().toString());

        double resultado = 1 / segundoNumero;

        str_resultado.setText(String.valueOf(resultado));

        primerNumero = resultado;
        segundoNumero = 0.0;
        operacion = "";
    }

    private void operacion(String operador) {
        primerNumero = Double.parseDouble(str_resultado.getText().toString());
        operacion = operador;
        numero = "";
        str_resultado.setText("0");
    }

    private void mostrarNumero(View view) {
        Button btn = (Button) view;
        String nuevoNumero = btn.getText().toString();

        String textoActual = str_resultado.getText().toString();

        if (textoActual.equals("Infinity") || textoActual.equals("NaN")) {
            limpiar();
            textoActual = "0";
        }
        if (textoActual.equals("0") && !nuevoNumero.equals(".")) {
            // Reemplaza el 0 inicial cuando se presiona un número
            str_resultado.setText(nuevoNumero);
            numero = nuevoNumero;
        } else {
            if (nuevoNumero.equals(".")) {
                if (!textoActual.contains(".")) {
                    str_resultado.setText(textoActual + nuevoNumero);
                    numero += nuevoNumero;
                }
            } else {
                str_resultado.setText(textoActual + nuevoNumero);
                numero += nuevoNumero;
            }
        }
    }

    private void limpiar() {
        str_resultado.setText("0");
    }
}