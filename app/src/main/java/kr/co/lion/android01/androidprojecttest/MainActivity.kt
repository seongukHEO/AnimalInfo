package kr.co.lion.android01.androidprojecttest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.androidprojecttest.databinding.ActivityMainBinding
import kr.co.lion.android01.androidprojecttest.databinding.RowMainBinding
import kr.co.lion.androidproject1test.AnimalType
import kr.co.lion.androidproject1test.Util
import kr.co.lion.androidproject1test.showFilterDiaLog

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //info에서 삭제하고 올때
    lateinit var animalInfoActivitylauncher:ActivityResultLauncher<Intent>

    //이제 필터를 써야하기 때문에 여기 있는 값들을 담을 저장소
    var recycleranimal = mutableListOf<AnimalClass>()
    // 현재 항목을 구성하기 위해 사용한 객체가 Util.animalList의 몇번째 객체인지를 담을 리스트
    var recyclerViewIndexList = mutableListOf<Int>()
    //현재 필터의 상태 설정
    var filterDiaLog = showFilterDiaLog.FILTER_TYPE_ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setView()
        setToolBar()
        clickFloatButton()
        initView()

    }

    override fun onResume() {
        super.onResume()
        activityMainBinding.apply {
            setDiaLogEvent()
        }
        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
    }

    //뷰 설정
    fun setView(){
        var contract = ActivityResultContracts.StartActivityForResult()
        animalInfoActivitylauncher = registerForActivityResult(contract){

        }
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
                            showFilterdiaLog()

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
            return recycleranimal.size
        }

        override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
            var animal = recycleranimal[position]
            holder.rowMainBinding.textViewRowMainName.text = animal.name
            when(animal.type){
                AnimalType.ANIMAL_TYPE_LION -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
                }
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
                }
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
                }
            }
            holder.rowMainBinding.root.setOnClickListener {
                var newintent = Intent(this@MainActivity, AnimalInfoActivity::class.java)
                //자리값으로 하기 때문에 position을 입력해준다
                newintent.putExtra("position", recyclerViewIndexList[position])
                animalInfoActivitylauncher.launch(newintent)
            }
        }
    }
    fun showFilterdiaLog(){
        var obj = MaterialAlertDialogBuilder(this@MainActivity)
        obj.setTitle("필터 선택")

        //항목
        var itenArray = arrayOf("전체", "사자", "호랑이", "기린")
        obj.setItems(itenArray){ dialogInterface: DialogInterface, i: Int ->
            //사용자가 선택한 DiaLog의 항목 순서 값으로 분기한다 즉 첫번째 버튼은 0번, 2번째 버튼은 1번
            filterDiaLog = when(i){
                0 -> showFilterDiaLog.FILTER_TYPE_ALL
                1 -> showFilterDiaLog.FILTER_TYPE_LION
                2 -> showFilterDiaLog.FILTER_TYPE_TIGER
                3 -> showFilterDiaLog.FILTER_TYPE_GIRAFFE
                else -> showFilterDiaLog.FILTER_TYPE_ALL
            }
            //데이터를 새로 담는다?
            setDiaLogEvent()

            //리사이클러뷰를 갱신한다
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }
        obj.setPositiveButton("확인", null)
        obj.show()

    }
    //다이알로그 이벤트 주기
    fun setDiaLogEvent(){
        //우선 싹 비워준다
        recycleranimal.clear()
        recyclerViewIndexList.clear()
        //타입별로 분기한다
        when(filterDiaLog){
            showFilterDiaLog.FILTER_TYPE_ALL -> {
                //모두 담는다?
                Util.animalList.forEachIndexed { index, animalClass ->
                    recycleranimal.add(animalClass)
                    recyclerViewIndexList.add(index)
                }
            }
            showFilterDiaLog.FILTER_TYPE_LION -> {
                Util.animalList.forEachIndexed { index, animalClass ->
                    if (animalClass.type == AnimalType.ANIMAL_TYPE_LION){
                        recycleranimal.add(animalClass)
                        recyclerViewIndexList.add(index)
                    }
                }
            }
            showFilterDiaLog.FILTER_TYPE_TIGER -> {
                Util.animalList.forEachIndexed { index, animalClass ->
                    if (animalClass.type == AnimalType.ANIMAL_TYPE_TIGER){
                        recycleranimal.add(animalClass)
                        recyclerViewIndexList.add(index)
                    }
                }
            }
            showFilterDiaLog.FILTER_TYPE_GIRAFFE -> {
                Util.animalList.forEachIndexed { index, animalClass ->
                    if (animalClass.type == AnimalType.ANIMAL_TYPE_GIRAFFE){
                        recycleranimal.add(animalClass)
                        recyclerViewIndexList.add(index)
                    }

                }

            }
        }
    }

}



















































