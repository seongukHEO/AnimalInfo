package kr.co.lion.android01.androidprojecttest

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android01.androidprojecttest.databinding.ActivityAnimalInfoBinding
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding

class AnimalInfoActivity : AppCompatActivity() {
    lateinit var activityAnimalInfoBinding: ActivityAnimalInfoBinding

    //Activity런펴
    lateinit var modifyActivitylauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAnimalInfoBinding = ActivityAnimalInfoBinding.inflate(layoutInflater)
        setContentView(activityAnimalInfoBinding.root)

        initData()
        setToolBar()
        setView()
    }


    fun initData(){
        //갔다가 돌아왔을 때 동작하는 부분
        var contract = ActivityResultContracts.StartActivityForResult()
        modifyActivitylauncher = registerForActivityResult(contract){

        }

    }

    //툴바 설정
    fun setToolBar(){
        activityAnimalInfoBinding.apply {
            materialToolbar.apply {
                //타이틀 설정
                title = "동물 정보"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌렀을 때?
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //수정 메뉴 설정
                inflateMenu(R.menu.animalinfo_menu)
                //메뉴를 눌렀을 때
                setOnMenuItemClickListener {
                    //사용자가 선택한 메뉴 항목의 id로 분기
                    when(it.itemId){
                        //수정
                        R.id.menu_modify -> {
                            var newIntent = Intent(this@AnimalInfoActivity, ModifyInfoActivity::class.java)
                            modifyActivitylauncher.launch(newIntent)

                        }
                        //삭제
                        R.id.menu_delect -> {


                        }
                    }



                    true
                }
            }
        }
    }

  //View설정
    fun setView(){
        activityAnimalInfoBinding.apply {
            //TextView
            textViewShowInfo.apply {

                var info1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("str1", LionClass::class.java)
                }else{
                    intent?.getParcelableExtra<LionClass>("str1")
                }
                if (info1 != null){
                    text = "동물 종류 : 사자\n"
                    append("이름 : ${info1.name}\n")
                    append("나이 : ${info1.age}살\n")
                    append("털의 개수 : ${info1.fair}개\n")
                    append("성별 : ${info1.gender}\n")
                }

                var info2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("str2", TigerClass::class.java)
                }else{
                    intent?.getParcelableExtra<TigerClass>("str2")
                }
                if (info2 != null){
                    text = "동물 종류 : 호랑이\n"
                    append("이름 : ${info2.name}\n")
                    append("나이 : ${info2.age}살\n")
                    append("줄무늬 개수 : ${info2.stripe}개\n")
                    append("몸무게 : ${info2.weight}kg\n")
                }

                var info3 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("str3", GiraffeClass::class.java)
                }else{
                    intent?.getParcelableExtra<GiraffeClass>("str3")
                }
                if (info3 != null){
                    text = "동물 종류 : 기린\n"
                    append("이름 : ${info3.name}\n")
                    append("나이 : ${info3.age}살\n")
                    append("목의 길이 : ${info3.neck}cm\n")
                    append("달리는 속도 : 시속 ${info3.run}km\n")
                }




            }
        }
    }












































}