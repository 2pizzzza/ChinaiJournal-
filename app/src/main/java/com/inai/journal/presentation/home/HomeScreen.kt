package com.inai.journal.presentation.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inai.journal.R
import com.inai.journal.ui.theme.MediumGray
import com.inai.journal.ui.theme.Red

@Composable
fun HomeScreen() {
    val daysOfWeek = listOf("Mon", "Die", "Mie", "Don", "Fre")
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Список уроков для каждого дня недели
    val lessonsByDay = listOf(
        // Понедельник
        listOf(
            Lesson(1, "Math", "Lec", "9:30 - 10:50", "205", "Win-1-22", true),
            Lesson(2, "Physics", "Pr", "11:00 - 12:20", "206", "Win-1-23", false),
            Lesson(3, "Chemistry", "Lab", "13:00 - 14:20", "Lab-1", "Win-1-24", true),
        ),
        // Вторник
        listOf(
            Lesson(4, "History", "Lec", "9:30 - 10:50", "207", "Win-1-25", false),
            Lesson(5, "English", "Pr", "11:00 - 12:20", "208", "Win-1-26", true),
        ),
        // Среда
        listOf(
            Lesson(6, "Biology", "Lab", "9:30 - 10:50", "Lab-2", "Win-1-27", false),
            Lesson(7, "Math", "Pr", "11:00 - 12:20", "209", "Win-1-28", true),
            Lesson(8, "Physics", "Lec", "13:00 - 14:20", "210", "Win-1-29", false),
        ),
        // Четверг
        listOf(
            Lesson(9, "Chemistry", "Pr", "9:30 - 10:50", "211", "Win-1-30", true),
            Lesson(10, "Math", "Lec", "11:00 - 12:20", "212", "Win-1-31", false),
        ),
        // Пятница
        listOf(
            Lesson(11, "English", "Lec", "9:30 - 10:50", "213", "Win-1-32", true),
            Lesson(12, "History", "Pr", "11:00 - 12:20", "214", "Win-1-33", false),
            Lesson(13, "Biology", "Lec", "13:00 - 14:20", "215", "Win-1-34", true),
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex) {
            daysOfWeek.forEachIndexed { index, day ->
                Tab(
                    text = { Text(day) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        LazyColumn {
            items(lessonsByDay[selectedTabIndex]) { lesson ->
                LessonItem(lesson)
            }
        }
    }
}




data class Lesson(
    val id: Int,
    val name: String,
    val lecpc:String,
    val time: String,
    val location: String,
    val group: String,
    val isHear: Boolean
)

@Composable
fun LessonItem(item: Lesson){
    Card(onClick = { /*TODO*/ },
        modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp)
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .border(1.dp, if (item.isHear) Color.Gray else Color.Red)
                .background(if (item.isHear) MediumGray else Red))
         {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = item.lecpc,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .background(Color.White, shape = CircleShape)
                        .circleLayout()
                        .padding(20.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp),
                ){
                    Text(
                        text = item.name,
                        style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.W700),
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    Text(text = item.time,
                        style = TextStyle(fontWeight = FontWeight.W300, fontSize = 12.sp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(top=15.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_location_on_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(15.dp)

                        )
                        Text(
                            text = item.location,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(top=15.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_location_on_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(15.dp)
                        )
                        Text(
                            text = item.group,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

fun Modifier.circleLayout() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        val currentHeight = placeable.height
        val currentWidth = placeable.width
        val newDiameter = maxOf(currentHeight, currentWidth)

        layout(newDiameter, newDiameter) {
            placeable.placeRelative((newDiameter-currentWidth)/2, (newDiameter-currentHeight)/2)
        }
    }