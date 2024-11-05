package com.example.xo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    boolean isXmove = true;
    View.OnClickListener onClickListener;

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

        initializeGame();
    }

    private void initializeGame() {
        cell1 = findViewById(R.id.imageButton);
        cell2 = findViewById(R.id.imageButton2);
        cell3 = findViewById(R.id.imageButton3);
        cell4 = findViewById(R.id.imageButton4);
        cell5 = findViewById(R.id.imageButton5);
        cell6 = findViewById(R.id.imageButton6);
        cell7 = findViewById(R.id.imageButton7);
        cell8 = findViewById(R.id.imageButton8);
        cell9 = findViewById(R.id.imageButton9);

        onClickListener = v -> {
            ImageButton cell = (ImageButton) v;

            if (isXmove) {
                cell.setImageResource(R.drawable.noto);
            } else {
                cell.setImageResource(R.drawable.o);
            }

            cell.setEnabled(false);
            if (checkForWinner()) {
                Toast.makeText(this, (isXmove ? "X" : "O") + " победили!", Toast.LENGTH_SHORT).show();
                resetGame();
            } else if (areAllCellsFilled()) {
                Toast.makeText(this, "Нечья!", Toast.LENGTH_SHORT).show();
                resetGame();
            } else {
                isXmove = !isXmove;
            }
        };

        setCellClickListeners();
    }

    private void setCellClickListeners() {
        cell1.setOnClickListener(onClickListener);
        cell2.setOnClickListener(onClickListener);
        cell3.setOnClickListener(onClickListener);
        cell4.setOnClickListener(onClickListener);
        cell5.setOnClickListener(onClickListener);
        cell6.setOnClickListener(onClickListener);
        cell7.setOnClickListener(onClickListener);
        cell8.setOnClickListener(onClickListener);
        cell9.setOnClickListener(onClickListener);
    }

    private boolean checkForWinner() {
        return (checkCells(cell1, cell2, cell3) ||
                checkCells(cell4, cell5, cell6) ||
                checkCells(cell7, cell8, cell9) ||
                checkCells(cell1, cell4, cell7) ||
                checkCells(cell2, cell5, cell8) ||
                checkCells(cell3, cell6, cell9) ||
                checkCells(cell1, cell5, cell9) ||
                checkCells(cell3, cell5, cell7));
    }

    private boolean checkCells(ImageButton cellA, ImageButton cellB, ImageButton cellC) {
        return (cellA.getDrawable() != null &&
                cellB.getDrawable() != null &&
                cellC.getDrawable() != null &&
                cellA.getDrawable().getConstantState() == cellB.getDrawable().getConstantState() &&
                cellB.getDrawable().getConstantState() == cellC.getDrawable().getConstantState());
    }

    private boolean areAllCellsFilled() {
        return cell1.getDrawable() != null && cell2.getDrawable() != null &&
                cell3.getDrawable() != null && cell4.getDrawable() != null &&
                cell5.getDrawable() != null && cell6.getDrawable() != null &&
                cell7.getDrawable() != null && cell8.getDrawable() != null &&
                cell9.getDrawable() != null;
    }

    private void resetGame() {
        cell1.setImageResource(0);
        cell2.setImageResource(0);
        cell3.setImageResource(0);
        cell4.setImageResource(0);
        cell5.setImageResource(0);
        cell6.setImageResource(0);
        cell7.setImageResource(0);
        cell8.setImageResource(0);
        cell9.setImageResource(0);

        cell1.setEnabled(true);
        cell2.setEnabled(true);
        cell3.setEnabled(true);
        cell4.setEnabled(true);
        cell5.setEnabled(true);
        cell6.setEnabled(true);
        cell7.setEnabled(true);
        cell8.setEnabled(true);
        cell9.setEnabled(true);

        isXmove = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
