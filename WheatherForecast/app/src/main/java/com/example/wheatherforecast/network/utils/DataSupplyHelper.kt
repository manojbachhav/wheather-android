package com.example.wheatherforecast.network.utils

interface DataSupplyHelper<T> {

    fun get(): T
}
