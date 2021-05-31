package com.spiraldev.cryptoticker.ui.home.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.data.local.database.CoinsListEntity

private const val REMOTE_VIEW_COUNT: Int = 10

class StackRemoteViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {

    private lateinit var widgetItems: List<CoinsListEntity>

    override fun onCreate() {
        // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
    }

    override fun onDataSetChanged() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewAt(position: Int): RemoteViews {
        return RemoteViews(context.packageName, R.layout.app_widget).apply {
            setTextViewText(R.id.empty_view, widgetItems[position].name)
        }
    }

    override fun getLoadingView(): RemoteViews {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun hasStableIds(): Boolean {
        TODO("Not yet implemented")
    }
}