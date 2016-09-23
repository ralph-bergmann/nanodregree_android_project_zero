package udacty.nanadegree.projectzero.utils

import android.view.LayoutInflater
import android.view.View
import udacty.nanadegree.projectzero.data.App

fun View.layoutInflater(): LayoutInflater =
    LayoutInflater.from(context)

fun Array<kotlin.String>.toAppList(): List<App> =
    mapIndexed { i: Int, string: kotlin.String -> App(i, string) }
