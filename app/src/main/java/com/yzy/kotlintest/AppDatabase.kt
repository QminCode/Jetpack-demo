package com.yzy.kotlintest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author: playboi_YzY
 * @date: 2022/10/11 22:15
 * @description:
 * @version:
 */


@Database(version = 1, entities = [User::class])   //声明数据库的版本号，包含哪些实体类，多个实体类之间可用逗号隔开
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao //提供抽象方法，用于获取已编写的Dao实例；只声明方法，具体实现在Room底层自动完成
    companion object {  //Kotlin的类中不允许你声明静态成员或方法,要添加Companion对象来包装这些静态引用
        private var instance: AppDatabase? = null
        @Synchronized
        fun getDatabase(context: Context) : AppDatabase {
            instance?.let {
                return it //instance变量不为空直接返回
            }
            //否则就调用Room.databaseBuilder()方法来构建一个AppDatabase的实例
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app_database")
                .build().apply {
                    instance = this
                }
        }
    }
}