package com.cherlat.taskmanager.presentation.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherlat.taskmanager.R
import com.cherlat.taskmanager.ui.theme.BlackIconTint
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import com.cherlat.taskmanager.ui.theme.GreyStroke
import java.util.Locale

private val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)

@Composable
fun TaskTimeLabel(timeStamp: Long) {

    val date = simpleDateFormat.format(timeStamp)

    Row(
        modifier = Modifier
            .border(1.dp, GreyStroke, CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(32.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_alarm),
            contentDescription = null,
            tint = BlackIconTint,
            modifier = Modifier
                .wrapContentSize()
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = date.toString(),
            fontSize = 12.sp,
            lineHeight = 13.2.sp
        )

    }

}


@Preview
@Composable
private fun Preview() {
    CherlatTaskManagerTheme {
        TaskTimeLabel(timeStamp = System.currentTimeMillis())
    }
}