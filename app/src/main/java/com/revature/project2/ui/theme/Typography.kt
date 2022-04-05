package com.revature.project2.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.revature.project2.R

val Lemonada = FontFamily(
    Font(R.font.lemonada)
)

val HachiMaruPop = FontFamily(
    Font(R.font.hachi_maru_pop)
)



val Project2Typography = Typography(
    h3 = TextStyle(
        fontFamily = HachiMaruPop,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    h4 = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.W600,
        fontSize = 25.sp
    ),
    h6 = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),

    subtitle1 = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),

    body1 = TextStyle(
        fontFamily = HachiMaruPop,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Lemonada,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    caption = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    overline = TextStyle(
        fontFamily = Lemonada,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),

)