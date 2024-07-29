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
import com.cherlat.taskmanager.data.fakeTaskData
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
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        )

        Surface(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Grey,
            border = BorderStroke(1.dp, GreyStroke),
            modifier = Modifier
                .padding(top = 130.dp + paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {

                item {
                    Text(
                        text = "Tasks for today",
                        fontWeight = FontWeight.W600,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                items(uiState.tasks, key = { it.id }) {
                    TaskActiveCard(
                        task = it,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        viewModel.markTaskAsDone(it)
                    }
                }
//                fakeTaskData.tasks.forEach {
//                    item {
//                        TaskActiveCard(
//                            task = it,
//                            modifier = Modifier.padding(vertical = 4.dp)
//                        )
//                    }
//                }

                item {
                    Text(
                        text = "Completed",
                        fontWeight = FontWeight.W600,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                items(uiState.completed, key = { it.id }) {
                    TaskCompletedCard(
                        task = it,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        viewModel.markTaskAsUndone(it)
                    }
                }

//                fakeTaskData.completedTasks.forEach {
//                    item {
//                        TaskCompletedCard(
//                            task = it,
//                            modifier = Modifier.padding(vertical = 4.dp)
//                        )
//                    }
//                }

            }
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