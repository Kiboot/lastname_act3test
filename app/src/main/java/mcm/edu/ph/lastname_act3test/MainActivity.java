package mcm.edu.ph.lastname_act3test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int heroHP = 615;
    int monsterHP = 1000;
    int heroMinDPT = 45;
    int heroMaxDPT = 60;
    int monsMinDPT = 20;
    int monsMaxDPT = 25;
    int turnNumber= 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);
        Button nextTurn = findViewById(R.id.btnNextTurn);

        TextView txtHeroHP, txtMonsterHP,txtHeroDPT, txtMonsterDPT;
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtMonsterHP = findViewById(R.id.txtMonsterHP);
        txtHeroDPT = findViewById(R.id.txtHeroDPT);
        txtMonsterDPT = findViewById(R.id.txtMonsterDPT);

        txtHeroHP.setText(String.valueOf(this.heroHP));
        txtMonsterHP.setText(String.valueOf(this.monsterHP));
        txtHeroDPT.setText((heroMinDPT)+ " ~ "+ (heroMaxDPT));
        txtMonsterDPT.setText((monsMinDPT)+ " ~ "+ (monsMaxDPT));
        nextTurn.setOnClickListener(this);

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v){

        Random random = new Random();

        int heroDPT = random.nextInt(heroMaxDPT - heroMinDPT) + heroMinDPT;
        int monsDPT = random.nextInt(monsMaxDPT - monsMinDPT) + monsMinDPT;

        TextView txtHeroHP, txtMonsterHP,txtHeroDPT, txtMonsterDPT,txtMsg;
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtMonsterHP = findViewById(R.id.txtMonsterHP);
        txtHeroDPT = findViewById(R.id.txtHeroDPT);
        txtMonsterDPT = findViewById(R.id.txtMonsterDPT);
        txtMsg = findViewById(R.id.txtMsg);

        Button btnNext = findViewById(R.id.btnNextTurn);

        switch(v.getId()) {
            case R.id.btnNextTurn:


                if(turnNumber%2==1){
                    monsterHP = monsterHP - heroDPT;
                    txtMsg.setText("The hero dealt " +heroDPT+ " damage to the enemy");
                    turnNumber++;
                    btnNext.setText("Monster's Turn");

                }
                else{
                    heroHP = heroHP - monsDPT;
                    txtMsg.setText("The monster dealt " +monsDPT+ " damage to the hero");
                    turnNumber++;
                    btnNext.setText("Hero's Turn");

                }
                txtHeroHP.setText(String.valueOf(heroHP));
                txtMonsterHP.setText(String.valueOf(monsterHP));
                txtHeroDPT.setText((heroMinDPT)+ " ~ "+ (heroMaxDPT));
                txtMonsterDPT.setText((monsMinDPT)+ " ~ "+ (monsMaxDPT));

                if (heroHP<=0){
                    txtMsg.setText("The hero was defeated!");
                    txtHeroHP.setText("");
                    txtMonsterHP.setText("");
                    txtHeroDPT.setText("");
                    txtMonsterDPT.setText("");
                    btnNext.setText("Try again next time");
                }
                else if (monsterHP<=0){
                    txtMsg.setText("The hero was victorious!");
                    txtHeroHP.setText("");
                    txtMonsterHP.setText("");
                    txtHeroDPT.setText("");
                    txtMonsterDPT.setText("");
                    btnNext.setText("Congratulations");
                }


                break;



        }
    }
}