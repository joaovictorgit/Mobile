package dadm.quixada.ufc.projeto_itech.Agendamento;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dadm.quixada.ufc.projeto_itech.R;

public class CadAgendamento extends AppCompatActivity {

    private Spinner spinner_tipoDispositivo;
    private Button btn_agendar;
    private EditText edtText_modeloDispositivo, edtText_problemaDispositivo;
    private TextView textView_dia, textView_horario;
    private MaterialDatePicker datePicker;
    private MaterialTimePicker timePicker;
    private RadioGroup radio_group;
    private RadioButton radioButton;

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_agendamento);
        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Spinner
        spinner_tipoDispositivo = findViewById(R.id.spinner_tipoDispositivo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_dispositivo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tipoDispositivo.setAdapter(adapter);

        //EditText
        edtText_modeloDispositivo = findViewById(R.id.edtText_modeloDispositivo);
        edtText_problemaDispositivo = findViewById(R.id.edtText_problemaDispositivo);
        textView_dia = findViewById(R.id.textView_dia);
        textView_horario = findViewById(R.id.textView_horario);

        textView_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = MaterialDatePicker.Builder.datePicker().build();
                datePicker.show(getSupportFragmentManager(), "DatePicker");

                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        textView_dia.setText(datePicker.getHeaderText());
                    }
                });

                datePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("DATEPICKER", datePicker.getHeaderText());
                    }
                });

                datePicker.addOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.d("DATEPICKER", "Date Picker cancelled");
                    }
                });
            }
        });

        textView_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build();
                timePicker.show(getSupportFragmentManager(), "TimePicker");

                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer newHour = timePicker.getHour();
                        Integer newMinute = timePicker.getMinute();
                        String horario = newHour.toString()+":"+newMinute+":"+"00";
                        textView_horario.setText(horario);
                    }
                });
            }
        });

        //Button
        btn_agendar = findViewById(R.id.btn_agendar);
        btn_agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Radio Group
                radio_group = findViewById(R.id.radio_group);
                int selectId = radio_group.getCheckedRadioButtonId();
                radioButton = findViewById(selectId);

                cadastrarAgendamento();
            }
        });



    }

    public void cadastrarAgendamento(){

        String id = UUID.randomUUID().toString();
        String idUsuario = fAuth.getCurrentUser().getUid();
        String tipoDispositivo = spinner_tipoDispositivo.getSelectedItem().toString();
        String modeloDispositivo = edtText_modeloDispositivo.getText().toString();
        String problema = edtText_problemaDispositivo.getText().toString();
        String opcaoEntrega = radioButton.getText().toString();
        String dia = textView_dia.getText().toString();
        String horario = textView_horario.getText().toString();
        String status = "Pendente";

        AgendamentoModel agendamento = new AgendamentoModel(id, idUsuario, tipoDispositivo, modeloDispositivo,
                problema, opcaoEntrega, dia, horario, status);

        documentReference = fStore.collection("Agendamentos").document(id);

        documentReference.set(agendamento).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Agendado com sucesso!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Não foi possível agendar :(", Toast.LENGTH_LONG).show();
            }
        });

        edtText_modeloDispositivo.setText("");
        edtText_problemaDispositivo.setText("");
        textView_dia.setText("Dia");
        textView_horario.setText("Horário");


    }
}