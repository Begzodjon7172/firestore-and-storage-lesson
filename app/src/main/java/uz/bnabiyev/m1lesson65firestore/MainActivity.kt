package uz.bnabiyev.m1lesson65firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import uz.bnabiyev.m1lesson65firestore.databinding.ActivityMainBinding
import uz.bnabiyev.m1lesson65firestore.models.User

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firestore = FirebaseFirestore.getInstance()

        // malumot qo'shish
        binding.btnAddData.setOnClickListener {
            val user = User(1, "Begzodjon", "nabiyevbegzodjon40@gmail.com", 21)
            firestore.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Log.d(TAG, "onCreate: ${it.id} -> shu id bilan qoshildi")
                }.addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // malumotni o'qib olish
        binding.btnReadData.setOnClickListener {
            firestore.collection("users")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    querySnapshot.forEach {
                        val user = it.toObject(User::class.java)
                        Log.d(TAG, "onCreate: $user")
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }


            // document ni o'chirish
//            firestore.collection("users").document("").delete()
        }


    }
}