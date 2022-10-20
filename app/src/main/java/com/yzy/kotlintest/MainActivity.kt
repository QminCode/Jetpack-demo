package com.yzy.kotlintest

import android.app.StatusBarManager
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.notification.StatusBarNotification
import android.util.Log
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yzy.kotlintest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences
    //创建两个User对象
    val userDao = AppDatabase.getDatabase(this).userDao()
    val user1 = User("Tom", "Brady", 40)
    val user2 = User("Tom", "Hanks", 63)
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        /**通过ViewModelProvider 来获取ViewModel 的实例
         * 因为ViewModel 有其独立的生命周期，并且其生命周期要长于Activity 。
        如果我们在onCreate()方法中创建ViewModel 的实例，那么每次onCreate()方法执行的时
        候，ViewModel 都会创建一个新的实例，这样当手机屏幕发生旋转的时候，就无法保留其中的
        数据了
         */
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
//        binding.plusOneBtn.setOnClickListener {
//            viewModel.plusOne()
//        }
//        binding.clearBtn.setOnClickListener{
//            viewModel.clear()
//        }
        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2) //更新ID
            }
        }
        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }
        viewModel.counter.observe(this, Observer { count ->
            binding.infoText.text = count.toString()
        })
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }

}