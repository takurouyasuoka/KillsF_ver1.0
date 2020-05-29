package com.takurou.android.killsf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_battle.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.jar.Attributes
import kotlin.concurrent.schedule

class BattleActivity : AppCompatActivity() {

    lateinit var timer :Timer
    var NameTitle :String? = ""
    var attackPower = 0
    var HP = 0
    var actionMSG = ""
    var randomNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        val bundle :Bundle? = intent.extras
        NameTitle = bundle?.getString("NameTitle")

        // 役割の設定
        when(roleNumber){
            1 -> {
                textViewRole.setText(getString(R.string.role1))
            }
            2 -> {
                textViewRole.setText(getString(R.string.role2))
            }
            3 -> {
                textViewRole.setText(getString(R.string.role3))
            }
            4 -> {
                textViewRole.setText(getString(R.string.role4))
            }
            5 -> {
                textViewRole.setText(getString(R.string.role5))
            }
        }
        roleOwnSetting()

        // あいてカードの設定
        EnemyHP.setText(enemyHP.toString())

        // 攻撃ボタン押下時
        buttonBattleAtack.setOnClickListener {
            buttonBattleAtack.isEnabled = false
            //  自分の攻撃
            //  攻撃メッセージの編集
            actionMSG = NameTitle + getString(R.string.actionMSG)
            textViewBattleMsg.setText(actionMSG)
            //  少し時間空けて攻撃結果を反映
            randomNumber = Random().nextInt(10)
            if (randomNumber < 3){
                //  攻撃ミス
                timer.schedule(2000, { runOnUiThread { buttleAttackMiss() } })
            }else{
                //  攻撃成功
                timer.schedule(2000, { runOnUiThread { buttleAttack() } })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // タイマークラスのインスタンス化
        timer = Timer()
    }

    override fun onPause() {
        super.onPause()
        // タイマークラスのリリース
        timer.cancel()
    }

    private fun buttleAttack() {
        //  HPから攻撃分を差し引く
        enemyHP = enemyHP - attackPower
        if (enemyHP <= 0){
            enemyHP = 0
        }
        EnemyHP.setText(enemyHP.toString())
        //  攻撃結果メッセージの編集
        actionMSG = "あいてに" + attackPower.toString() + "のダメージ"
        textViewBattleMsg.setText(actionMSG)
        if (enemyHP <= 0){
            timer.schedule(2000,{runOnUiThread { GameWin() }})
        }else{
            timer.schedule(2000,{runOnUiThread { AttackEnemyMSG() }})
        }
    }

    private fun GameWin() {
        actionMSG = "あなたの勝ち"
        textViewBattleMsg.setText(actionMSG)
        buttonBattleAtack.isEnabled = false
    }

    private fun AttackEnemyMSG(){
        actionMSG = "あいての攻撃"
        textViewBattleMsg.setText(actionMSG)
        timer.schedule(2000,{runOnUiThread { AttackEnemy() }})
    }

    private fun AttackEnemy() {
        randomNumber = Random().nextInt(10)
        if (randomNumber < 2){
            buttleEnemyMiss()
        }else{
            //  HPから攻撃分を差し引く
            HP = HP - enemyAttackPower
            if (HP <= 0){
                HP = 0
            }
            actionMSG = NameTitle + "は" + enemyAttackPower.toString() + "のダメージ"
            OwnHP.setText(HP.toString())
            textViewBattleMsg.setText(actionMSG)
            if (HP == 0){
                timer.schedule(2000,{runOnUiThread { actionLose() }})
            }else{
                timer.schedule(2000,{runOnUiThread { actionClerar()}})
            }
        }
    }

    private fun actionLose() {
        arriveMemberCount = arriveMemberCount - 1
        actionMSG = NameTitle + "は力尽きた"
        textViewBattleMsg.setText(actionMSG)

        if (arriveMemberCount == 0) {
            timer.schedule(2000,{runOnUiThread { GameLose() }})
        } else{
            timer.schedule(2000, { runOnUiThread { memberChange() } })
        }
    }

    private fun GameLose() {
        actionMSG = "あなたの負け"
        textViewBattleMsg.setText(actionMSG)
        buttonBattleAtack.isEnabled = false
    }

    private fun memberChange() {
        actionClerar()
        finish()
    }

    private fun actionClerar(){
        actionMSG = ""
        textViewBattleMsg.setText(actionMSG)
        buttonBattleAtack.isEnabled = true
    }

    private fun buttleAttackMiss() {
        actionMSG = NameTitle + "　痛恨のミス"
        textViewBattleMsg.setText(actionMSG)
        timer.schedule(2000,{runOnUiThread { AttackEnemyMSG() }})
    }

    private fun buttleEnemyMiss() {
        actionMSG = "痛恨のミス"
        textViewBattleMsg.setText(actionMSG)
        timer.schedule(2000,{runOnUiThread { actionClerar() }})
    }

    private fun roleOwnSetting() {
        when(NameTitle){
            getString(R.string.CardName1) -> {
                imageViewMyCard.setImageResource(R.drawable.matsuken)
                OwnHP.setText(getString(R.string.HpStatus1))
                attackPower = getString(R.string.AtackStatus1).toInt()
                HP = getString(R.string.HpStatus1).toInt()
            }
            getString(R.string.CardName2) -> {
                imageViewMyCard.setImageResource(R.drawable.yuhei)
                OwnHP.setText(getString(R.string.HpStatus2))
                attackPower = getString(R.string.AtackStatus2).toInt()
                HP = getString(R.string.HpStatus2).toInt()
            }
            getString(R.string.CardName3) -> {
                imageViewMyCard.setImageResource(R.drawable.yasu)
                OwnHP.setText(getString(R.string.HpStatus3))
                attackPower = getString(R.string.AtackStatus3).toInt()
                HP = getString(R.string.HpStatus3).toInt()
            }
            getString(R.string.CardName4) -> {
                imageViewMyCard.setImageResource(R.drawable.zako1)
                OwnHP.setText(getString(R.string.HpStatus4))
                attackPower = getString(R.string.AtackStatus4).toInt()
                HP = getString(R.string.HpStatus4).toInt()
            }
            getString(R.string.CardName5) -> {
                imageViewMyCard.setImageResource(R.drawable.zako2)
                OwnHP.setText(getString(R.string.HpStatus5))
                attackPower = getString(R.string.AtackStatus5).toInt()
                HP = getString(R.string.HpStatus5).toInt()
            }
            getString(R.string.CardName6) -> {
                imageViewMyCard.setImageResource(R.drawable.zako3)
                OwnHP.setText(getString(R.string.HpStatus6))
                attackPower = getString(R.string.AtackStatus6).toInt()
                HP = getString(R.string.HpStatus6).toInt()
            }
        }
    }

}
