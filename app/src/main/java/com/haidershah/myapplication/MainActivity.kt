package com.haidershah.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.haidershah.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val cardWidth = 350.dp
    val cardPadding = 8.dp

    val colors by viewModel.colorsState.collectAsStateWithLifecycle()
    val isAutoScrollEnabled by viewModel.autoScrollState.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()

    LaunchedEffect(isAutoScrollEnabled) {
        if (isAutoScrollEnabled) {
            while (true) {
                delay(500.milliseconds)

                if (lazyListState.canScrollForward && !lazyListState.lastScrolledBackward) {
                    lazyListState.animateScrollToItem(lazyListState.firstVisibleItemIndex + 1)
                } else if (!lazyListState.canScrollForward) { // end of list
                    lazyListState.animateScrollToItem(lazyListState.firstVisibleItemIndex)
                } else {
                    lazyListState.animateScrollToItem(lazyListState.firstVisibleItemIndex - 1)
                }
            }
        }
    }

    Column(modifier = modifier) {
        LazyRow(state = lazyListState) {
            items(items = colors) {
                Card(
                    modifier = Modifier.padding(cardPadding),
                    border = BorderStroke(1.dp, colorResource(R.color.black))
                ) {
                    Column(
                        modifier = Modifier
                            .width(cardWidth)
                            .height(150.dp)
                            .background(colorResource(it.colorResId))
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(text = it.colorName)
                        Text(text = it.colorHex)
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(isAutoScrollEnabled, {
                viewModel.onAutoScrollClicked()
            })
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable(onClick = { viewModel.onAutoScrollClicked() }),
                text = "Auto Scroll"
            )
        }
    }
}
