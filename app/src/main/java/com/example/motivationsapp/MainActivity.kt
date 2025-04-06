package com.example.motivationsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {

    private val quotes = listOf(
        "Du klarer det!",
        "En dag ad gangen.",
        "Du er sejere end du tror.",
        "Lad ikke frygten styre dig.",
        "Husk at trække vejret.",
        "Fremtiden begynder nu.",
        "Tro på, at du kan, og du er halvvejs - Theodore Roosevelt",
        "Du er modigere end du tror, stærkere end du ser ud til, og klogere end du ved - Peter Plys",
        "Hvis du bliver træt, skal du lære at hvile, ikke at give op - Banksy",
        "Du har styrken i dig."
    )

    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    private lateinit var quoteTextView: TextView // Gør den til en klassevariabel
    private lateinit var favoriteQuoteTextView: TextView // Gør den til en klassevariabel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser views
        quoteTextView = findViewById(R.id.quoteTextView)
        favoriteQuoteTextView = findViewById(R.id.favoriteQuoteTextView) // Korrekt ID
        val generateButton = findViewById<Button>(R.id.generateButton)
        val saveFavoriteButton = findViewById<Button>(R.id.saveFavoriteButton) // Korrekt ID

        // Generér et tilfældigt citat
        generateButton.setOnClickListener {
            val randomQuote = quotes.random()
            quoteTextView.text = randomQuote
        }

        // Gem favoritcitatet
        saveFavoriteButton.setOnClickListener {
            val currentQuote = quoteTextView.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                saveFavoriteQuote(currentQuote)
                val favoriteQuote = getFavoriteQuote()
                runOnUiThread {
                    favoriteQuoteTextView.text = favoriteQuote // Opdater favoritcitatet
                }
            }
        }

        // Hent og vis favoritcitatet ved app-start
        CoroutineScope(Dispatchers.IO).launch {
            val favoriteQuote = getFavoriteQuote()
            runOnUiThread {
                favoriteQuoteTextView.text = favoriteQuote
            }
        }
    }

    private suspend fun saveFavoriteQuote(quote: String) {
        val favoriteQuoteKey = stringPreferencesKey("favorite_quote")
        dataStore.edit { preferences ->
            preferences[favoriteQuoteKey] = quote
        }
    }

    private suspend fun getFavoriteQuote(): String {
        val favoriteQuoteKey = stringPreferencesKey("favorite_quote")
        val preferences = dataStore.data.first()
        return preferences[favoriteQuoteKey] ?: "Ingen favorit gemt endnu."
    }
}