package com.example.messengerapp.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.messengerapp.CAViewModel
import com.example.messengerapp.CommonImage

enum class ProgressIndicatorState {
    INITIAL,
    ACTIVE,
    COMPLETED
}

@Composable
fun StatusScreen(navController: NavController, vm: CAViewModel, userId: String) {
    val statuses = vm.status.value.filter { it.user?.userId == userId }
    if (statuses.isNotEmpty()) {
        val currentStatus = remember { mutableStateOf(0) }


        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            CommonImage(
                data = statuses[currentStatus.value].imageUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                statuses.forEachIndexed { index, status ->
                    CustomProgressIndicator(
                        modifier = Modifier
                            .weight(1f)
                            .height(7.dp)
                            .padding(1.dp),
                        state = if (currentStatus.value < index)
                            ProgressIndicatorState.INITIAL
                        else if (currentStatus.value == index)
                            ProgressIndicatorState.ACTIVE
                        else
                            ProgressIndicatorState.COMPLETED
                    ) {
                        if (currentStatus.value < statuses.size - 1)
                            currentStatus.value++
                        else
                            navController.popBackStack()
                    }
                }
            }
        }
    }
}

@Composable
fun CustomProgressIndicator(
    modifier: Modifier,
    state: ProgressIndicatorState,
    onComplete: () -> Unit
) {
    var progress = if (state == ProgressIndicatorState.INITIAL) 0f else 1f
    if (state == ProgressIndicatorState.ACTIVE) {
        val toggleState = remember { mutableStateOf(false) }
        LaunchedEffect(toggleState) {
            toggleState.value = true
        }
        val p: Float by animateFloatAsState(
            if (toggleState.value) 1f else 0f,
            animationSpec = tween(5000),
            finishedListener = {
                onComplete.invoke()
            }
        )
        progress = p
    }

    LinearProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = Color.Red
    )
}