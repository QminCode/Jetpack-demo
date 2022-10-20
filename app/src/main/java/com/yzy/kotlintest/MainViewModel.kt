package com.yzy.kotlintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author: playboi_YzY
 * @date: 2022/9/28 18:10
 * @description: 计数器Model
 * @version:
 */
class MainViewModel(countReserved: Int) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter //在counter的get方法中返回_counter变量
    init {
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = _counter.value ?: 0 //当获取到的数据为空时，就用0来作为默认计数
        _counter.value = count + 1
    }

    fun clear(){
        _counter.value = 0
    }
}