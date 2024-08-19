package `in`.madhwendra.usbdebugging

import android.app.AlertDialog
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       if (isUsbEnabled(this)){
           showUsbDialog(this)
       }

    }

    private fun showUsbDialog(activity: MainActivity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("USB Debugging")
        builder.setMessage("Usb debugging is enabled")
        builder.setCancelable(false)

        builder.setPositiveButton("OK"){ dailog, _ ->
            finish()
        }
        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun isUsbEnabled(activity: MainActivity): Boolean {
        return try {
            val usbDebugging = Settings.Global.getInt(activity.contentResolver,Settings.Global.ADB_ENABLED,0)
            usbDebugging == 1
        } catch (e :Exception){
            false
        }
    }


}