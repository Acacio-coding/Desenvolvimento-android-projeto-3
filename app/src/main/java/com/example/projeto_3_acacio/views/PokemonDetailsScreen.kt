package com.example.projeto_3_acacio.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projeto_3_acacio.R
import com.example.projeto_3_acacio.data.domain.Pokemon

@Composable
fun PokemonDetailsScreen(
    pokemon: Pokemon
) {
    Form(pokemon = pokemon)
}

@Composable
fun Form(
    pokemon: Pokemon
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp)
                .clip(RectangleShape)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.sprite)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.pokeball),
            placeholder = painterResource(R.drawable.pokeball),
            contentDescription = pokemon.name,
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = White,
                focusedBorderColor = White,
                unfocusedLabelColor = White,
                focusedLabelColor = White,
                cursorColor = White
            ),
            textStyle = TextStyle(color = White, fontSize = 16.sp),
            label = {
                Text(text = "Number", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            },
            value = pokemon.number,
            readOnly = true,
            onValueChange = {}
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = White,
                focusedBorderColor = White,
                unfocusedLabelColor = White,
                focusedLabelColor = White,
                cursorColor = White
            ),
            textStyle = TextStyle(color = White, fontSize = 16.sp),
            label = {
                Text(text = "Name", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            },
            value = pokemon.name,
            readOnly = true,
            onValueChange = {}
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = White,
                focusedBorderColor = White,
                unfocusedLabelColor = White,
                focusedLabelColor = White,
                cursorColor = White
            ),
            textStyle = TextStyle(color = White, fontSize = 16.sp),
            label = {
                Text(text = "Generation", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            },
            value = "${pokemon.gen}",
            readOnly = true,
            onValueChange = {}
        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = White,
                focusedBorderColor = White,
                unfocusedLabelColor = White,
                focusedLabelColor = White,
                cursorColor = White
            ),
            textStyle = TextStyle(color = White, fontSize = 16.sp),
            label = {
                Text(text = "Type1", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            },
            value = pokemon.types[0],
            readOnly = true,
            onValueChange = {}
        )

        if (pokemon.types.size > 1) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = White,
                    focusedBorderColor = White,
                    unfocusedLabelColor = White,
                    focusedLabelColor = White,
                    cursorColor = White
                ),
                textStyle = TextStyle(color = White, fontSize = 16.sp),
                label = {
                    Text(text = "Type2", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                },
                value = pokemon.types[1],
                readOnly = true,
                onValueChange = {}
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .height(80.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = White,
                focusedBorderColor = White,
                unfocusedLabelColor = White,
                focusedLabelColor = White,
                cursorColor = White
            ),
            textStyle = TextStyle(color = White, fontSize = 16.sp),
            label = {
                Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            },
            value = pokemon.description,
            readOnly = true,
            onValueChange = {}
        )
    }
}