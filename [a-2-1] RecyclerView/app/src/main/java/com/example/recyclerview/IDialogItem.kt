package com.example.recyclerview

interface IDialogItem {
    fun addItem(item:ItemModel)
    fun openEditItem(posicao:Int, item:ItemModel)
    fun editItem(posicao:Int, item:ItemModel)

    fun removeItem(item:ItemModel)
}