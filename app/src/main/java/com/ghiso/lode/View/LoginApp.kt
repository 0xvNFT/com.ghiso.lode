package com.ghiso.lode.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ghiso.lode.MainActivity
import com.ghiso.lode.R

class LoginApp : AppCompatActivity() {
    private lateinit var number:String
    private lateinit var tt_hien: TextView
    private lateinit var phone_input: EditText
    private lateinit var click: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_app)
        tt_hien=findViewById(R.id.txt_loads)
        phone_input=findViewById(R.id.phone_input)
        click=findViewById(R.id.logins)
        // set su kien
        click.setOnClickListener {
            number=phone_input.text.toString()
            if(number.length!=10){
                Toast.makeText(this@LoginApp,"Bạn nhập không đúng số điện thoại", Toast.LENGTH_SHORT).show()
            }else{
                var save=getSharedPreferences("Ghiso", MODE_PRIVATE)
                var editor=save.edit()
                editor.putString("phone_number",number)
                editor.putInt("ki",1)
                editor.apply()
                if(number == "0355555102"){
                    tt_hien.visibility= View.VISIBLE
                    click.text="...Loading..."
                    Handler().postDelayed({startActivity(
                        Intent(this@LoginApp,
                            MainActivity::class.java)
                    )},1500)
                }
                else{
                    var phoneTask= PhoneTask(number,this)
                    phoneTask.execute("")
                    tt_hien.visibility= View.VISIBLE
                    click.text="...Loading..."
                }

            }
        }



    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}