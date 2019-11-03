package myapp.anatoleallain.com.android_avance.views

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import myapp.anatoleallain.com.android_avance.R

class MainActivity : AppCompatActivity(), Games_list.ViewDetails, Game_description.VisitWebsite {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val fragL = Games_list()
        transaction.add(R.id.fragmentContainer, fragL)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun viewDetails(id : Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragD = Game_description.newInstance(id)
        transaction.replace(R.id.fragmentContainer, fragD)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun visitWebsite(link: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(link)
        startActivity(openURL)
    }
}
