package com.dajce_olti.nfc_emulator

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Placeholder

class MainActivity : AppCompatActivity() {

    private lateinit var nfcAdapter : NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if(nfcAdapter.isEnabled){
            Toast.makeText(this,  "NFC enabled!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "NFC not available", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        Toast.makeText(this, "NFC intent received!", Toast.LENGTH_LONG).show()
        super.onNewIntent(intent)
    }

    override fun onResume() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING)
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        var intentFilter = arrayOf<IntentFilter>()

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null)
        super.onResume()
    }

    override fun onPause() {
        nfcAdapter.disableForegroundDispatch(this)
        super.onPause()
    }

}
