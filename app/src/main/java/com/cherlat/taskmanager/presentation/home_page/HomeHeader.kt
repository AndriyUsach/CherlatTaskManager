package com.cherlat.taskmanager.presentation.home_page

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherlat.taskmanager.R
import com.cherlat.taskmanager.ui.theme.AccentViolet
import com.cherlat.taskmanager.ui.theme.BlackIconTint
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import com.cherlat.taskmanager.ui.theme.GreyDark
import com.cherlat.taskmanager.ui.theme.MontserratFontFamily
import kotlin.math.roundToInt

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    progress: Float = .0f
) {

    Row(
        modifier = modifier then Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp)
    ) {

        val progressState by animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(400),
            label = "anim_progress"
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = getAnnotatedStringForHeaderTitle(),
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {

                Text(
                    text = "Progress",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .weight(1f)
                        .alignByBaseline()
                )

                Text(
                    text = getAnnotatedStringForProgress(progressState),
                    color = AccentViolet,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.alignByBaseline(),
                )

            }

            LinearProgressIndicator(
                progress = { progressState },
                trackColor = AccentViolet.copy(alpha = 0.3f),
                color = AccentViolet,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(8.dp)
                    .clip(CircleShape)

            )

        }

        Image(
            painter = painterResource(id = R.drawable.image_home_header),
            contentDescription = null
        )

    }

}

private fun getAnnotatedStringForHeaderTitle(): AnnotatedString {
    return buildAnnotatedString {
        val firstPart = AnnotatedString(
            text = "I am more ",
            spanStyle = SpanStyle(color = BlackIconTint)
        )

        val secondPart = AnnotatedString(
            text = "than my thoughts",
            spanStyle = SpanStyle(color = GreyDark),
        )

        append(firstPart)
        append(secondPart)
    }
}

private fun getAnnotatedStringForProgress(progress: Float): AnnotatedString {
    return buildAnnotatedString {
        append((progress * 100).roundToInt().toString())
        val percentString = AnnotatedString(
            "%",
            spanStyle = SpanStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
            )
        )
        append(percentString)
    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    CherlatTaskManagerTheme {

        var progress by remember {
            mutableStateOf(0f)
        }

        HomeHeader(
            progress = progress
        )

        LaunchedEffect(key1 = Unit) {
            progress++
        }
    }
}