package kr.co.lion.android01.androidprojecttest

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android01.androidprojecttest.databinding.ActivityAnimalInfoBinding
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding

class AnimalInfoActivity : AppCompatActivity() {
    lateinit var activityAnimalInfoBinding: ActivityAnimalInfoBinding

    //Activity런펴
    lateinit var modifyActivitylauncher:ActivityResultLauncher<Intent>

    //사자
    var lionList = mutableListOf<LionClass>()
    var tigerList = mutableListOf<TigerClass>()
    var giraffeList = mutableListOf<GiraffeClass>()


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
                    var please = textViewShowInfo.text.toString()
                    //사용자가 선택한 메뉴 항목의 id로 분기
                    when(it.itemId){

                        //수정
                        R.id.menu_modify -> {
                            var newIntent = Intent(this@AnimalInfoActivity, ModifyInfoActivity::class.java)
                            newIntent.putExtra("please1", LionClass::class.java)
                            newIntent.putExtra("please2", TigerClass::class.java)
                            newIntent.putExtra("please3", GiraffeClass::class.java)
                            modifyActivitylauncher.launch(newIntent)

                        }
                        //삭제
                        R.id.menu_delect -> {
                            var newIntent = Intent()
                            newIntent.putExtra("ak1", AnimalClass::class.java)
                            setResult(RESULT_OK, newIntent)
                            finish()


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
            textViewShowInfo.apply {
                var info2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("str2", LionClass::class.java)
                }else{
                    intent?.getParcelableExtra<LionClass>("str2")
                }

                var info3 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("str3", TigerClass::class.java)
                }else{
                    intent?.getParcelableExtra<TigerClass>("str3")
                }

                var info4 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("str4", GiraffeClass::class.java)
                }else{
                    intent?.getParcelableExtra<GiraffeClass>("str4")
                }
                //Log.e("test1234", "${info4?.neck}")
                //Log.e("test1234", "${info2?.fair}")
                if (info2 != null){
                    text = "동물 타입 : 사자\n"
                    append("이름 : ${info2.name}\n")
                    append("나이 : ${info2.age}\n")
                    append("털의 갯수 : ${info2.fair}\n")
                    append("성별 : ${info2.gender}\n")

                // Log.e("test1234", "${info2.fair}")
                }else if (info3 != null){
                    text = "동물 타입 : 호랑이\n"
                    append("이름 : ${info3.name}\n")
                    append("나이 : ${info3.age}\n")
                    append("줄무늬 갯수 : ${info3.stripe}\n")
                    append("성별 : ${info3.weight}\n")
                } else if (info4 != null){
                    text = "동물 타입 : 기린\n"
                    append("이름 : ${info4.name}\n")
                    append("나이 : ${info4.age}\n")
                    append("목의 길이 : ${info4.neck}\n")
                    append("달리기 속도 : ${info4.run}\n")
                }





            }
        }
    }

















































}