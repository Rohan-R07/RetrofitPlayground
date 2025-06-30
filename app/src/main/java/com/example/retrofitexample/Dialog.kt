package com.example.retrofitexample

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.retrofitexample.DataModel.PostsItem
import com.example.retrofitexample.ViewModels.RetroViewModel


@Composable
fun Dialogue(
    dialog: MutableState<Boolean>,
    body: MutableState<String>,
    title: MutableState<String>,
    userIDN: MutableIntState,
    viewModel: RetroViewModel,
    contex: Context
) {

    Dialog(
        onDismissRequest = { dialog.value = !dialog.value },
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
                .clip(RoundedCornerShape(20.dp))
                .height(300.dp)
                .width(400.dp)
                .background(Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // body

            TextField(value = body.value, onValueChange = {
                body.value = it
            }, placeholder = {
                Text(
                    text = "Body"
                )
            })

            // TITLE

            Spacer(Modifier.padding(10.dp))

            TextField(
                value = title.value, onValueChange = {
                    title.value = it
                }, placeholder = {
                    Text(
                        text = "TITLE"
                    )
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )


            // USER ID

            Spacer(Modifier.padding(10.dp))

            TextField(
                value = userIDN.value.toString(), onValueChange = {
                    userIDN.value = it.toInt()
                }, placeholder = {
                    Text(
                        text = "Body"
                    )
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )
            val newPost = PostsItem(body.value,title = title.value, userId =  userIDN.value)
            Spacer(Modifier.padding(10.dp))
            Button(
                onClick = {
                    viewModel.createPosts(newPost, contex)
                    dialog.value = !dialog.value
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = "POST"
                )
            }

        }
    }

}
