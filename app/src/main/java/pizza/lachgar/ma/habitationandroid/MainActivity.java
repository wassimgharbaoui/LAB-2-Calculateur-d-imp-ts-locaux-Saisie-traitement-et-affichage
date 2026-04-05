package pizza.lachgar.ma.habitationandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import pizza.lachgar.ma.habitationandroid.R;

public class MainActivity extends AppCompatActivity {

    private EditText nomEditText, adresseEditText, surfaceEditText, nbPiecesEditText;
    private CheckBox piscineCheckBox;
    private Button calculButton;
    private TextView impotBaseTextView, impotSupplementTextView, impotTotalTextView;

    private static final double TAUX_BASE_PAR_M2 = 2.0;
    private static final double SUPPLEMENT_PAR_PIECE = 15.0;
    private static final double SUPPLEMENT_PISCINE = 300.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // R doit être résolu ici

        // Liaison des vues
        nomEditText = findViewById(R.id.nomEditText);
        adresseEditText = findViewById(R.id.adresseEditText);
        surfaceEditText = findViewById(R.id.surfaceEditText);
        nbPiecesEditText = findViewById(R.id.nbPiecesEditText);
        piscineCheckBox = findViewById(R.id.piscineCheckBox);
        calculButton = findViewById(R.id.calculButton);
        impotBaseTextView = findViewById(R.id.impotBaseTextView);
        impotSupplementTextView = findViewById(R.id.impotSupplementTextView);
        impotTotalTextView = findViewById(R.id.impotTotalTextView);

        calculButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerImpot();
            }
        });
    }

    private void calculerImpot() {
        String surfaceStr = surfaceEditText.getText().toString().trim();
        String nbPiecesStr = nbPiecesEditText.getText().toString().trim();
        boolean aPiscine = piscineCheckBox.isChecked();

        if (surfaceStr.isEmpty() || nbPiecesStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        double surface = Double.parseDouble(surfaceStr);
        int nbPieces = Integer.parseInt(nbPiecesStr);

        double impotBase = surface * TAUX_BASE_PAR_M2;
        double impotSupplement = nbPieces * SUPPLEMENT_PAR_PIECE;
        if (aPiscine) {
            impotSupplement += SUPPLEMENT_PISCINE;
        }
        double impotTotal = impotBase + impotSupplement;

        impotBaseTextView.setText(String.format("Impôt de base : %.1f €", impotBase));
        impotSupplementTextView.setText(String.format("Impôt supplémentaire : %.1f €", impotSupplement));
        impotTotalTextView.setText(String.format("Impôt Total : %.1f €", impotTotal));
    }
}