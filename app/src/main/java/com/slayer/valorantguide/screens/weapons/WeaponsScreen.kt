package com.slayer.valorantguide.screens.weapons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.slayer.domain.models.weapons.WeaponModel
import com.slayer.valorantguide.ui.theme.md_theme_dark_secondaryContainer

@Composable
fun WeaponsScreen(
    vm: WeaponsViewModel = hiltViewModel<WeaponsViewModel>(),
    navHostController: NavHostController,
    appBarTitle: MutableState<String>
) {
    SideEffect {
        appBarTitle.value = "Weapons"

        vm.getWeaponFromLocal()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
        ),
    ) {
        vm.weaponsResult.value?.let { weapons ->
            items(weapons, key = { it.uuid }, contentType = { WeaponModel::class }) { buddy ->
                WeaponCard(buddy,navHostController)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun WeaponCard(weapon: WeaponModel,navHostController : NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        border = BorderStroke(2.dp, md_theme_dark_secondaryContainer),
        onClick = { navHostController.navigate("weaponDetails/${weapon.uuid}") }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            AsyncImage(
                model = weapon.image,
                contentDescription = null,
                contentScale = ContentScale.Fit, // Set the content scale as needed
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .padding(32.dp)
            )

            Box(
                modifier = Modifier
                    .weight(.75f)
                    .fillMaxSize()
                    .background(color = md_theme_dark_secondaryContainer)
                    .padding(8.dp),
                contentAlignment = Alignment.Center,

                ) {
                Text(
                    text = weapon.name,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }
}