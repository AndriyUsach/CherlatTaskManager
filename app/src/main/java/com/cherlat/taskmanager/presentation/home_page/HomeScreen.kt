package com.cherlat.taskmanager.presentation.home_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherlat.taskmanager.model.TaskInfo
import com.cherlat.taskmanager.presentation.components.TaskActiveCard
import com.cherlat.taskmanager.presentation.components.TaskCompletedCard
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import com.cherlat.taskmanager.ui.theme.Grey
import com.cherlat.taskmanager.ui.theme.GreyStroke

@Composable
fun HomeScreen(
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: HomeVM = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeHeader(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            progress = uiState.progress
        )

        Surface(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Grey,
            border = BorderStroke(1.dp, GreyStroke),
            modifier = Modifier
                .padding(top = 130.dp + paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {
            val listContentPaddings = PaddingValues(
                top = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            )

            TasksList(
                uiState = uiState,
                contentPaddingValues = listContentPaddings,
                markAsDone = { viewModel.markTaskAsDone(it) },
                markAsUnDone = { viewModel.markTaskAsUndone(it) }
            )

        }

    }
}

@Composable
private fun TasksList(
    uiState: HomeUiState,
    contentPaddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    markAsDone: (TaskInfo) -> Unit,
    markAsUnDone: (TaskInfo) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = contentPaddingValues
    ) {

        if (uiState.todo.isNotEmpty()) {
            item {
                Text(
                    text = "Tasks for today",
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }

        items(uiState.todo, key = { it.id }) {
            TaskActiveCard(
                task = it,
                modifier = Modifier.padding(vertical = 4.dp),
            ) { markAsDone(it) }
        }

        if (uiState.completed.isNotEmpty()) {
            item {
                Text(
                    text = "Completed",
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }

        items(uiState.completed, key = { it.id }) {
            TaskCompletedCard(
                task = it,
                modifier = Modifier.padding(vertical = 4.dp)
            ) { markAsUnDone(it) }
        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CherlatTaskManagerTheme {
        HomeScreen(PaddingValues())
    }
}