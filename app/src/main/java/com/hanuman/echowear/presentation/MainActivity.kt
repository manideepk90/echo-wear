/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.hanuman.echowear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.hanuman.echowear.presentation.theme.EchoWearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {

    EchoWearTheme {
        val pagerState = rememberPagerState(
            initialPage = 0,
        ) { 3 }
        HorizontalPager(
            modifier = Modifier.fillMaxSize(), state = pagerState
        ) { page ->
            // Content for each page
            if (page == 0) {
                val verticalPagerState = rememberPagerState(initialPage = 0) { 2 }
                VerticalPager(
                    modifier = Modifier.fillMaxSize(), state = verticalPagerState
                ) { verticalPage ->
                    VerticalPageContent(verticalPage)
                }
            } else if (page == 1) Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (page % 2 == 0) MaterialTheme.colors.primary else MaterialTheme.colors.secondary),
                contentAlignment = Alignment.Center
            ) {
                ChatPage()
            }
            else if (page == 2)
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp) // Spacing between buttons
                    ) {
                        // Button 1: New Chat (+ symbol)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Button(

                                onClick = { /* Handle new chat action */ },
                                modifier = Modifier.size(ButtonDefaults.DefaultButtonSize)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Icon",
                                    modifier = Modifier.size(20.dp)
                                )
//                                Spacer(modifier = Modifier.width(8.dp))
//                                Text("New Chat")
                            }

                            // Button 2: Delete Chat (delete icon)
                            Button(
                                onClick = { /* Handle delete chat action */ },
                                modifier = Modifier.size(ButtonDefaults.DefaultButtonSize),
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Icon",
                                    modifier = Modifier.size(20.dp)
                                )
//                                Spacer(modifier = Modifier.width(8.dp))
//                                Text("Delete Chat")
                            }
                        }
                        Row(
//                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Button 3: Info (info icon)
                            Button(
                                onClick = { /* Handle info action */ },
                                modifier = Modifier.size(ButtonDefaults.DefaultButtonSize)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info Icon",
                                    modifier = Modifier.size(20.dp)
                                )
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text("Info")
                            }

                            // Button 4: Model (model icon)
                            Button(
                                onClick = { /* Handle model action */ },
                                modifier = Modifier.size(ButtonDefaults.DefaultButtonSize)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Build, // Replace with a model-related icon if available
                                    contentDescription = "Model Icon",
                                    modifier = Modifier.size(20.dp)
                                )
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text("Model")
                            }
                        }
                    }
                }

        }
    }
}


@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}

@Composable
fun VerticalPageContent(verticalPage: Int) {
    when (verticalPage) {
        0 -> AppScaffold { ListeningPage() }
        1 -> ChatHistoryPage()
        else -> DefaultPage()
    }
}

@Composable
fun ListeningPage() {
    // Animation state for the pulsating effect
    val scale = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        while (true) {
            scale.animateTo(1.5f, animationSpec = tween(1000, easing = LinearEasing))
            scale.animateTo(1f, animationSpec = tween(1000, easing = LinearEasing))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pulsating circular shape
        Box(
            modifier = Modifier
                .size(80.dp * scale.value) // Scale the size dynamically
                .background(
                    color = MaterialTheme.colors.primary.copy(alpha = 0.3f), shape = CircleShape
                )
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Button(modifier = Modifier.size(ButtonDefaults.DefaultButtonSize),
                onClick = { /* Perform mic action */ }) {
                Icon(
                    imageVector = Icons.Rounded.Mic,
                    contentDescription = "Mic action",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun ChatHistoryPage() {
    val listState = rememberResponsiveColumnState(
        contentPadding = ScalingLazyColumnDefaults.padding(
            first = ItemType.SingleButton,
            last = ItemType.Chip,
        ),
    )
    ScreenScaffold(
        scrollState = listState,
    ) {
        ScalingLazyColumn(
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp),
            columnState = listState,
        ) {

            items(5) { index -> // Repeat cards for demonstration
                HistoryAppCard(
                    message = "Chat ${index + 1}"
                )
            }
        }
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun ChatPage() {
    val listState = rememberResponsiveColumnState(
        contentPadding = ScalingLazyColumnDefaults.padding(
            first = ItemType.SingleButton,
            last = ItemType.Chip,
        ),
    )
    ScreenScaffold(
        scrollState = listState,
    ) {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(8.dp),
            columnState = listState,
        ) {
            items(5) { index -> // Repeat cards for demonstration

                Card(
                    onClick = { /* Your onClick logic */ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp), // Ensure card has rounded corners
                ) {
                    Row { // Add padding inside the card
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Message,
                            modifier = Modifier
                                .size(12.dp)
                                .wrapContentSize(align = Alignment.Center),
                            contentDescription = "Message icon"
                        )
                        Spacer(modifier = Modifier.size(4.dp)) // Add spacing
                        Text("EchoWear", style = MaterialTheme.typography.caption2)
//                        Spacer(modifier = Modifier.size(4.dp)) // Add spacing
                        // Time
                        Text(
                            "12 AM",
                            style = MaterialTheme.typography.caption2,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }



                    Spacer(modifier = Modifier.height(4.dp))

                    // Message or Content
                    Text(
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ",
                        style = MaterialTheme.typography.caption3
                    )
                }
            }

        }

    }
}


@Composable
fun HistoryAppCard(message: String) {
    Card(modifier = Modifier.fillMaxWidth(),
//            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp), onClick = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            }
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                modifier = Modifier
                    .rotate(180f)
                    .size(16.dp),
                contentDescription = "Navigate",
                tint = MaterialTheme.colors.primary
            )
//            }
        }
    }
}

@Composable
fun DefaultPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.error),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Page not found",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onError
        )
    }
}