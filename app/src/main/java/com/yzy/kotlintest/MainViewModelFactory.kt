package com.yzy.kotlintest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author: playboi_YzY
 * @date: 2022/9/28 20:09
 * @description:
 * @version:
 */
class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //创建了MainViewModel 的实例
        return MainViewModel(countReserved) as T
    }
}