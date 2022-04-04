package com.revature.project2.view.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.view.nav.NavScreens


/**
 * Toy Display card
 *
 * button functionality not created yet
 */
@Composable
fun ToyCard(toy: ToyItem, onClick: () -> Unit){

    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight()
        .clickable {
            onClick()
        },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface)
    {

        Row(verticalAlignment = Alignment.Top)
        {

            Image(painter = rememberCoilPainter(
                request = toy.sImagePath,),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.size(5.dp))

            Column( modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                Text(toy.sName,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center)

                Spacer(Modifier.size(2.dp))

                Text(text = toy.sDescription,
                    style = MaterialTheme.typography.body1)
            }
        }

    }
}

/**
 * Bottom app bar for use between screens
 *
 * button functionality not created yet
 */
@Composable
fun BottomBar(navController: NavController){
    
    val selectedIndex = rememberSaveable { mutableStateOf(0) }
    BottomNavigation(
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.secondary) {
        
        BottomNavigationItem(
            selected = selectedIndex.value == 0,
            onClick = {

                selectedIndex.value = 0
                navController.navigate(NavScreens.BrowseItemsScreen.route)
            },
            icon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "")},
            label = {Text("Browse")}
        )

        BottomNavigationItem(
            selected = selectedIndex.value == 1,
            onClick = {

                selectedIndex.value = 1
                navController.navigate(NavScreens.PostedItemListScreen.route)
            },
            icon = { Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "")},
            label = {Text("Post")}
        )

        BottomNavigationItem(
            selected = selectedIndex.value == 2,
            onClick = {
                selectedIndex.value = 2
                navController.navigate(NavScreens.ProfileScreen.route)
            },
            icon = { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "")},
            label = {Text("Profile")}
        )

    }
}


@Composable
fun ToyCardWithButton(toy: ToyItem,buttonText:String, onClick:()->Unit){

    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight()
        .clickable { },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface){

        Row(verticalAlignment = Alignment.Top) {

            Image(painter = rememberCoilPainter(
                request = toy.sImagePath),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Fit)

            Spacer(Modifier.size(5.dp))

            Column( modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(toy.sName,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center)

                Spacer(Modifier.size(2.dp))

                Text(text = toy.sDescription,
                    style = MaterialTheme.typography.body1)
            }
            Button(onClick = onClick) {
                Text(text = buttonText)
            }
        }

    }
}