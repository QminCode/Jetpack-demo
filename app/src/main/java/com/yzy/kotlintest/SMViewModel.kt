package com.yzy.kotlintest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * @author: playboi_YzY
 * @date: 2022/10/5 17:09
 * @description:
 * @version:
 */
class SMViewModel: ViewModel() {
    private var studentLiveData = MutableLiveData<Student>()
    val transformationsLiveData = Transformations.switchMap(studentLiveData) {
        if (it.scoreTAG) {
            MutableLiveData(it.englishScore)
        } else {
        MutableLiveData(it.mathScore)
        }
    }
}