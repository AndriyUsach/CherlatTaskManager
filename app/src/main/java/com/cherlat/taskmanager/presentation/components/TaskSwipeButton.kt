package com.cherlat.taskmanager.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherlat.taskmanager.R
import com.cherlat.taskmanager.ui.theme.AccentGreen
import com.cherlat.taskmanager.ui.theme.AccentViolet
import com.cherlat.taskmanager.ui.theme.BlackIconTint
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import com.cherlat.taskmanager.ui.theme.GreyStroke
import kotlin.math.roundToInt

enum class DragAnchors {
    Start,
    End,
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskSwipeButton(
    modifier: Modifier = Modifier,
    reversed: Boolean = false,
    onSwipe: () -> Unit = {}
) {

    val buttonStringRes = if (reversed) {
        R.string.drag_to_undone
    } else {
        R.string.drag_to_done
    }

    val indicatorIconRes = if (reversed) R.drawable.ic_close else R.drawable.ic_done
    val indicatorColor = if (reversed) AccentViolet else AccentGreen
    val indicatorTint = if (reversed) Color.White else BlackIconTint

    val density = LocalDensity.current

    val indicatorSize = 60.dp
    val indicatorSizePx = with(density) { indicatorSize.toPx() }

    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(indicatorSize)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, GreyStroke, CircleShape)
    ) {

        val draggableState = remember {
            AnchoredDraggableState(
                initialValue = DragAnchors.Start,
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { 100.dp.toPx() } },
                animationSpec = tween()
            ).apply {
                val layoutWidth = with(density) { maxWidth.toPx() }
                val anchors: DraggableAnchors<DragAnchors> = if (reversed) {
                    DraggableAnchors {
                        DragAnchors.Start at layoutWidth - indicatorSizePx
                        DragAnchors.End at 0f
                    }
                } else {
                    DraggableAnchors {
                        DragAnchors.Start at 0f
                        DragAnchors.End at layoutWidth - indicatorSizePx
                    }
                }
                updateAnchors(anchors)
            }
        }

        LaunchedEffect(key1 = draggableState.currentValue) {
            if (draggableState.currentValue == DragAnchors.End) {
                onSwipe()
            }
        }

        val alpha by remember {
            derivedStateOf {
                val progress = draggableState.progress
                if (draggableState.currentValue == DragAnchors.Start) {
                    if (progress == 1f) 1f else (1f - (progress * 1.5f)).coerceAtLeast(0f)
                } else {
                    progress
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .matchParentSize()
                .fillMaxSize()
                .alpha(alpha)
                .graphicsLayer {
                    this.translationX = if (reversed) {
                        draggableState.offset.isNaN()
                        -size.width + draggableState.requireOffset() + indicatorSizePx
                    } else {
                        draggableState.requireOffset()
                    }
                }
        ) {

            Text(
                text = stringResource(id = buttonStringRes),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrows_vector),
                contentDescription = null,
                modifier = Modifier
                    .align(if (reversed) Alignment.CenterStart else Alignment.CenterEnd)
                    .padding(end = 24.dp, start = 24.dp)
                    .graphicsLayer {
                        this.scaleX = if (reversed) -1f else 1f
                    }
            )

        }

        SwipeIndicator(
            iconRes = indicatorIconRes,
            color = indicatorColor,
            tint = indicatorTint,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(
                        x = draggableState
                            .requireOffset()
                            .roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(draggableState, Orientation.Horizontal)
        )
    }
}

@Composable
private fun SwipeIndicator(
    @DrawableRes iconRes: Int,
    color: Color,
    tint: Color,
    modifier: Modifier = Modifier,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(6.dp)
            .fillMaxHeight()
            .aspectRatio(
                ratio = 1f,
                matchHeightConstraintsFirst = true
            )
            .clip(CircleShape)
            .border(1.dp, GreyStroke, CircleShape)
            .background(color)
    ) {

        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = tint,
        )

    }

}

@Preview
@Composable
private fun TaskSwipeButtonPreview() {
    CherlatTaskManagerTheme {
        TaskSwipeButton(
            Modifier.padding(horizontal = 32.dp, vertical = 40.dp),
        ) {
            println("Swipe")
        }
    }
}

@Preview
@Composable
private fun ReversedTaskSwipeButtonPreview() {
    CherlatTaskManagerTheme {
        TaskSwipeButton(
            Modifier.padding(horizontal = 32.dp, vertical = 40.dp),
            reversed = true
        ) {
            println("Swipe")
        }
    }
}