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
    int monsterHP = 8000;
    int heroMinDPT = 102;
    int heroMaxDPT = 104;
    int monsMinDPT = 75;
    int monsMaxDPT = 80;
    int turnNumber= 1;
    int passiveSkill=1;
    int activeSkillBaseDamage = 100;
    int activeSkillCooldown = 0;
    int damageOverTime = 140;
    int damageOverTimeTurns=0;
    boolean damageOverTimeLogic = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);
        Button nextTurn = findViewById(R.id.btnAtk);
        Button stun = findViewById(R.id.btnActiveStun);
        Button passive = findViewById(R.id.btnPassive);

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
        stun.setOnClickListener(this);
        passive.setEnabled(false);
    }


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"}) //suppresses warnings. you can remove or ignore this line
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

        Button btnNext = findViewById(R.id.btnAtk);
        Button stun = findViewById(R.id.btnActiveStun);
        Button passive = findViewById(R.id.btnPassive);
        passive.setEnabled(false);

        switch(v.getId()) {
            case R.id.btnAtk:


                if(turnNumber%2==1 && (damageOverTimeTurns > 0 || damageOverTimeTurns == 0)){ //turn counter condition to simulate alternating hero-enemy attack
                    monsterHP = monsterHP - heroDPT;
                    txtMsg.setText("The hero dealt " +heroDPT+ " damage to the enemy");
                    turnNumber++;
                    btnNext.setText("Monster's Turn");
                    stun.setEnabled(false);

                }
                else if(turnNumber%2!=1 && damageOverTimeTurns > 0){
                    monsterHP = monsterHP - damageOverTime;
                    damageOverTimeTurns--;
                    txtMsg.setText("The monster is still stunned. The monster takes " +damageOverTime+ " damage from WraithFire Blast for "
                            +damageOverTimeTurns+" more turns");
                    turnNumber++;
                    btnNext.setText("Attack");
                    stun.setEnabled(true);
                    if(activeSkillCooldown !=0){
                        activeSkillCooldown--;
                        stun.setEnabled(false);
                    }
                }
                else if(turnNumber%2!=1 && damageOverTimeTurns == 0){
                    heroHP = heroHP - monsDPT;
                    txtMsg.setText("The monster dealt " +heroDPT+ " damage to the hero");
                    turnNumber++;
                    btnNext.setText("Attack");
                    stun.setEnabled(true);
                    if(activeSkillCooldown !=0){
                        activeSkillCooldown--;
                        stun.setEnabled(false);
                    }
                }
                else{}


                txtHeroHP.setText(String.valueOf(heroHP));
                txtMonsterHP.setText(String.valueOf(monsterHP));
                txtHeroDPT.setText((heroMinDPT)+ " ~ "+ (heroMaxDPT));
                txtMonsterDPT.setText((monsMinDPT)+ " ~ "+ (monsMaxDPT));

                if (heroHP<=0){ //condition that disables text and buttons when the hero loses or wins
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

            case R.id.btnActiveStun:

                monsterHP = monsterHP - activeSkillBaseDamage;
                monsterHP = monsterHP - damageOverTime;
                damageOverTimeTurns = 2;
                txtMsg.setText("The hero casted WraithFire Blast. The hero dealt " +activeSkillBaseDamage+ "initial damage to the enemy. \n"+
                        "The enemy receives " +damageOverTime+ " damage and is stunned for "+damageOverTimeTurns+" turns.");
                turnNumber++;
                btnNext.setText("Monster's Turn");
                activeSkillCooldown=8;
                stun.setEnabled(true);
                if(activeSkillCooldown !=0){
                    activeSkillCooldown--;
                    stun.setEnabled(false);
                }
                break;

        }
    }
}