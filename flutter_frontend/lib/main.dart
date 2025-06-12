import 'package:ai_crop_disease_system/screens/login.dart';
import 'package:ai_crop_disease_system/screens/otpverify.dart';
import 'package:ai_crop_disease_system/screens/schedule_consultation.dart';
import 'package:ai_crop_disease_system/screens/signup.dart';
import 'package:flutter/material.dart';
import 'screens/start_application.dart'; // Import the StartApplication widget
import 'screens/multilanguage_support.dart'; // Import your multilingual support screen
import 'screens/home_screen.dart'; // Import the HomeScreen widget
import 'screens/recommendation.dart'; // Import Recommendation screen
import 'screens/self_notes.dart'; // Import SelfNotes screen
import 'screens/agronomist_screen.dart'; // Import Agronomist screen
import 'screens/profile_screen.dart'; // Import Profile screen
import 'screens/taskbar.dart'; // Import Taskbar widget

void main() {
  runApp(MyApp());
}

// Main application class
class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'AI Crop Disease Prediction',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          primarySwatch: Colors.green,
        ),
        // Set StartApplication as the initial screen
        home: StartApplication(),
        routes: {
          '/start': (context) => StartApplication(),
          '/multilanguage': (context) => MultilingualSupport(),
          '/home': (context) => HomeScreen(),
          '/self_notes': (context) => SelfNotesScreen(),
          '/agronomist': (context) => AgronomistScreen(),
          '/profile': (context) => FarmerProfileScreen(),
          '/login': (context) => LoginScreen(),
          '/verifyOtp': (context) => VerifyOtpScreen(),
          '/signup': (context) => SignUpScreen(),
          '/Schedule': (context) => ScheduleConsultationScreen()
        });
  }
}


// import 'package:flutter/material.dart';
// import 'package:flutter_localizations/flutter_localizations.dart';
// import 'screens/start_application.dart';
// import 'screens/multilanguage_support.dart';
// import 'screens/home_screen.dart';
// import 'screens/recommendation.dart';
// import 'screens/self_notes.dart';
// import 'screens/agronomist_screen.dart';
// import 'screens/profile_screen.dart';
// import 'screens/taskbar.dart';
// import 'l10n/l10n.dart'; // This is where you'll manage the language files
// import 'package:ai_crop_disease_system/screens/login.dart';
// import 'package:ai_crop_disease_system/screens/otpverify.dart';
// import 'package:ai_crop_disease_system/screens/schedule_consultation.dart';
// import 'package:ai_crop_disease_system/screens/signup.dart';



// void main() {
//   runApp(MyApp());
// }

// class MyApp extends StatefulWidget {
//   @override
//   _MyAppState createState() => _MyAppState();
// }

// class _MyAppState extends State<MyApp> {
//   Locale _locale = Locale('en'); // Set default language to English

//   // Function to change locale
//   void _changeLanguage(Locale locale) {
//     setState(() {
//       _locale = locale;
//     });
//   }

//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       title: 'AI Crop Disease Prediction',
//       debugShowCheckedModeBanner: false,
//       theme: ThemeData(primarySwatch: Colors.green),
//       locale: _locale, // Current locale
//       supportedLocales: L10n.all, // Supported languages
//       localizationsDelegates: [
//         // Add localization delegates
//         GlobalMaterialLocalizations.delegate,
//         GlobalWidgetsLocalizations.delegate,
//         GlobalCupertinoLocalizations.delegate,
//       ],
//       home: StartApplication(onLocaleChange: _changeLanguage),
//       routes: {
//         '/start': (context) => StartApplication(onLocaleChange: _changeLanguage),
//         '/multilanguage': (context) => MultilingualSupport(onLocaleChange: _changeLanguage),
//         '/home': (context) => HomeScreen(),
//         '/self_notes': (context) => SelfNotesScreen(),
//         '/agronomist': (context) => AgronomistScreen(),
//         '/profile': (context) => FarmerProfileScreen(),
//         '/login': (context) => LoginScreen(),
//         '/verifyOtp': (context) => VerifyOtpScreen(),
//         '/signup': (context) => SignUpScreen(),
//         '/Schedule': (context) => ScheduleConsultationScreen(),
//       },
//     );
//   }
// }


