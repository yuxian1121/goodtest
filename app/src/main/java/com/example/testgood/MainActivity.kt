package com.example.testgood

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testgood.ui.theme.TestgoodTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestgoodTheme {
                main()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun main() {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopAppBarWithMenu(navController)
            }
        ) {
            NavHost(navController, startDestination = "JumpFirst") {
                composable("JumpFirst") { FirstScreen(navController) }
                composable("JumpSecond") { SecondScreen(navController) }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBarWithMenu(navController: androidx.navigation.NavController) {
        var showMenu by remember { mutableStateOf(false) }
        val context = LocalContext.current

        TopAppBar(
            title = {
                // 使用 Image 替代 Text 作为标题
                Image(
                    painter = painterResource(id = R.drawable.maria),
                    contentDescription = "App Logo",
                    // 根据需要调整图像大小
                )
            },
            actions = {
                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = {
                            navController.navigate("JumpFirst")
                            showMenu = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = {
                            navController.navigate("JumpSecond")
                            showMenu = false
                        }
                    )
                }
            }
        )
    }
}

//@Composable
//fun FirstScreen(navController: NavController) {
//    Box(
//        contentAlignment = Alignment.TopStart, // 将文本对齐到屏幕左上角
//        modifier = Modifier
//            .fillMaxSize() // 让 Box 填充屏幕
//            .padding(top = 60.dp) // 仅在顶部添加20dp的内边距，让文字往下移动
//    ) {
//        Text(
//            text = "簡介",
//            color = Color.Blue
//        )
//    }
//}
//
//
//@Composable
//fun SecondScreen(navController: NavController) {
//    Box(
//        contentAlignment = Alignment.TopStart, // 将文本对齐到屏幕左上角
//        modifier = Modifier
//            .fillMaxSize() // 让 Box 填充屏幕
//            .padding(top = 60.dp) // 仅在顶部添加20dp的内边距，让文字往下移动
//    ) {
//        Text(
//            text = "主要機構",
//            color = Color.Red
//        )
//    }
//}

//@Composable
//fun FirstScreen(navController: NavController) {
//    var isOriginalState by remember { mutableStateOf(true) }
//
//    // For the sake of simplicity and animating each text, image, and button visibility separately
//    // We use AnimatedVisibility for each item that changes based on the state
//    AnimatedVisibility(
//        visible = isOriginalState,
//        enter = fadeIn(animationSpec = tween(3000)),
//        exit = fadeOut(animationSpec = tween(3000))
//    ) {
//        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(top = 70.dp)) {
//            Text(text = "瑪利亞基金會服務總覽", color = Color.Blue)
//        }
//    }
//
//    AnimatedVisibility(
//        visible = !isOriginalState,
//        enter = fadeIn(animationSpec = tween(3000)),
//        exit = fadeOut(animationSpec = tween(3000))
//    ) {
//        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(top = 70.dp)) {
//            Text(text = "關於App作者", color = Color.Blue)
//        }
//    }
//
//    AnimatedVisibility(
//        visible = isOriginalState,
//        enter = fadeIn(animationSpec = tween(3000)),
//        exit = fadeOut(animationSpec = tween(3000))
//    ) {
//        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 90.dp)) {
//            Image(painter = painterResource(id = R.drawable.service), contentDescription = "瑪利亞基金會相關圖片")
//        }
//    }
//
//    AnimatedVisibility(
//        visible = !isOriginalState,
//        enter = fadeIn(animationSpec = tween(3000)),
//        exit = fadeOut(animationSpec = tween(3000))
//    ) {
//        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 90.dp)) {
//            Image(painter = painterResource(id = R.drawable.my_selfpicture), contentDescription = "這邊放自己的圖片")
//        }
//    }
//
//    // Button placement adapts based on the state
//    AnimatedVisibility(
//        visible = true, // Always visible, but we animate the contents on state change
//        enter = fadeIn(animationSpec = tween(3000)), // Apply fading in when changing state
//        exit = fadeOut(animationSpec = tween(3000)) // Apply fading out when changing state
//    ) {
//        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
//            .fillMaxSize()
//            .padding(top = if (isOriginalState) 400.dp else 570.dp)) {
//            Button(onClick = { isOriginalState = !isOriginalState }) {
//                Text(text = if (isOriginalState) "作者: 資管系陳語賢" else "服務總覽")
//            }
//        }
//    }
//}
//
//
//@Composable
//fun SecondScreen(navController: NavController) {
//    Box(
//        contentAlignment = Alignment.TopStart, // 将文本对齐到屏幕左上角
//        modifier = Modifier
//            .fillMaxSize() // 让 Box 填充屏幕
//            .padding(top = 70.dp) // 仅在顶部添加20dp的内边距，让文字往下移动
//    ) {
//        Text(
//            text = "主要機構",
//            color = Color.Red
//        )
//    }
//}

