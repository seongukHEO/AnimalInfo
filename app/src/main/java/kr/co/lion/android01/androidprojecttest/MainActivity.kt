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

    //animalInput을 받을 런쳐
    lateinit var animalInputActivitylauncher:ActivityResultLauncher<Intent>

    //animalInfo를 받은 런쳐
    lateinit var animalInfoActivitylauncher:ActivityResultLauncher<Intent>

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

        //메서드 호출 순서는 상관 없다
        initData()
        setToolBar()
        floatButton()
        initView()

    }

    fun initData(){
        var contract = ActivityResultContracts.StartActivityForResult()
        animalInputActivitylauncher = registerForActivityResult(contract) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                var ani1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data!!.getParcelableExtra("obj1", LionClass::class.java)
                } else {
                    result.data!!.getParcelableExtra<LionClass>("obj1")
                }
                if (ani1 != null) {
                    lionList.add(ani1)
                    activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                }

                var ani2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data!!.getParcelableExtra("obj2", TigerClass::class.java)
                } else {
                    result.data!!.getParcelableExtra<TigerClass>("obj2")
                }
                if (ani2 != null) {
                    tigerList.add(ani2)
                    activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                }

                var ani3 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data!!.getParcelableExtra("obj3", GiraffeClass::class.java)
                } else {
                    result.data!!.getParcelableExtra<GiraffeClass>("obj3")
                }
                if (ani3 != null) {
                    giraffeList.add(ani3)
                    activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                }
            }
        }





        var contract2 = ActivityResultContracts.StartActivityForResult()
        animalInfoActivitylauncher = registerForActivityResult(contract2){

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
                //InputAnimalActivity를 실행
                var inputIntent = Intent(this@MainActivity, InputAnimalActivity::class.java)
                animalInputActivitylauncher.launch(inputIntent)
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

    fun showDiaLog(title:String, message:String){
        var viewDiaLog = MaterialAlertDialogBuilder(this).apply {
            setTitle(title)
            setMessage(message)

        }
    }

    //어댑터 class 생성
    inner class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolderClass>(){

        var animalList = mutableListOf<AnimalClass>()

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

                //recyclerView를 클릭했을 때!
                this.rowMainBinding.root.setOnClickListener {
                   var infoIntent = Intent(this@MainActivity, AnimalInfoActivity::class.java)
                    infoIntent.putExtra("str1", animalList[adapterPosition])
                    animalInfoActivitylauncher.launch(infoIntent)
                }
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
            return lionList.size + tigerList.size + giraffeList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            //뷰 타입에 따라 데이터를 바인딩
            animalList.get(position)
            holder.rowMainBinding.textViewRowMainName.text = animalList[position].name
            holder.rowMainBinding.imageViewRowMainType.setImageResource(animalList[position].uriImage)

        }

    }
}























































