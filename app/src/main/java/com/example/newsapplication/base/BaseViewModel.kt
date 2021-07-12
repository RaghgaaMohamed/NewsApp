package com.example.newsapplication.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<N> :ViewModel() {
    var navigator :N? =null
}