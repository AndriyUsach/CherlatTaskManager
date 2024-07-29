package com.cherlat.taskmanager.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherlat.taskmanager.R
import com.cherlat.taskmanager.data.fakeTaskData
import com.cherlat.taskmanager.model.TaskInfo
import com.cherlat.taskmanager.ui.theme.BlackIconTint
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import com.cherlat.taskmanager.ui.theme.GreyLight
import com.cherlat.taskmanager.ui.theme.GreyStroke


@Composable
fun TaskActiveCard(
    task: TaskInfo,
    modifier: Modifier = Modifier,
    markAsDone: () -> Unit = {}
) {

    Surface(
        modifier = modifier then Modifier
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(24f),
        color = GreyLight,
        border = BorderStroke(1.dp, GreyStroke)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TaskTimeLabel(timeStamp = task.timeStamp)

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = null,
                    tint = BlackIconTint,
                    modifier = Modifier
                        .border(1.dp, GreyStroke, CircleShape)
                        .clip(CircleShape)
                        .padding(10.dp)
                )

            }

            Text(
                text = task.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            TaskSwipeButton() { markAsDone() }

        }
    }

}




@Preview
@Composable
private fun TaskInfoToDoPreview() {
    CherlatTaskManagerTheme {
        TaskActiveCard(
            task = fakeTaskData.tasks[1],
            modifier = Modifier.padding(10.dp)
        )
    }
}
