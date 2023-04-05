package com.ruan.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruan.exam.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Column {
                    examBottomBar(selected = 0)
                }
            }
        }
    }

    @Composable
    private fun examBottomBar(selected: Int) {
        Row {
            TabItem(
                if (selected == 0) R.drawable.ic_home else R.drawable.ic_home_select,
                "主页",
                if (selected == 0) Color.Black else Color.Gray,
            )
            TabItem(
                if (selected == 1) R.drawable.ic_me else R.drawable.ic_me_select,
                "我的",
                if (selected == 1) Color.Black else Color.Gray,
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun examBottomBarPreview() {
        examBottomBar(1)
    }

    @Composable
    private fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color) {
        Column(
            Modifier.padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painterResource(iconId), title, Modifier.size(24.dp),
                tint = tint
            )
            Text(title, fontSize = 11.sp)
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun TabItemPreview() {
        TabItem(iconId = R.drawable.ic_home, title = "主页", Color.Blue)
    }


}
