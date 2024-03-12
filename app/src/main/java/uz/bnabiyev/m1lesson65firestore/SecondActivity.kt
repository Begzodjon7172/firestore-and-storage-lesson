package uz.bnabiyev.m1lesson65firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import uz.bnabiyev.m1lesson65firestore.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    private lateinit var storage: FirebaseStorage
    private lateinit var reference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()
        reference = storage.getReference("images")

        binding.btn.setOnClickListener {
            launcher.launch("image/*")
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

        if (uri == null) return@registerForActivityResult
        reference
            .child("${System.currentTimeMillis()}.png")
            .putFile(uri)
            .addOnSuccessListener {

                it.metadata?.reference?.downloadUrl?.addOnSuccessListener {uri->
                    Picasso.get().load(uri).into(binding.img)
                }

            }.addOnFailureListener {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }

    }
}