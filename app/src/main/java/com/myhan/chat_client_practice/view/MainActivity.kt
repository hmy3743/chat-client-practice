package com.myhan.chat_client_practice.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myhan.chat_client_practice.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setOnNavigationItemSelectedListener { navigate(it) }
    }

    private fun navigate(menuItem: MenuItem): Boolean {
        var fragmentInstance: Fragment? = null
        when(menuItem.itemId) {
            R.id.menuItemFriend -> {
                fragmentInstance = FragmentFriend.newInstance()
            }
            R.id.menuItemChatting -> {
                fragmentInstance = FragmentChatting.newInstance()
            }
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragmentInstance!!)
            .commitAllowingStateLoss()
        return true
    }
}
