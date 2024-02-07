package kr.co.lion.android01.androidprojecttest

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.androidprojecttest.databinding.ActivityMainBinding
import kr.co.lion.android01.androidprojecttest.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setToolBar()
        clickFloatButton()
        initView()

    }
    //툴바 설정
    fun setToolBar() {
        activityMainBinding.apply {
            toolBarMain.apply {
                //타이틀 설정
                title = "동물원 관리"

                //메뉴 설정
                inflateMenu(R.menu.main_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_item_main_filter -> {

                        }
                    }

                    true
                }
            }
        }

    }

    //floatButton
    fun clickFloatButton() {
        activityMainBinding.apply {
            fabMainAdd.apply {
                setOnClickListener {
                    var newIntent = Intent(this@MainActivity, InputAnimalActivity::class.java)
                    startActivity(newIntent)
                }
            }
        }

    }
    //어댑터 객체 생성
    fun initView(){
        activityMainBinding.apply {
            recyclerViewMain.apply {
                //어댑터 객체
                adapter = AnimalRecyclerView()
                //레이아웃
                layoutManager = LinearLayoutManager(this@MainActivity)
                //데코
                var deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    inner class AnimalRecyclerView : RecyclerView.Adapter<AnimalRecyclerView.AnimalViewHolder>(){

        //viewHolderClass생성
        inner class AnimalViewHolder(rowMainBinding: RowMainBinding):RecyclerView.ViewHolder(rowMainBinding.root){
            var rowMainBinding:RowMainBinding
            init {
                this.rowMainBinding = rowMainBinding

                //가로 세로 길이를 설정해준다
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
            //viewBinding
            var rowMainBinding = RowMainBinding.inflate(layoutInflater)

            //viewHolderClass
            var animalviewHolder = AnimalViewHolder(rowMainBinding)
            return animalviewHolder
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "허성욱"
            holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
        }
    }

}



















































