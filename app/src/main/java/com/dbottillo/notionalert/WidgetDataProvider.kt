package com.dbottillo.notionalert

import android.R.id.text1
import android.R.layout.simple_list_item_1
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.dbottillo.notionalert.feature.home.HomeStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class WidgetDataProvider(
    private val context: Context,
    intent: Intent,
    private val homeStorage: HomeStorage
) : RemoteViewsService.RemoteViewsFactory {

    private var dataList = mutableListOf<String>()

    override fun onCreate() {
        initData()
    }

    override fun onDataSetChanged() {
        initData()
    }

    @Suppress("EmptyFunctionBlock")
    override fun onDestroy() {
    }

    override fun getCount() = dataList.size

    override fun getViewAt(position: Int): RemoteViews {
        val view = RemoteViews(
            context.packageName,
            simple_list_item_1
        )
        view.setTextViewText(text1, dataList[position])
        return view
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount() = 1

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds() = true

    private fun initData() {
        dataList.clear()
        runBlocking {
            homeStorage.data.first().nextActions.split("\n").forEach { entry ->
                dataList.add(entry)
            }
        }
    }
}
