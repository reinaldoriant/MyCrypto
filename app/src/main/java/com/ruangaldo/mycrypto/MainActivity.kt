package com.ruangaldo.mycrypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ruangaldo.mycrypto.domain.CryptoFeedViewModel
import com.ruangaldo.mycrypto.ui.theme.MyCryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CryptoFeedRoute()
                }
            }
        }
    }
}

@Composable
fun CryptoFeedRoute(
    viewModel: CryptoFeedViewModel = viewModel(factory = CryptoFeedViewModel.FACTORY)
) {
    CryptoFeedScreen()
}

@Composable
fun CryptoFeedScreen() {
    Text(text = "Starter Project")
}
