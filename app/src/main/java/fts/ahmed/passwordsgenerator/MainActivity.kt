package fts.ahmed.passwordsgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import fts.ahmed.passwordsgenerator.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonsClickListeners()

    }

    private fun buttonsClickListeners() {
        binding.btnGenerate.setOnClickListener {
        binding.editText.setText(generatePassword(15))
        }
        binding.btnCopy.setOnClickListener {

            val text: String = binding.editText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "the password is generated yet", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("password", text)
            clipboard.setPrimaryClip(clip) // Set the ClipBoard's primary clip
            Toast.makeText(this, "copied", Toast.LENGTH_SHORT).show()

        }

    }

    private fun generatePassword(passwordLength: Int): String {
        val password = StringBuilder()
        for (i in 0 until passwordLength) password.append(getRandomSymbol())
        return password.toString()
    }

    private fun getRandomSymbol(): Char {
        val charArray = charArrayOf(
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
        )
        val numbersArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        val specialCharsArray = charArrayOf(
            '!',
            '@',
            '#',
            '$',
            '%',
            '&',
            '*',
            '-',
            '+',
            '=',
            '<',
            '>',
            '.',
            '?',
        )
        val random = Random()
        val w: Int = random.nextInt(3)
        return if (w == 0) {
            charArray[random.nextInt(charArray.size)]
        } else if (w == 1) {
            numbersArray[random.nextInt(numbersArray.size)]
        } else {
            specialCharsArray[random.nextInt(specialCharsArray.size)]
        }
    }
}