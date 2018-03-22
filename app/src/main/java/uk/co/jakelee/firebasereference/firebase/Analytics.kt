package uk.co.jakelee.firebasereference.firebase

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import uk.co.jakelee.firebasereference.R

class Analytics(private val context: Context): Base {
    override val name = R.string.name_analytics
    override val description = R.string.desc_analytics
    override val documentation = R.string.docs_analytics
    override val source = R.string.source_analytics

    override fun testService() {
        // In this example, log a player spending 100 virtual coins
        val event = FirebaseAnalytics.Event.SPEND_VIRTUAL_CURRENCY
        val firstParam = Pair(FirebaseAnalytics.Param.CHARACTER, "Default")
        val secondParam = Pair(FirebaseAnalytics.Param.CURRENCY, "Coins")
        val thirdParam = Pair(FirebaseAnalytics.Param.QUANTITY, "100")
        exampleEventSender(event, firstParam, secondParam, thirdParam)
    }

    private fun exampleEventSender(event: String, vararg params: Pair<String, String>) {
        // Parameter key-value pairs can be any string
        val bundle = Bundle()
        for (param in params) {
            bundle.putString(param.first, param.second)
        }

        // Event can also be any string
        FirebaseAnalytics.getInstance(context).logEvent(event, bundle)

        // Report success
        Toast.makeText(context, context.getString(R.string.alert_analytics), Toast.LENGTH_SHORT)
                .show()
    }
}