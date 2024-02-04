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
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.androidprojecttest.databinding.ActivityMainBinding
import kr.co.lion.android01.androidprojecttest.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //animalInput을 받을 런쳐
    lateinit var animalInputActivitylauncher:ActivityResultLauncher<Intent>

    //사자
    var lionList = mutableListOf<LionClass>()
    //호랑이
    var tigerList = mutableListOf<TigerClass>()
    // 기린
    var giraffeList = mutableListOf<GiraffeClass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolBar()
        floatButton()
        initView()

    }

    fun initData(){
        var contract = ActivityResultContracts.StartActivityForResult()
        animalInputActivitylauncher = registerForActivityResult(contract){
            when(it.resultCode){
                RESULT_OK -> {
                    if (it.data != null){
                        var ani1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            it?.data!!.getParcelableExtra("lion", LionClass::class.java)
                        }else{
                            it?.data!!.getParcelableExtra<LionClass>("lion")
                        }
                        lionList.add(ani1!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()

                        //Log.e("test1234", "$ani1")
                    }

                }
                RESULT_CANCELED -> {
                    if (it.data != null){
                        var ani2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            it?.data!!.getParcelableExtra("tiger", TigerClass::class.java)
                        }else{
                            it?.data!!.getParcelableExtra<TigerClass>("tiger")
                        }
                        tigerList.add(ani2!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
                RESULT_FIRST_USER -> {
                    if (it.data != null){
                        var ani3 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            it?.data!!.getParcelableExtra("giraffe", GiraffeClass::class.java)
                        }else{
                            it?.data!!.getParcelableExtra<GiraffeClass>("giraffe")
                        }
                        giraffeList.add(ani3!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
            }

        }

    }

    fun setToolBar(){
        activityMainBinding.apply {
            toolBarMain.apply {
                //타이틀 설정
                title = "동물원 관리"
                setTitleTextColor(Color.BLACK)

                //메뉴
                inflateMenu(R.menu.main_menu)
                //
            }
        }
    }
    fun floatButton(){
        activityMainBinding.apply {
            fabMainAdd.setOnClickListener {
                var newIntent = Intent(this@MainActivity, InputAnimalActivity::class.java)
                animalInputActivitylauncher.launch(newIntent)
            }
        }
    }

    fun initView(){
        activityMainBinding.apply {
            recyclerViewMain.apply {
                //어댑터 객체 생성
                adapter = RecyclerviewAdapter()
                //레이아웃 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                //데코
                var deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    //어댑터 class 생성
    inner class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolderClass>(){

        //viewHolderClass 생성
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            var rowMainBinding:RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                //가로세로 길이 설정
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            //viewBinding
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            //viewHolder
            var viewHolderMain = ViewHolderClass(rowMainBinding)
            return viewHolderMain

        }

        override fun getItemCount(): Int {
            return lionList.size
            return giraffeList.size
            return tigerList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        }

    }
}























































