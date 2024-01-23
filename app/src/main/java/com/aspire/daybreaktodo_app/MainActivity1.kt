package com.aspire.daybreaktodo_app
import android.opengl.Visibility
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aspire.daybreaktodo_app.fragments.LoginFragment
import com.aspire.daybreaktodo_app.fragments.SplashFragment

class MainActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)


    }

    fun loadFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.nav_host_fragment, fragment)
        ft.commit()
    }
}