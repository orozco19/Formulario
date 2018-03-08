package com.orozcoh.santiago.form;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tInfo0,tInfo1, tInfo2, tInfo3, tInfo4, eText;
    EditText eLogin, ePassword, eRpassword, eCorreo;
    RadioButton rMasculino, rFemenino;
    DatePicker dDatepicker;
    Spinner sSpinner;
    CheckBox cNatacion, cCiclismo, cCantar, cBailar;
    Button bAceptar;
    Toast toast;
    Calendar Calendario;
    int day, month, year;
    String login,pass,rpass,correo,sexo, hobbies, lnacimiento, fnacimiento;

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        setContentView(R.layout.activity_main);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ciudades, android.R.layout.simple_spinner_item);

        CharSequence text = "Hello toast!";
        tInfo1 = findViewById(R.id.tInfo1);
        tInfo2 = findViewById(R.id.tInfo2);
        tInfo3 = findViewById(R.id.tInfo3);
        tInfo4 = findViewById(R.id.tInfo4);
        tInfo0 = findViewById(R.id.tInfo0);
        eLogin = findViewById(R.id.eLogin);
        ePassword = findViewById(R.id.ePassword);
        eRpassword = findViewById(R.id.eRpassword);
        eCorreo = findViewById(R.id.eCorreo);
        rMasculino = findViewById(R.id.rMasculino);
        rFemenino = findViewById(R.id.rFemenino);
//        dDatepicker = findViewById(R.id.dDatepicker);
        sSpinner = findViewById(R.id.sSpinner);
        cNatacion = findViewById(R.id.cNatacion);
        cCiclismo = findViewById(R.id.cCiclismo);
        cCantar = findViewById(R.id.cCantar);
        cBailar = findViewById(R.id.cBailar);
        bAceptar = findViewById(R.id.bAceptar);
        eText = findViewById(R.id.eText);
        sSpinner.setAdapter(adapter);
        Calendario = Calendar.getInstance();
        day = Calendario.get(Calendar.DAY_OF_MONTH);
        month = Calendario.get(Calendar.MONTH);
        year = Calendario.get(Calendar.YEAR);
        month = month +1;
        eText.setText(day+"/"+month+"/"+year);
        fnacimiento = "";
    }

    public void onButtonClicked(View view) {
        login = eLogin.getText().toString();
        pass = ePassword.getText().toString();
        rpass = eRpassword.getText().toString();
        correo = eCorreo.getText().toString();
        hobbies = "";

        if (!(isEmailValid(eCorreo.getText()))) {
            eCorreo.setError("Ingrese nuevamente");
            Toast.makeText(this, "E-mail ingresado no es valido", Toast.LENGTH_SHORT).show();
        }

        if (rMasculino.isChecked()){
            sexo ="Masculino";
        }
        else if (rFemenino.isChecked())
            sexo = "Femenino";
        else
            sexo = "";

        if (TextUtils.isEmpty(sexo))
            Toast.makeText(this, "Seleccione su sexo", Toast.LENGTH_SHORT).show();
        if (cCiclismo.isChecked())
            hobbies += "Ciclismo, ";
        if (cCantar.isChecked())
            hobbies += "Cantar, ";
        if (cNatacion.isChecked())
            hobbies += "Natacion, ";
        if (cBailar.isChecked())
            hobbies += "Bailar, ";

        if (TextUtils.isEmpty(hobbies))
            Toast.makeText(this, "Seleccione almenos un hobby", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(fnacimiento))
            Toast.makeText(this, "Ingrese una fecha de nacimiento", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(lnacimiento))
            Toast.makeText(this, "Ingrese un lugar de nacimiento", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(eLogin.getText().toString()))
            eLogin.setError("Campo Vacio!");
        if (TextUtils.isEmpty(ePassword.getText().toString()))
            ePassword.setError("Campo Vacio!");
        if (TextUtils.isEmpty(eRpassword.getText().toString()))
            eRpassword.setError("Campo Vacio!");
        if (TextUtils.isEmpty(eCorreo.getText().toString()))
            eCorreo.setError("Campo Vacio!");

        if (!(ePassword.getText().toString().equals(eRpassword.getText().toString()))) {
            ePassword.setError("Vuelva a ingresar la contraseña");
            Toast.makeText(this, "Ingrese nuevamente la contraseña", Toast.LENGTH_SHORT).show();
            ePassword.setText("");
            eRpassword.setText("");
        }



        lnacimiento = sSpinner.getSelectedItem().toString();
        tInfo0.setText("Login: " + login + "\t\t\tPassword: "+ pass + "\t\t\tSexo: " + sexo);
        tInfo1.setText(correo);
        tInfo2.setText(hobbies);
        tInfo3.setText(fnacimiento);
        tInfo4.setText(lnacimiento);

    }

    public void OnCalendarClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMont) {
                monthOfYear = monthOfYear +1 ;
                eText.setText(dayOfMont+"/"+monthOfYear+"/"+year);
                fnacimiento = dayOfMont+"/"+monthOfYear+"/"+year;
            }
        }, year, month-1, day);
        datePickerDialog.show();
    }
}
