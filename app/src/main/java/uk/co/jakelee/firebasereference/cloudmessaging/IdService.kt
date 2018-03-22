package uk.co.jakelee.firebasereference.cloudmessaging

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class IdService: FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        // Can listen to new registration tokens as they are generated
        val refreshedToken = FirebaseInstanceId.getInstance().token
    }
}