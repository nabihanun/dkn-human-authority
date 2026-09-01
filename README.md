# দক্ষিণ কেবল নগর হিউম্যান অথরিটি — Android App

এটি Android-এর জন্য একটি কার্যকর starter application। এতে Home, Notice, Activities/Events, Member Application, Donation এবং Contact screen আছে।

## সম্পূর্ণ অনলাইন সিস্টেম করতে যা যুক্ত করতে হবে
1. Firebase project তৈরি করুন।
2. Android app package name হিসেবে `com.dkn.humanauthority` যোগ করুন।
3. Firebase Authentication চালু করুন (Phone/Email যেটি চান)।
4. Cloud Firestore চালু করুন।
5. Firebase Storage চালু করুন ছবি/ভিডিওর জন্য।
6. `google-services.json` app/ ফোল্ডারে রাখুন।
7. Admin role-এর জন্য আলাদা authenticated account ও Firestore security rules সেট করুন।
8. bKash/Nagad payment integration করতে merchant/API credentials প্রয়োজন হবে; শুধু নম্বর দেখানোর ক্ষেত্রে API দরকার নেই।

## প্রস্তাবিত Firestore collections
- users
- members
- notices
- events
- gallery
- donations
- expenses
- settings

## গুরুত্বপূর্ণ
Firebase credentials, payment credentials এবং সংগঠনের বাস্তব ফোন/পেমেন্ট নম্বর ছাড়া নিরাপদভাবে production backend সংযুক্ত করা সম্ভব নয়। এগুলো ব্যক্তিগতভাবে এখানে প্রকাশ না করাই ভালো।
