package kr.co.lion.android01.androidprojecttest

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.core.view.iterator
import kr.co.lion.android01.androidprojecttest.databinding.ActivityAnimalInfoBinding
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding
import kr.co.lion.androidproject1test.AnimalType
import kr.co.lion.androidproject1test.Util

class AnimalInfoActivity : AppCompatActivity() {
    lateinit var activityAnimalInfoBinding: ActivityAnimalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAnimalInfoBinding = ActivityAnimalInfoBinding.inflate(layoutInflater)
        setContentView(activityAnimalInfoBinding.root)
        setToolBar()

    }

    //툴바 설정
    fun setToolBar(){
        activityAnimalInfoBinding.apply {
            toolbarAnimalInfo.apply {
                //타이틀 설정
                title = "동물 정보"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 떄
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.animalinfo_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_modify -> {
                            var newIntent = Intent(this@AnimalInfoActivity, ModifyInfoActivity::class.java)
                            var info = intent.getIntExtra("position", 0)
                            newIntent.putExtra("position", info)
                            startActivity(newIntent)
                        }
                        R.id.menu_delect -> {
                            var inin = intent.getIntExtra("position" , 0)
                            //와 여기서 삭제를 하는구나,,,
                            Util.animalList.removeAt(inin)
                            finish()

                        }
                    }
                    true
                }
            }
        }
    }

    //받아온 값을 보여준다
    fun showResult(){
        activityAnimalInfoBinding.apply {
            animalInfoText.apply {
                var info = intent.getIntExtra("position", 0)
                //animal객체 생성
                //메인에서 받아온 값을 넣어준다
                var animal = Util.animalList[info]
                //공통
                text = "동물 타입 : ${animal.type.str}\n"
                append("이름 : ${animal.name}\n")
                append("나이 : ${animal.age}살\n")

                //동물의 타입으로 분기한다
                when(animal.type){
                    AnimalType.ANIMAL_TYPE_LION -> {
                        //형 변환 잊지말자 정보는 모두 animalList에 들어있다는 걸 잊지마라
                        var lion = animal as LionClass
                        append("털의 갯수 : ${lion.fairCount}개\n")
                        append("성별 : ${lion.gender.str}\n")

                    }
                    AnimalType.ANIMAL_TYPE_TIGER -> {
                        var tiger = animal as TigerClass
                        append("줄무늬 갯수 : ${tiger.lineCount}개\n")
                        append("몸무게 : ${tiger.weight}kg\n")

                    }
                    AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                        var giraffe = animal as GiraffeClass
                        append("목의 길이 : ${giraffe.neck}cm\n")
                        append("달리는 속도 : ${giraffe.runSpeed}km\n")
                    }
                }

            }
        }

    }
    override fun onResume() {
        super.onResume()
        //다른 곳에 갔다 왔을 경우 출력 내용을다시 구성해준다
        showResult()
    }





















































}