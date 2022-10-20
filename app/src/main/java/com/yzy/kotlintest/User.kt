package com.yzy.kotlintest

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author: playboi_YzY
 * @date: 2022/10/10 17:19
 * @description:
 * @version:
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}