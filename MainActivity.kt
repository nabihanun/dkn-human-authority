package com.dkn.humanauthority

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Notice(val title:String, val body:String)
data class Event(val title:String, val date:String, val place:String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DknApp() }
    }
}

@Composable
fun DknApp() {
    var tab by remember { mutableIntStateOf(0) }
    val notices = listOf(
        Notice("স্বাগতম", "দক্ষিণ কেবল নগর হিউম্যান অথরিটির অফিসিয়াল Android অ্যাপে আপনাকে স্বাগতম।"),
        Notice("মাদকবিরোধী সচেতনতা", "মাদকমুক্ত সমাজ গঠনে সবাইকে সচেতন হওয়ার আহ্বান।")
    )
    val events = listOf(
        Event("সামাজিক সচেতনতা কর্মসূচি", "তারিখ অ্যাডমিন দ্বারা নির্ধারিত হবে", "দক্ষিণ কেবল নগর, ৮নং ওয়ার্ড"),
        Event("উঠান বৈঠক", "তারিখ অ্যাডমিন দ্বারা নির্ধারিত হবে", "দক্ষিণ কেবল নগর")
    )

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("দক্ষিণ কেবল নগর", fontWeight = FontWeight.Bold)
                            Text("হিউম্যান অথরিটি", fontSize = 12.sp)
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    listOf(
                        "হোম" to Icons.Default.Home,
                        "কার্যক্রম" to Icons.Default.Event,
                        "সদস্য" to Icons.Default.Group,
                        "অনুদান" to Icons.Default.AccountBalanceWallet,
                        "যোগাযোগ" to Icons.Default.Call
                    ).forEachIndexed { i, item ->
                        NavigationBarItem(
                            selected = tab == i,
                            onClick = { tab = i },
                            icon = { Icon(item.second, null) },
                            label = { Text(item.first) }
                        )
                    }
                }
            }
        ) { pad ->
            when(tab) {
                0 -> HomeScreen(notices)
                1 -> EventsScreen(events)
                2 -> MemberScreen()
                3 -> DonationScreen()
                else -> ContactScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(notices: List<Notice>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("মানবতার সেবায়, সমাজের উন্নতি", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("সামাজিক উন্নয়ন, মাদকমুক্ত সমাজ, পরিবেশ সংরক্ষণ ও জনসচেতনতার মাধ্যমে সুন্দর সমাজ গড়াই আমাদের অঙ্গীকার।")
                }
            }
        }
        item { Text("সাম্প্রতিক নোটিশ", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
        items(notices) { n ->
            Card {
                Column(Modifier.padding(16.dp)) {
                    Text(n.title, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(5.dp))
                    Text(n.body)
                }
            }
        }
    }
}

@Composable
fun EventsScreen(events: List<Event>) {
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("কর্মসূচি ও ইভেন্ট", fontSize = 22.sp, fontWeight = FontWeight.Bold) }
        items(events) { e ->
            Card {
                Column(Modifier.padding(16.dp)) {
                    Text(e.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("📅 ${e.date}")
                    Text("📍 ${e.place}")
                }
            }
        }
    }
}

@Composable
fun MemberScreen() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var sent by remember { mutableStateOf(false) }
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("সদস্য হওয়ার আবেদন", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(name, { name = it }, label = { Text("পূর্ণ নাম") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(phone, { phone = it }, label = { Text("মোবাইল নম্বর") }, modifier = Modifier.fillMaxWidth())
            Button(
                onClick = { sent = true },
                enabled = name.isNotBlank() && phone.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) { Text("আবেদন জমা দিন") }
            if (sent) Text("আপনার আবেদন গ্রহণের জন্য প্রস্তুত হয়েছে। অনলাইন ডাটাবেস যুক্ত করলে এটি সরাসরি অ্যাডমিনের কাছে যাবে।")
        }
    }
}

@Composable
fun DonationScreen() {
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("অনুদান", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("সংগঠনের সামাজিক কার্যক্রমে সহযোগিতা করুন।")
            Spacer(Modifier.height(12.dp))
            Card {
                Column(Modifier.padding(16.dp)) {
                    Text("বিকাশ / নগদ / ব্যাংক", fontWeight = FontWeight.Bold)
                    Text("পেমেন্ট নম্বর ও ব্যাংক তথ্য অ্যাডমিন সেট করবেন।")
                }
            }
            Spacer(Modifier.height(12.dp))
            Text("আয়-ব্যয়ের হিসাব", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("অনলাইন ডাটাবেস যুক্ত হলে এখানে রিয়েল-টাইম হিসাব দেখানো হবে।")
        }
    }
}

@Composable
fun ContactScreen() {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("যোগাযোগ", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("দক্ষিণ কেবল নগর হিউম্যান অথরিটি")
        Text("দক্ষিণ কেবল নগর, ৮নং ওয়ার্ড")
        Text("ফোন, Facebook ও অন্যান্য যোগাযোগের তথ্য অ্যাডমিন যুক্ত করবেন।")
    }
}
