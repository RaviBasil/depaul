package com.example.ravi.depaul.curri;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ravi.depaul.R;

public class CurrActivity extends AppCompatActivity {
String aug16="\n\nAugust-2016\n\n"+
        "1st to 12th\t \t\t Quaterly examination\t\n"+
        "15th\t(Monday) \t Independence Day celeberation\t\n"+
        "18th\t(Thursday) \t Rakshanamdhan (Holiday)\n" +
        "25th\t(Thursday) \t Janmashtami (Holiday)\n" +
        "26th\t(Friday) \t\t Answer Paper to Parents\t";

    String sep16="\n\nSeptember-2016\n\n"+
            "05th\t(Monday) \t Teacher's Day celebration\t\n"+
            "12th\t(Monday) \t Id-ul-Zuha Bakrid (Holiday)\n"+
            "17th\t(Saturday) \t Vishwakarma Pooja (Holiday)\n";

    String oct16="\n" +
            "\nOctober-2016\n\n"+
            "1st\t(Saturaday) \t\t Maharaja Agarsen jayanti (Holiday)\n"+
            "02nd\t(Sunday) \t Gandhi Jayanti Celebration\t\n"+
            "10th to 12th \t\t\t Dushera-Moharam (Holidays)\n"+
            "22nd\t(Saturaday) \t Parent-Teachers Meet\t\n"+
            "26th to 28th \t\t\t Minor subject exam in class time\t\n"+
            "30th & 31th \t\t\t Diwali Holidays\t";
    String nov16="\n\nNovember-2016\n\n"+
            "01st\t(Tuesday) \t Bhaidooj (Holiday)\n"+
            "03rd to 16th \t\t\t Half yearly Examination\t\n"+
            "14th\t(Monday) \t Guru Nanak Jayanti (Holiday)\n"+
            "15th\t(Tuesday) \t Kakoda Mela (Holiday)\n"+
            "24th\t(Thursday) \t Guru Teg Bahdhur Martydom (Holiday)\n"+
            "26th\t(Saturday) \t Answer paper to Parents\t\n";

    String dec16="\n\nDecember-2016\n\n"+
            "23rd\t(Friday) \t\t Charan Singh Jayanti (Holiday)\t\n"+
            "24th to 26th \t\t\t Christmas Holidays\t\n"+
            "27th to 31st \t\t\t ICSE Practical Exam for Class X\t\n";

    String jan17="\n\nJanuary-2017\n\n"+
            "01st\t(Sunday) \t New Years Day (Holiday)\n"+
            "05th\t(Thursday) \t Guru Gobind Singh Jayanti\n"+
            "26th\t(Thursday) \t Republic Day Celebration\t\n";
    String feb17="\n\nFebruary-2017\n\n"+
            "10th\t(Friday) \t\t Guru Ravidas Birthday\n"+
            "24th\t(Friday) \t\t Maha Shivratri\t\t\n";
    String mar17="\n\nMarch-2017\n\n"+
            "13th\t(Monday) \t\t Holi (Holiday)\n\n\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyRandomTheme1);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_curr);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        //getSupportActionbar
        if(Build.VERSION.SDK_INT>=21) {
            toolbar.setNavigationIcon(getResources().getDrawable(
                    R.drawable.ic_arrow_back_black_24dp, getTheme()));
        }else
        {
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        }
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
        TextView aug=(TextView) findViewById(R.id.aug);
        TextView sep=(TextView) findViewById(R.id.sep);
        TextView oct=(TextView) findViewById(R.id.oct);
        TextView nov=(TextView) findViewById(R.id.nov);
        TextView dec=(TextView) findViewById(R.id.dec);
        TextView jan=(TextView) findViewById(R.id.jan);
        TextView feb=(TextView) findViewById(R.id.feb);
        TextView mar=(TextView) findViewById(R.id.mar);
        aug.setText(aug16);
        sep.setText(sep16);
        oct.setText(oct16);
        nov.setText(nov16);
        dec.setText(dec16);
        jan.setText(jan17);
        feb.setText(feb17);
        mar.setText(mar17);
    }
}
