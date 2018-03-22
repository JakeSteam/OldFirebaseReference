package uk.co.jakelee.firebasereference.analytics

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import uk.co.jakelee.firebasereference.R
import uk.co.jakelee.firebasereference.BaseService

class Analytics(private val context: Activity): BaseService {
    override val name = R.string.name_analytics
    override val description = R.string.desc_analytics
    override val documentation = R.string.docs_analytics
    override val source = R.string.source_analytics

    override fun testService() {
        val event = FirebaseAnalytics.Event.SPEND_VIRTUAL_CURRENCY
        val firstParam = Pair(FirebaseAnalytics.Param.CHARACTER, "Default")
        val secondParam = Pair(FirebaseAnalytics.Param.CURRENCY, "Coins")
        val thirdParam = Pair(FirebaseAnalytics.Param.QUANTITY, "100")
        eventSender(event, firstParam, secondParam, thirdParam)
        propertyAssigner("role", "veteran")
        screenTracker("Analytics Screen")
    }

    private fun eventSender(event: String, vararg params: Pair<String, String>) {
        // Parameter key-value pairs can be any string, as can events
        val bundle = Bundle()
        for (param in params) {
            bundle.putString(param.first, param.second)
        }
        FirebaseAnalytics.getInstance(context).logEvent(event, bundle)
        Toast.makeText(context, context.getString(R.string.alert_analytics_event), Toast.LENGTH_SHORT)
                .show()
    }

    private fun propertyAssigner(property: String, value: String) {
        // Property and value can be any string
        FirebaseAnalytics.getInstance(context).setUserProperty(property, value)
        Toast.makeText(context, context.getString(R.string.alert_analytics_property), Toast.LENGTH_SHORT)
                .show()
    }

    private fun screenTracker(screenName: String) {
        // Activity / View Controller transitions are tracked automatic, but can be manually set
        FirebaseAnalytics.getInstance(context).setCurrentScreen(context, screenName, null)
        Toast.makeText(context, context.getString(R.string.alert_analytics_screen), Toast.LENGTH_SHORT)
                .show()
    }
}