@Composable
fun FirstScreen(navController: NavController) {
    var isOriginalState by remember { mutableStateOf(true) }

    // For the sake of simplicity and animating each text, image, and button visibility separately
    // We use AnimatedVisibility for each item that changes based on the state
    AnimatedVisibility(
        visible = isOriginalState,
        enter = fadeIn(animationSpec = tween(3000)),
        exit = fadeOut(animationSpec = tween(3000))
    ) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(top = 70.dp)) {
            Text(text = "瑪利亞基金會服務總覽", color = Color.Blue)
        }
    }

    AnimatedVisibility(
        visible = !isOriginalState,
        enter = fadeIn(animationSpec = tween(3000)),
        exit = fadeOut(animationSpec = tween(3000))
    ) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(top = 70.dp)) {
            Text(text = "關於App作者", color = Color.Blue)
        }
    }

    AnimatedVisibility(
        visible = isOriginalState,
        enter = fadeIn(animationSpec = tween(3000)),
        exit = fadeOut(animationSpec = tween(3000))
    ) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)) {
            Image(painter = painterResource(id = R.drawable.service), contentDescription = "瑪利亞基金會相關圖片")
        }
    }

    AnimatedVisibility(
        visible = !isOriginalState,
        enter = fadeIn(animationSpec = tween(3000)),
        exit = fadeOut(animationSpec = tween(3000))
    ) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)) {
            Image(painter = painterResource(id = R.drawable.my_selfpicture), contentDescription = "這邊放自己的圖片")
        }
    }

    // Button placement adapts based on the state
    AnimatedVisibility(
        visible = true, // Always visible, but we animate the contents on state change
        enter = fadeIn(animationSpec = tween(3000)), // Apply fading in when changing state
        exit = fadeOut(animationSpec = tween(3000)) // Apply fading out when changing state
    ) {
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
            .fillMaxSize()
            .padding(top = if (isOriginalState) 400.dp else 570.dp)) {
            Button(onClick = { isOriginalState = !isOriginalState }) {
                Text(text = if (isOriginalState) "作者: 資管系陳語賢" else "服務總覽")
            }
        }
    }
}


@Composable
fun SecondScreen(navController: NavController) {
    // Use LocalContext to gain access to the Context
    val context = LocalContext.current

    var showDescription by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(top = 70.dp)) {
        Text(
            text = "主要機構",
            color = Color.Red,
        )

        Row(modifier = Modifier.padding()) {
            Button(onClick = { showDescription = false }) {
                Text("台中市愛心家園")
            }

            Button(
                onClick = { showDescription = true },
                modifier = Modifier.padding()
            ) {
                Text("馬利亞學園")
            }
        }

        if (showDescription == false) {
            Text(text = "「台中市愛心家園」經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。\n")
            Text(
                text = "長按以下圖片，可以觀看愛心家園地圖",
                color = Color.Blue,
            )
            Image(
                painter = painterResource(id = R.drawable.lovehome),
                contentDescription = "愛心家園地圖",
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            // Call openGoogleMap here when long pressed
                            onLongPress = { openGoogleMap(context, "台中市南屯區東興路一段450號") }
                        )
                    }
            )
        }
        if (showDescription) {
            Text(text = "「瑪利亞學園」提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。\n")
            Text(
                text = "雙擊以下圖片，可以觀看瑪利亞學園地圖",
                color = Color.Blue,
            )
            Image(
                painter = painterResource(id = R.drawable.campus),
                contentDescription = "瑪利亞學園地圖",
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            // Call openGoogleMap here when long pressed
                            onDoubleTap = {
                                // Handle the double-tap gesture to open Google Maps with the address for 「瑪利亞學園」
                                openGoogleMap(context, "台中市北屯區經貿東路365號")
                            }
                        )
                    }
            )
        }
    }
}

// Use Context to launch Google Maps
fun openGoogleMap(context: Context, address: String) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    }
}
