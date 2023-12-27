package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements SingleChoiceDailog.SingleChoiceListener{

    String temp = "";
    Boolean flag = true;
    Boolean op = false;

    boolean isNightMode;
    SwitchMaterial switchBtn;
    ImageButton popup_btn;
    MaterialButton buttonac,buttonminus,buttondot;
    MaterialButton button_divide,button_multiply,button_add,button_minus,button_equals,button_mod;
    MaterialButton button9,button8,button7,button6,button5,button4,button3,button2,button1,button0;
    TextView solution_tv,result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//----------------------------------------------------------------------------------------------------------------------------------------------------------
        solution_tv = (TextView) findViewById(R.id.solution_tv);
        result_tv = (TextView) findViewById(R.id.tv_result);

        buttonac = (MaterialButton) findViewById(R.id.btn_ac);
        buttonminus = (MaterialButton) findViewById(R.id.btn_minus);
        button_mod = (MaterialButton) findViewById(R.id.button_mod);
        buttondot = (MaterialButton) findViewById(R.id.button_dot);
        button9 = (MaterialButton) findViewById(R.id.button_9);
        button8 = (MaterialButton) findViewById(R.id.button_8);
        button7 = (MaterialButton) findViewById(R.id.button_7);
        button6 = (MaterialButton) findViewById(R.id.button_6);
        button5 = (MaterialButton) findViewById(R.id.button_5);
        button4 = (MaterialButton) findViewById(R.id.button_4);
        button3 = (MaterialButton) findViewById(R.id.button_3);
        button2 = (MaterialButton) findViewById(R.id.button_2);
        button1 = (MaterialButton) findViewById(R.id.button_1);
        button0 = (MaterialButton) findViewById(R.id.button_0);
        button_divide = (MaterialButton) findViewById(R.id.button_divide);
        button_add = (MaterialButton) findViewById(R.id.button_plus);
        button_multiply = (MaterialButton) findViewById(R.id.button_multiply);
        button_minus = (MaterialButton) findViewById(R.id.button_minus);
        button_equals = (MaterialButton) findViewById(R.id.button_equals);
//        popup_btn = findViewById(R.id.imageButton1);
        switchBtn = findViewById(R.id.switch1);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're using the light theme
                switchBtn.setChecked(false);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're using dark theme
                switchBtn.setChecked(true);
                break;
        }

//        switchBtn.setChecked(true);
//        Toast.makeText(MainActivity.this, "Dark Mode", Toast.LENGTH_SHORT).show();

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Toast.makeText(MainActivity.this, "Dark Mode", Toast.LENGTH_SHORT).show();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(MainActivity.this, "Light Mode", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        popup_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),popup_btn);
//                popupMenu.getMenuInflater().inflate(R.menu.menu_item, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
////                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                        if(menuItem.getTitle().equals("Choose theme")) {
//                            DialogFragment dialog = new SingleChoiceDailog();
//                            dialog.setCancelable(false);
//                            dialog.show(getSupportFragmentManager(),"Single Choice Dialog");
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        });

        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(solution_tv.getText().toString().equals("")) {
                    setTextinTextbox("0.");
                }
                else if(flag) {
                    if(solution_tv.getText().toString().endsWith("+") || solution_tv.getText().toString().endsWith("–") || solution_tv.getText().toString().endsWith("÷")
                        || solution_tv.getText().toString().endsWith("%") || solution_tv.getText().toString().endsWith("×")) {
                        setTextinTextbox("0" + buttondot.getText().toString());
                    }
                    else {
                        setTextinTextbox(buttondot.getText().toString());
                        flag = false;
                    }
                }

//                else if(solution_tv.getText().toString().endsWith("+") || solution_tv.getText().toString().endsWith("–") || solution_tv.getText().toString().endsWith("÷")
//                        || solution_tv.getText().toString().endsWith("%") || solution_tv.getText().toString().endsWith("×")) {
//                    setTextinTextbox("0.");
//                }
            }
        });

        buttonac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                temp = "";
                solution_tv.setText("");
                result_tv.setText("");
            }
        });

        button_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!solution_tv.getText().toString().equals("")) {
                    setTextinTextbox(button_mod.getText().toString());
                    flag = true;
                    op = true;
                }
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!solution_tv.getText().toString().equals("")) {
                    setTextinTextbox(button_divide.getText().toString());
                    flag = true;
                    op = true;
                }
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!solution_tv.getText().toString().equals("")) {
                    setTextinTextbox(button_add.getText().toString());
                    flag = true;
                    op = true;
                }
            }
        });

        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!solution_tv.getText().toString().equals("")) {
                    setTextinTextbox(button_multiply.getText().toString());
                    flag = true;
                    op = true;
                }
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!solution_tv.getText().toString().equals("")) {
                    setTextinTextbox(button_minus.getText().toString());
                    flag = true;
                    op = true;
                }
            }
        });

        button_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solution_tv.setText(result_tv.getText().toString());
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button9.getText().toString());
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button8.getText().toString());
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button7.getText().toString());
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button6.getText().toString());
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button5.getText().toString());
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button4.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button3.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button2.getText().toString());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button1.getText().toString());
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextinTextbox(button0.getText().toString());
            }
        });

//----------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    @SuppressLint("SetTextI18n")
    public void setTextinTextbox(String value) {
        temp = temp + value;
        Boolean Valid = validation(temp);

        if(Valid) {
            solution_tv.setText(temp);
            String result = Calculate(temp);

            if(!result.equals("Error") && !result.equals("Expression")) {
                result_tv.setText(result);
            }
        }
    }

    public String Calculate(String value) {

        if(value.contains("×")) {
            value = value.replaceAll("×", "*");
        }
        if(value.contains("–")) {
            value = value.replaceAll("–", "-");
        }
        if(value.contains("÷")) {
            value = value.replaceAll("÷", "/");
        }

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result =  context.evaluateString(scriptable,value,"Javascript",1,null).toString();
            if (result.endsWith(".0")) {
                result = result.replace(".0","");
            }
            return result;
        }
        catch (Exception e) {
            return "Error";
        }
    }

    public boolean validation(String value) {

        if(value.contains("×")) {
            value = value.replaceAll("×", "*");
        }
        if(value.contains("–")) {
            value = value.replaceAll("–", "-");
        }
        if(value.contains("÷")) {
            value = value.replaceAll("÷", "/");
        }

        if(value.contains("++") || value.contains("--") || value.contains("**") || value.contains("//") || value.contains("%%") || value.contains("+-")
                || value.contains("-+") || value.contains("+*") || value.contains("*+") || value.contains("+/") || value.contains("/+") || value.contains("+%") || value.contains("%+")
                || value.contains("-*") || value.contains("*-") || value.contains("-/") || value.contains("/-") || value.contains("-%") || value.contains("%-")
                || value.contains("*/") || value.contains("/*") || value.contains("*%") || value.contains("%*") || value.contains("%/")
                || value.contains("/%") || value.contains("%/"))
        {
            temp = temp.substring(0,temp.length()-1);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        if(list[position].equals("Light")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            restartActivity();
        }
        else if(list[position].equals("Dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            restartActivity();
        }
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    public void restartActivity(){
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }
}