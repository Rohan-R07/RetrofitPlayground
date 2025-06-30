package com.example.retrofitexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ReportDrawn
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofitexample.DataModel.PostsItem
import com.example.retrofitexample.ViewModels.RetroViewModel
import com.example.retrofitexample.ui.theme.RetrofitExampleTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    val viewmodel = viewModels<RetroViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isLoading = viewmodel.value.isLoading.collectAsState()
            val circularProgression = remember { mutableStateOf(false) }

            var dialogState = remember { mutableStateOf(false) }

            var body = remember { mutableStateOf("") }
            var title = remember { mutableStateOf("") }
            var userId = remember { mutableIntStateOf(0) }


            RetrofitExampleTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            containerColor = Red,
                            onClick = {
                                // POST
                                dialogState.value = true

                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    },
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Retrofit") },
                            actions = {
                                IconButton(
                                    onClick = {
                                        viewmodel.value.deletePost(5, applicationContext)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }

                ) { innerPadding ->
                    if (dialogState.value) {
                        Dialogue(
                            dialog = dialogState,
                            body = body,
                            title = title,
                            userIDN = userId,
                            viewModel = viewmodel.value,
                            contex = applicationContext
                        )
                    }


                    if (isLoading.value) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            LaunchedEffect(Unit) {
                                circularProgression.value = true
                                delay(3000)
                                circularProgression.value = false
                            }
                            if (circularProgression.value)
                                CircularProgressIndicator(
                                    modifier = Modifier.size(200.dp), strokeWidth = 20.dp
                                )
                            else {
                                Text("ERROR NOT FOUND", fontSize = 30.sp)
                                Button(
                                    onClick = {

                                        viewmodel.value.fetchPosts()

                                    }
                                ) {
                                    Text("Reload")
                                }
                            }
                        }
                    } else {
                        MainUi(viewmodel.value, innerPadding)

                    }
                    Log.d("ISLOADINF", isLoading.value.toString())

                }
            }
        }
    }
}

@Composable
fun MainUi(viewMOdel: RetroViewModel, innerPadding: PaddingValues) {

    val postslist = viewMOdel.posts.collectAsState(emptyList<PostsItem>())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(postslist.value) { items ->

            Card(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                border = BorderStroke(width = 3.dp, color = Black)
            ) {

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        "id: ${items.id}"
                    )
                    Text(
                        "title: ${items.title}"
                    )
                    Text(
                        "User Id: ${items.userId}"
                    )
                    Text(
                        "Body: ${items.body}"
                    )
                }

            }
        }
    }


}

//}