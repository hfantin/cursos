package com.github.hfantin.aluvery.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hfantin.aluvery.model.Product
import com.github.hfantin.aluvery.sampledata.sampleProducts
import com.github.hfantin.aluvery.sampledata.sampleSections
import com.github.hfantin.aluvery.ui.components.CardProductItem
import com.github.hfantin.aluvery.ui.components.ProductsSection
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(sections: Map<String, List<Product>>) {
    Column {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(100),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "ícone de lupa") },
            label = { Text("Produto") },
            placeholder = { Text("O que você procura?") }
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            sampleProducts.forEach {p ->
                item {
                    CardProductItem(product = p, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
//            sections.forEach {
//                item {
//                    ProductsSection(
//                        title = it.key,
//                        products = it.value
//                    )
//                }
//            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}