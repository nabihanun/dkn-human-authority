package com.dkn.humanauthority

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Notice(
    val title: String,
    val body: String
)

data class Event(
    val title: String,
    val date: String,
    val place: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DknApp()
        }
    }
}

@Composable
fun DknApp() {

    var tab by remember { mutableIntStateOf(0) }

    val notices = listOf(
        Notice(
            "সতর্কতা",
            "দক্ষিণ কেবল নগর এলাকার গুরুত্বপূর্ণ তথ্য ও নোটিশ এখানে প্রকাশ করা হবে।"
        ),
        Notice(
            "সামাজিক সচেতনতা",
            "নিরাপদ ও সুন্দর সমাজ গঠনে সবাইকে সচেতন হওয়ার আহ্বান।"
        ),
        Notice(
            "জরুরি ঘোষণা",
            "প্রয়োজনীয় সরকারি ও স্থানীয় ঘোষণা এই অংশে প্রকাশ করা হবে।"
        )
    )

    val events = listOf(
        Event(
            "সামাজিক সচেতনতা কর্মসূচি",
            "তারিখ: আগামী শুক্রবার",
            "স্থান: কডার বাজার"
        ),
        Event(
            "উঠান বৈঠক",
            "তারিখ: আগামী শনিবার",
            "স্থান: দক্ষিণ কেবল নগর"
        )
    )

    MaterialTheme {

        Scaffold(

            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                "দক্ষিণ কেবলনগর",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "হিউম্যান অথরিটি",
                                fontSize = 12.sp
                            )
                        }
                    }
                )
            },

            bottomBar = {

                NavigationBar {

                    val items = listOf(
                        "হোম" to Icons.Default.Home,
                        "কার্যক্রম" to Icons.Default.Event,
                        "সদস্য" to Icons.Default.Group,
                        "অনুদান" to Icons.Default.AccountBalanceWallet,
                        "যোগাযোগ" to Icons.Default.Call
                    )

                    items.forEachIndexed { index, item ->

                        NavigationBarItem(
                            selected = tab == index,
                            onClick = {
                                tab = index
                            },
                            icon = {
                                androidx.compose.material3.Icon(
                                    imageVector = item.second,
                                    contentDescription = item.first
                                )
                            },
                            label = {
                                Text(item.first)
                            }
                        )
                    }
                }
            }

        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                when (tab) {

                    0 -> HomeScreen(notices)

                    1 -> EventsScreen(events)

                    2 -> MemberScreen()

                    3 -> DonationScreen()

                    4 -> ContactScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(notices: List<Notice>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Text(
                "সর্বশেষ নোটিশ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(notices) { notice ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        notice.title,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        notice.body,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun EventsScreen(events: List<Event>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Text(
                "কার্যক্রম ও অনুষ্ঠান",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(events) { event ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        event.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "📅 ${event.date}",
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        "📍 ${event.place}",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MemberScreen() {

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var sent by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            "সদস্য আবেদন",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "সদস্য হওয়ার জন্য নিচের তথ্য দিন।"
        )

        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("নাম")
            },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            label = {
                Text("মোবাইল নম্বর")
            },
            modifier = Modifier.fillMaxWidth()
        )

        androidx.compose.material3.Button(
            onClick = {
                sent = name.isNotBlank() && phone.isNotBlank()
            },
            enabled = name.isNotBlank() && phone.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("আবেদন পাঠান")
        }

        if (sent) {

            Text(
                "আপনার আবেদন গ্রহণের জন্য প্রস্তুত। পরবর্তী ধাপে Firebase-এর মাধ্যমে এটি সংরক্ষণ করা হবে।"
            )
        }
    }
}

@Composable
fun DonationScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            "অনুদান",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "সংগঠনের সামাজিক কার্যক্রম পরিচালনায় আপনার সহযোগিতা গুরুত্বপূর্ণ।"
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    "বিকাশ / নগদ / ব্যাংক",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "পরবর্তী ধাপে নিরাপদ পেমেন্ট ব্যবস্থা যুক্ত করা হবে।",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ContactScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            "যোগাযোগ",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "দক্ষিণ কেবল নগর  হিউম্যান অথরিটি"
        )

        Text(
            "ফোন: যোগাযোগ নম্বর এখানে যুক্ত হবে"
        )

        Text(
            "ঠিকানা: দক্ষিণ কেবল নগর "
        )

        Text(
            "Facebook ও অন্যান্য যোগাযোগ মাধ্যম পরবর্তী ধাপে যুক্ত করা হবে।"
        )
    }
}
