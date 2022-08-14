importScripts("https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js");

// Initialize Firebase
var config = {
  apiKey: "AIzaSyAH2b61bqynf-4o-HKOXnTi64WgWDpBPNU",
  authDomain: "onsikgo.firebaseapp.com",
  // databaseURL: "https://onsikgo.firebaseio.com",
  projectId: "onsikgo",
  storageBucket: "onsikgo.appspot.com",
  messagingSenderId: "580899564705",

  appId: "1:580899564705:web:ba803077a8a0fb894a54e0",
  measurementId: "G-X12T9KQEVG",
};
firebase.initializeApp(config);

const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function (payload) {
  const title = "Hello World";
  const options = {
    body: payload.data.status,
  };

  return self.registration.showNotification(title, options);
});
