package com.arpak.ikrayourself.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arpak.ikrayourself.data.model.UserModel
import com.arpak.ikrayourself.util.Constants.FAILURE
import com.arpak.ikrayourself.util.Constants.PHONE_NUMBER
import com.arpak.ikrayourself.util.Constants.SIGN_UP
import com.arpak.ikrayourself.util.Constants.SIGN_IN
import com.arpak.ikrayourself.util.Constants.USERS
import com.arpak.ikrayourself.util.Constants.USER_INFO
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase





class UserRepository {

    var isSignIn = MutableLiveData<Boolean>()
    var isSignUp = MutableLiveData<Boolean>()

    var userInfo = MutableLiveData<UserModel>()

    private var auth = Firebase.auth
    private var db = Firebase.firestore


    fun signIn(eMail: String, password: String) {

        auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                isSignIn.value = true

            } else {

                isSignIn.value = false
                Log.w(SIGN_IN, FAILURE,task.exception)
            }
        }

    }

    fun sigUp(email: String, password: String, phoneNumber: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val currentUser = auth.currentUser
                currentUser?.let { firebaseUser ->
                    val user = hashMapOf(
                        "id" to firebaseUser.uid,
                        "e_mail" to email,
                        "phone_number" to phoneNumber
                    )

                    db.collection("users").document(firebaseUser.uid)
                        .set(user)
                        .addOnSuccessListener {
                            isSignUp.value = true
                        }
                        .addOnFailureListener { exc ->
                            isSignUp.value = false
                            Log.w(SIGN_UP, FAILURE, exc)
                        }

                }
            } else {
                isSignUp.value = false
                Log.w(SIGN_UP, FAILURE, task.exception)
            }

        }
    }

    fun getUserInfo() {
        auth.currentUser?.let { user ->

            val documentRef = db.collection(USERS).document(user.uid)
            documentRef.get()
                .addOnSuccessListener { document ->
                    document?.let {
                        userInfo.value = UserModel(
                            user.email,
                            document.get(PHONE_NUMBER) as String
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(USER_INFO, FAILURE, exception)
                }

        }
    }

    fun signOut() {
        auth.signOut()
    }

}

