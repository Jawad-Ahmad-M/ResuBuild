package com.resumebuilder.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DatePickerBottomSheet {

    private final Context context;
    private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private boolean yearTextViewsPresent = false;
    private boolean monthTextViewsPresent = false;
    private int currentYearStart;
    private final int yearsCount = 12;

    private BottomSheetDialog bottomSheetDialog;
    private GridLayout gridMonths, gridYears;
    private TextView tvYearRange;
    private Button btnPrevYears, btnNextYears, btnConfirm;
    private EditText currentEditText;

    // Map to store selections for each EditText
    private final Map<Integer, Integer> selectedMonths = new HashMap<>();
    private final Map<Integer, Integer> selectedYears = new HashMap<>();

    public DatePickerBottomSheet(Context context) {
        this.context = context;
    }

    public void showForEditText(EditText editText) {
        this.currentEditText = editText;
        bottomSheetDialog = new BottomSheetDialog(context);
        @SuppressLint("InflateParams") View view = View.inflate(context, R.layout.bottom_sheet_date_picker, null);
        bottomSheetDialog.setContentView(view);

        gridMonths = view.findViewById(R.id.grid_months);
        gridYears = view.findViewById(R.id.grid_years);
        tvYearRange = view.findViewById(R.id.tv_year_range);
        btnPrevYears = view.findViewById(R.id.btn_prev_years);
        btnNextYears = view.findViewById(R.id.btn_next_years);
        btnConfirm = view.findViewById(R.id.btn_confirm);

        // Null check for views
        if (gridMonths == null || gridYears == null || tvYearRange == null ||
                btnPrevYears == null || btnNextYears == null || btnConfirm == null) {
            Toast.makeText(context, "Error: Could not initialize dialog views", Toast.LENGTH_LONG).show();
            return;
        }

        // Set currentYearStart based on selected year for this EditText, or default
        int thisYear = getCurrentYear();
        Integer selYear = selectedYears.get(currentEditText.getId());
        if (selYear != null && selYear != -1) {
            currentYearStart = selYear - (selYear % yearsCount);
        } else {
            currentYearStart = thisYear - (thisYear % yearsCount);
        }

        // Reset grid flags for new dialog instance
        monthTextViewsPresent = false;
        yearTextViewsPresent = false;

        populateMonthsGrid();
        populateYearsGrid();

        btnPrevYears.setOnClickListener(v -> {
            currentYearStart -= yearsCount;
            populateYearsGrid();
        });

        btnNextYears.setOnClickListener(v -> {
            currentYearStart += yearsCount;
            populateYearsGrid();
        });

        btnConfirm.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            updateEditText();
        });

        bottomSheetDialog.setOnDismissListener(dialog -> {
            gridMonths = null;
            gridYears = null;
            tvYearRange = null;
            btnPrevYears = null;
            btnNextYears = null;
            btnConfirm = null;
            currentEditText = null;
        });

        bottomSheetDialog.show();
    }

    private void updateEditText() {
        if (currentEditText == null) return;
        Integer selectedMonth = selectedMonths.get(currentEditText.getId());
        Integer selectedYear = selectedYears.get(currentEditText.getId());
        if (selectedMonth != null && selectedYear != null && selectedMonth != -1 && selectedYear != -1) {
            String result = months[selectedMonth] + " " + selectedYear;
            currentEditText.setText(result);
        }
    }

    private void populateMonthsGrid() {
        if (!monthTextViewsPresent) {
            gridMonths.removeAllViews();
            for (int i = 0; i < months.length; i++) {
                CardView card = createCard(months[i]);
                final int index = i;
                card.setOnClickListener(v -> {
                    if (currentEditText != null) {
                        selectedMonths.put(currentEditText.getId(), index);
                        updateMonthSelection();
                    }
                });
                gridMonths.addView(card);
            }
            monthTextViewsPresent = true;
        }
        updateMonthSelection();
    }

    private void updateMonthSelection() {
        if (currentEditText == null) return;
        Integer selectedMonth = selectedMonths.get(currentEditText.getId());
        int monthValue = selectedMonth != null ? selectedMonth : -1;
        for (int i = 0; i < gridMonths.getChildCount(); i++) {
            CardView card = (CardView) gridMonths.getChildAt(i);
            TextView tv = (TextView) card.getChildAt(0);
            if (i == monthValue) {
                card.setCardBackgroundColor(Color.parseColor("#2196F3"));
                tv.setTextColor(Color.WHITE);
            } else {
                card.setCardBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.BLACK);
            }
        }
    }

    private void populateYearsGrid() {
        String yearRange = currentYearStart + " - " + (currentYearStart + yearsCount - 1);
        tvYearRange.setText(yearRange);

        if (!yearTextViewsPresent) {
            gridYears.removeAllViews();
            for (int i = 0; i < yearsCount; i++) {
                final int year = currentYearStart + i;
                CardView card = createCard(String.valueOf(year));
                card.setOnClickListener(v -> {
                    if (currentEditText != null) {
                        selectedYears.put(currentEditText.getId(), year);
                        updateYearSelection();
                    }
                });
                gridYears.addView(card);
            }
            yearTextViewsPresent = true;
        } else {
            for (int i = 0; i < gridYears.getChildCount(); i++) {
                int year = currentYearStart + i;
                CardView card = (CardView) gridYears.getChildAt(i);
                TextView tv = (TextView) card.getChildAt(0);
                tv.setText(String.valueOf(year));
                card.setOnClickListener(v -> {
                    if (currentEditText != null) {
                        selectedYears.put(currentEditText.getId(), year);
                        updateYearSelection();
                    }
                });
            }
        }
        updateYearSelection();
    }

    private void updateYearSelection() {
        if (currentEditText == null) return;
        Integer selectedYear = selectedYears.get(currentEditText.getId());
        int yearValue = selectedYear != null ? selectedYear : -1;
        for (int i = 0; i < gridYears.getChildCount(); i++) {
            CardView card = (CardView) gridYears.getChildAt(i);
            TextView tv = (TextView) card.getChildAt(0);
            int year = Integer.parseInt(tv.getText().toString());
            if (year == yearValue) {
                card.setCardBackgroundColor(Color.parseColor("#2196F3"));
                tv.setTextColor(Color.WHITE);
            } else {
                card.setCardBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.BLACK);
            }
        }
    }

    private CardView createCard(String text) {
        CardView card = new CardView(context);
        card.setRadius(16f);
        card.setCardElevation(4f);
        card.setUseCompatPadding(true);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(8, 8, 8, 8);
        card.setLayoutParams(params);

        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16f);
        tv.setPadding(24, 24, 24, 24);
        tv.setContentDescription(text);
        card.addView(tv);
        card.setContentDescription("Select " + text);

        return card;
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public void saveState(Bundle outState) {
        // Save selections for each EditText
        outState.putSerializable("selectedMonths", (HashMap<Integer, Integer>) selectedMonths);
        outState.putSerializable("selectedYears", (HashMap<Integer, Integer>) selectedYears);
        outState.putInt("currentYearStart", currentYearStart);
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            outState.putBoolean("dialogShowing", true);
            if (currentEditText != null) {
                outState.putInt("currentEditTextId", currentEditText.getId());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void restoreState(Bundle savedInstanceState) {
        // Restore selections
        selectedMonths.clear();
        selectedYears.clear();
        Map<Integer, Integer> savedMonths = (Map<Integer, Integer>) savedInstanceState.getSerializable("selectedMonths");
        Map<Integer, Integer> savedYears = (Map<Integer, Integer>) savedInstanceState.getSerializable("selectedYears");
        if (savedMonths != null) {
            selectedMonths.putAll(savedMonths);
        }
        if (savedYears != null) {
            selectedYears.putAll(savedYears);
        }
        currentYearStart = savedInstanceState.getInt("currentYearStart", getCurrentYear() - (getCurrentYear() % yearsCount));
        if (savedInstanceState.getBoolean("dialogShowing", false)) {
            int editTextId = savedInstanceState.getInt("currentEditTextId", -1);
            if (editTextId != -1) {
                EditText editText = ((AppCompatActivity) context).findViewById(editTextId);
                if (editText != null) {
                    showForEditText(editText);
                }
            }
        }
    }
}