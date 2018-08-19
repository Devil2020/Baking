package comc.example.mohammedmorse.baking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import comc.example.mohammedmorse.baking.Adapters.CustomPagerAdapter;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener ,View.OnClickListener {
ViewPager viewPager;
CustomPagerAdapter adapter;
LinearLayout frameLayout;
TextView[] textView;
Button Skip , Back;
SharedPreferences SharedPreferences;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    viewPager=findViewById(R.id.myViewPage);
    adapter=new CustomPagerAdapter(this,viewPager);
    frameLayout=findViewById(R.id.myLinearLayoutDots);
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(this);
    Skip=findViewById(R.id.Skip);
    Back=findViewById(R.id.Back);
    Skip.setOnClickListener(this);
    Back.setOnClickListener(this);
    SharedPreferences= getSharedPreferences("Preference",MODE_PRIVATE);
   boolean b= SharedPreferences.getBoolean("FirstTime",true);
   if(b==false){
       Intent intent=new Intent(this,MenuActivity.class);
       startActivity(intent);
   }
   ChangeUi();
    addDots(0);
    }
   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   public void ChangeUi(){    int position = viewPager.getCurrentItem();
       if (position == 0) {
           Skip.setVisibility(View.VISIBLE);
           Back.setVisibility(View.INVISIBLE);
           Skip.setBackground(getResources().getDrawable(R.drawable.myskipbutton));
           getWindow().setStatusBarColor(getResources().getColor(R.color.SliderBackground1Title));
           //Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
       } else {
           getWindow().setStatusBarColor(getResources().getColor(R.color.Statebar));
           Skip.setVisibility(View.VISIBLE);
           Back.setVisibility(View.VISIBLE);
           Skip.setBackground(getResources().getDrawable(R.drawable.mysecondbuttonstyle));
           Back.setBackground(getResources().getDrawable(R.drawable.mysecondbuttonstyle));
           //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
       }}
    private void addDots(int position) {
        textView=new TextView [2];
        frameLayout.removeAllViews();

            for (int i = 0; i < textView.length; i++) {
                textView[i] = new TextView(this);
                textView[i].setText(Html.fromHtml("&#8226"));
                textView[i].setTextColor(getResources().getColor(R.color.TransplantWhite));
                textView[i].setTextSize(35);
                frameLayout.addView(textView[i]);
            }
        if(position>=0){

            textView[position].setTextColor(getResources().getColor(R.color.White));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Toast.makeText(this, "Scroll", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPageSelected(int position) {
       ChangeUi();
       if(position==2){
           android.content.SharedPreferences.Editor editor=SharedPreferences.edit();
           editor.putBoolean("FirstTime",false);
           editor.apply();
           Intent intent=new Intent(this , MenuActivity.class);
           startActivity(intent);
       }
        //Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
addDots(position);
}

    @Override
    public void onPageScrollStateChanged(int state) {
        // Toast.makeText(this, "Scroll Change", Toast.LENGTH_SHORT).show();

}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int position = viewPager.getCurrentItem();
        if(v.getId()==R.id.Skip) {
            if (position == 0) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                // Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
            } else {
                android.content.SharedPreferences.Editor editor=SharedPreferences.edit();
                editor.putBoolean("FirstTime",false);
                editor.apply();
                Intent intent=new Intent(this , MenuActivity.class);
                startActivity(intent);
                // Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.Back){
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            ChangeUi();
        }
    }
}
