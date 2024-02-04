package kr.co.lion.android01.androidprojecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding


class InputAnimalActivity : AppCompatActivity() {
    lateinit var activityInputAnimalBinding:ActivityInputAnimalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputAnimalBinding = ActivityInputAnimalBinding.inflate(layoutInflater)
        setContentView(activityInputAnimalBinding.root)

        initData()
        activityInputAnimalBinding.toggleGroup1.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked){
                toggleButtonClick(checkedId)
            }
        }

        setToolBar()



    }

    fun initData(){

    }

    fun setToolBar(){
        activityInputAnimalBinding.apply {
            materialToolbar2.apply {
                //타이틀 설정
                title = "동물 등록"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.inputanimal_menu)
                //메뉴를 클릭했을 때?
                setOnMenuItemClickListener {
                    when(toggleGroup1.checkedButtonId){
                        R.id.lionButton -> {
                            var lion1 = nameInputText.text.toString()
                            var lion2 = ageInputText.text.toString().toInt()
                            var lion3 = chooseInputText.text.toString().toInt()
                            var lion4 = choose2InputText.text.toString()

                            var animal1 = LionClass(lion1, lion2, lion3, lion4)

                            var newIntent = Intent()
                            newIntent.putExtra("lion", animal1)
                            setResult(RESULT_OK, newIntent)
                            finish()

                        }
                        R.id.tigerButton -> {
                            var tiger1 = nameInputText.text.toString()
                            var tiger2 = ageInputText.text.toString().toInt()
                            var tiger3 = chooseInputText.text.toString().toInt()
                            var tiger4 = choose2InputText.text.toString().toInt()

                            var animal2 = TigerClass(tiger1, tiger2, tiger3, tiger4)

                            var newIntent2 = Intent()
                            newIntent2.putExtra("tiger", animal2)
                            setResult(RESULT_CANCELED, newIntent2)
                            finish()

                        }
                        R.id.giraffeButton -> {
                            var giraffe1 = nameInputText.text.toString()
                            var giraffe2 = ageInputText.text.toString().toInt()
                            var giraffe3 = chooseInputText.text.toString().toInt()
                            var giraffe4 = choose2InputText.text.toString().toInt()

                            var animal3 = GiraffeClass(giraffe1, giraffe2, giraffe3, giraffe4)

                            var newIntent3 = Intent()
                            newIntent3.putExtra("giraffe", animal3)
                            setResult(RESULT_FIRST_USER, newIntent3)
                            finish()
                        }
                    }




                    true
                }
            }
        }

    }
    fun toggleButtonClick(checkedId:Int){
        activityInputAnimalBinding.apply {
            when(checkedId){
                R.id.tigerButton -> {
                    choose1.hint = "줄무늬 갯수"
                    choose2.hint = "몸무게"
                    randomText.text = "몸무게는 50kg~200kg사이로 입력하시오"
                }
                R.id.lionButton -> {
                    choose1.hint = "털의 갯수"
                    choose2.hint = "성별"
                    randomText.text = "암컷 또는 수컷으로 입력하시오"
                }
                R.id.giraffeButton -> {
                    choose1.hint = "목의 길이"
                    choose2.hint = "달리는 속도"
                    randomText.text = ""
                }

            }


        }
    }



    }


















































