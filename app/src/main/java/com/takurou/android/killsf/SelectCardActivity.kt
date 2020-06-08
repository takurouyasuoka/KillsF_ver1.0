package com.takurou.android.killsf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isInvisible
import androidx.core.view.size
import kotlinx.android.synthetic.main.activity_select_card.*

class SelectCardActivity : AppCompatActivity(), AdapterView.OnItemClickListener,
    AdapterView.OnItemLongClickListener {

    var listSelectCount = 0
    var selectedSW = 0
    var clicked :Boolean = false
    var selectedItemPosition :MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_card)

        // todo 1.Listviewに一覧表示
        // 表示するListの準備
        val Card_List = ArrayList<String>()
        // Ｌｉｓｔへのカード情報追加
        Card_List.add(getString(R.string.CardName1) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus1)
                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus1)
                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus1))
        Card_List.add(getString(R.string.CardName2) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus2)
                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus2)
                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus2))
        Card_List.add(getString(R.string.CardName3) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus3)
                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus3)
                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus3))
        Card_List.add(getString(R.string.CardName4) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus4)
                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus4)
                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus4))
        Card_List.add(getString(R.string.CardName5) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus5)
                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus5)
                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus5))
        Card_List.add(getString(R.string.CardName6) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus6)
                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus6)
                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus6))
//        Card_List.add(getString(R.string.CardName7) + "　" + getString(R.string.HpTitle) + getString(R.string.HpStatus7)
//                + "　" + getString(R.string.AtackTitle) + getString(R.string.AtackStatus7)
//                + "　" + getString(R.string.DefenceTitle) + getString(R.string.DefenceStatus7))

        // ListViewへの表示
        val adapter =ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,Card_List)
        listView.adapter = adapter

//        // SearchViewの初期表示状態を設定
//        searchView.isIconifiedByDefault = false
//
//        // SearchViewにOnQueryChangeListenerを設定
//        searchView.setOnQueryTextFocusChangeListener(this)
//
//        // SearchViewのSubmitボタンを使用不可にする
//        searchView.isSubmitButtonEnabled = true
//
//        // SearchViewに何も入力していない時のテキストを設定
//        searchView.queryHint = getString(R.string.searchHint)

        // 2.ButtonNextを非活性（５枚選択されるまで）
        buttonNext.isEnabled = false

        // 3.Listタップ時の処理
        listView.setOnItemClickListener(this)

        // 4.List長押し時の処理
        listView.setOnItemLongClickListener(this)

        // 5.ButtonNextクリック時の処理
        buttonNext.setOnClickListener {
            // 選択したItemPositionの取得
            val checked :SparseBooleanArray = listView.checkedItemPositions
            for (i in 0 .. 4){
//                if (checked.get(i)){
                    selectedItemPosition.add(checked.keyAt(i))
//                }
            }

            // インテントの受け渡し先の設定
            val intent = Intent(this@SelectCardActivity,ChoiseCardActivity::class.java)
            // インテントへ受け渡す情報の設定
            intent.putExtra("selectedItemPosition",selectedItemPosition.toIntArray())
            // インテントの受け渡し
            startActivity(intent)
            finish()
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        listSelectCount = listView.checkedItemCount
//        listView.checkedItemPosition

        // 3-2.Listの選択状態が５になったら、ButtonNextを活性化
        // タップ数のカウント
        if (listSelectCount == 5){
            buttonNext.isEnabled = true
        }else{
            buttonNext.isEnabled = false
        }

    }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {
        // 長押ししたアイテムの取得
        val longClickItemPosition = position
        // インテントへ受け渡す情報の設定
        val intent = Intent(this@SelectCardActivity,CardImageActivity::class.java)
        intent.putExtra("longClickItemPosition",longClickItemPosition)
        // インテントの受け渡し
        startActivity(intent)
        return true
    }
}


