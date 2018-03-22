package uk.co.jakelee.firebasereference.cloudmessaging

import android.app.Activity
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import uk.co.jakelee.firebasereference.BaseService
import uk.co.jakelee.firebasereference.R



class CloudMessaging(private val context: Activity): BaseService {
    override val name = R.string.name_cloudmessaging
    override val description = R.string.desc_cloudmessaging
    override val documentation = R.string.docs_cloudmessaging
    override val source = R.string.source_cloudmessaging

    override fun testService() {
        idFetcher()
        topicSubscriber("news")
        sendMessageToServer()
    }

    private fun idFetcher() {
        val latestId = FirebaseInstanceId.getInstance().token
        Toast.makeText(context,
                String.format(context.getString(R.string.alert_cloudmessaging_id), latestId),
                Toast.LENGTH_SHORT)
                .show()
    }

    private fun topicSubscriber(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
        Toast.makeText(context, context.getString(R.string.alert_cloudmessaging_topic),
                Toast.LENGTH_SHORT).show()
    }

    private fun sendMessageToServer() {
        val fm = FirebaseMessaging.getInstance()
        fm.send(RemoteMessage.Builder("example-server@gcm.googleapis.com")
                .setMessageId(1234.toString())
                .addData("my_message", "Hello World")
                .addData("my_action", "SAY_HELLO")
                .build())
        Toast.makeText(context, context.getString(R.string.alert_cloudmessaging_send),
                Toast.LENGTH_SHORT).show()
    }
}